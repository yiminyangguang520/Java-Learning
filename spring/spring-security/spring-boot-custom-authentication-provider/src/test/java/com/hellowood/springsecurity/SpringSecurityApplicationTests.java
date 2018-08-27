package com.hellowood.springsecurity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * The type Spring security application tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Access unprotected.
     *
     * @throws Exception the exception
     */
    @Test
    public void accessUnprotected() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Access protected redirects to login.
     *
     * @throws Exception the exception
     */
    @Test
    public void accessProtectedRedirectsToLogin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/index"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getRedirectedUrl().endsWith("/login"));
    }

    /**
     * Login user.
     *
     * @throws Exception the exception
     */
    @Test
    public void loginUser() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("user").password("password"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    /**
     * Login invalid user.
     *
     * @throws Exception the exception
     */
    @Test
    public void loginInvalidUser() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("invalid").password("invalid"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    /**
     * Login user access protected.
     *
     * @throws Exception the exception
     */
    @Test
    public void loginUserAccessProtected() throws Exception {
        MvcResult mvcResult = mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("user").password("password"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andReturn();

        MockHttpSession httpSession = (MockHttpSession) mvcResult.getRequest().getSession(false);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/index").session(httpSession))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Login user validate logout.
     *
     * @throws Exception the exception
     */
    @Test
    public void loginUserValidateLogout() throws Exception {
        MvcResult mvcResult = mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("user").password("password"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andReturn();

        MockHttpSession httpSession = (MockHttpSession) mvcResult.getRequest().getSession(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/logout").with(SecurityMockMvcRequestPostProcessors.csrf()).session(httpSession))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/index").session(httpSession))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}

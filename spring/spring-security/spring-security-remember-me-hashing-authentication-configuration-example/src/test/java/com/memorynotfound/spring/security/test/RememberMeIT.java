package com.memorynotfound.spring.security.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class RememberMeIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void accessProtectedRedirectsToLogin() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(get("/"))
        .andExpect(status().is3xxRedirection())
        .andReturn();

    assertThat(mvcResult.getResponse().getRedirectedUrl()).endsWith("/login");
  }

  @Test
  public void loginUser() throws Exception {
    this.mockMvc.perform(formLogin().user("user").password("password"))
        .andExpect(authenticated());
  }

  @Test
  public void loginInvalidUser() throws Exception {
    this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
        .andExpect(unauthenticated())
        .andExpect(status().is3xxRedirection());
  }

  @Test
  public void loginUserValidateLogout() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(formLogin().user("user").password("password"))
        .andExpect(authenticated()).andReturn();

    MockHttpSession httpSession = (MockHttpSession) mvcResult.getRequest().getSession(false);

    this.mockMvc.perform(post("/logout").with(csrf()).session(httpSession))
        .andExpect(unauthenticated())
        .andExpect(redirectedUrl("/login?logout"));
    this.mockMvc.perform(get("/user").session(httpSession))
        .andExpect(unauthenticated())
        .andExpect(status().is3xxRedirection());
  }

}

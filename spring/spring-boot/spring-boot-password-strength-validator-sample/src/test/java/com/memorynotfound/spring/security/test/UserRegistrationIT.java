package com.memorynotfound.spring.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationPasswordNotValid() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "new@memorynotfound.com")
                                .param("confirmEmail", "new@memorynotfound.com")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "password", "confirmPassword"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationPasswordNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "new@memorynotfound.com")
                                .param("confirmEmail", "new@memorynotfound.com")
                                .param("password", "xjD1!3djk4")
                                .param("confirmPassword", "xjD1!3djk3")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }


    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "new@memorynotfound.com")
                                .param("confirmEmail", "new@memorynotfound.com")
                                .param("password", "xjD1!3djk4")
                                .param("confirmPassword", "xjD1!3djk4")
                                .param("terms", "on")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/registration?success"))
                .andExpect(status().is3xxRedirection());
    }

}

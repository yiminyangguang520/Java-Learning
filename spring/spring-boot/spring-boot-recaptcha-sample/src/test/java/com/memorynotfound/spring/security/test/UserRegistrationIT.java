package com.memorynotfound.spring.security.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.memorynotfound.spring.security.recaptcha.ReCaptchaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ReCaptchaService reCaptchaService;

  @Test
  public void submitWithoutReCaptcha() throws Exception {
    this.mockMvc
        .perform(
            post("/forgot-password")
                .param("email", "new@memorynotfound.com")
        )
        .andExpect(model().hasErrors())
        .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
        .andExpect(status().isOk());
  }

  @Test
  public void submitWithInvalidReCaptcha() throws Exception {
    String invalidReCaptcha = "invalid-re-captcha";
    when(reCaptchaService.validate(invalidReCaptcha)).thenReturn(false);
    this.mockMvc
        .perform(
            post("/forgot-password")
                .param("email", "new@memorynotfound.com")
                .param("reCaptchaResponse", invalidReCaptcha)
        )
        .andExpect(model().hasErrors())
        .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
        .andExpect(status().isOk());
  }

  @Test
  public void submitWithValidReCaptcha() throws Exception {
    String validReCaptcha = "valid-re-captcha";
    when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
    this.mockMvc
        .perform(
            post("/forgot-password")
                .param("email", "new@memorynotfound.com")
                .param("reCaptchaResponse", validReCaptcha)
        )
        .andExpect(model().hasNoErrors())
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/forgot-password?success"));
  }

}

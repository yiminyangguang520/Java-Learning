package com.packtpub.springsecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * https://docs.spring.io/spring-security/site/docs/current/reference/html/test-method.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalendarApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  @WithAnonymousUser
  public void securityEnabled() throws Exception {
    mvc
        .perform(get("/admin/h2")
            .header("X-Requested-With", "XMLHttpRequest")
        )
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void chapterXX_XX__failed_Login() throws Exception {
    mvc.perform(post("/login")
            .accept(MediaType.TEXT_HTML)
            .contentType(
                MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "bob@example.com")
            .param("password", "bob1")
//                .with(csrf())
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login/form?error"))
        .andDo(print())
    ;
  }


  @Test
  public void test_login_user1() throws Exception {
    mvc.perform(post("/login")
        .accept(MediaType.TEXT_HTML)
        .contentType(
            MediaType.APPLICATION_FORM_URLENCODED)
        .param("username", "user1@example.com")
        .param("password", "user1")
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/default"))
    ;
  }

  @Test
  public void test_login_admin1() throws Exception {
    mvc.perform(post("/login")
            .accept(MediaType.TEXT_HTML)
            .contentType(
                MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "admin1@example.com")
            .param("password", "admin1")
//                .with(csrf())
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/default"))
        .andDo(print())
    ;
  }


  @Test
  @WithAnonymousUser
  public void test_events_WithAnonymousUser() throws Exception {
    mvc.perform(get("/events/"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("http://localhost/login/form"))
//                .andExpect(redirectedUrlPattern("/login/form"))
    ;
  }

  @Test
  @WithMockUser
  public void test_events_WithMockUser() throws Exception {
    mvc.perform(get("/events/"))
        .andExpect(status().is4xxClientError())
//                .andExpect(view().name("login"))
//                .andExpect(content().string(Matchers.containsString("Spring Framework Guru")))
    ;
  }

} // The End...

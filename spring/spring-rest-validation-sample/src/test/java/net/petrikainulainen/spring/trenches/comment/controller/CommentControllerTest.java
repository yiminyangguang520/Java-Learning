package net.petrikainulainen.spring.trenches.comment.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;
import net.petrikainulainen.spring.trenches.UnitTestUtil;
import net.petrikainulainen.spring.trenches.comment.dto.CommentDTO;
import net.petrikainulainen.spring.trenches.config.ExampleApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author Petri Kainulainen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExampleApplicationContext.class})
//@ContextConfiguration(locations = {"classpath:exampleApplicationContext.xml"})
@WebAppConfiguration
@ActiveProfiles("webapp")
public class CommentControllerTest {

  private static final String COMMENT_TEXT = "comment";

  @Resource
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void add_CommentTextIsEmpty_ShouldReturnValidationError() throws Exception {
    CommentDTO added = new CommentDTO();
    mockMvc.perform(post("/api/comment")
        .contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
        .content(UnitTestUtil.convertObjectToJsonBytes(added))
    )
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
        .andExpect(jsonPath("$.fieldErrors[0].field", is("text")))
        .andExpect(jsonPath("$.fieldErrors[0].message", is("Text cannot be empty.")));
  }

  @Test
  public void add_CommentTextIsTooLong_ShouldReturnValidationError() throws Exception {
    String added = UnitTestUtil.createStringWithLength(141);
    CommentDTO expected = createComment(added);
    mockMvc.perform(post("/api/comment")
        .contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
        .content(UnitTestUtil.convertObjectToJsonBytes(expected))
    )
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
        .andExpect(jsonPath("$.fieldErrors[0].field", is("text")))
        .andExpect(jsonPath("$.fieldErrors[0].message", is("The maximum length of text is 140 characters.")));
  }

  @Test
  public void add_ValidInformationSet_ShouldReturnAddedComment() throws Exception {
    CommentDTO added = createComment(COMMENT_TEXT);

    mockMvc.perform(post("/api/comment")
        .contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
        .content(UnitTestUtil.convertObjectToJsonBytes(added))
    )
        .andExpect(status().isOk())
        .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.text", is(COMMENT_TEXT)));
  }

  private CommentDTO createComment(String text) {
    CommentDTO comment = new CommentDTO();
    comment.setText(text);
    return comment;
  }
}

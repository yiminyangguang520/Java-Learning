package net.petrikainulainen.spring.trenches.comment.controller;

import javax.validation.Valid;
import net.petrikainulainen.spring.trenches.comment.dto.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Petri Kainulainen
 */
@Controller
public class CommentController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

  @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
  @ResponseBody
  public CommentDTO add(@Valid @RequestBody CommentDTO comment) {
    LOGGER.debug("Received comment: {}", comment);
    return comment;
  }
}

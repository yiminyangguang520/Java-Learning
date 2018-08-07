package com.example.polls.controller;

import com.example.polls.model.Poll;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.PagedResponse;
import com.example.polls.payload.PollRequest;
import com.example.polls.payload.PollResponse;
import com.example.polls.payload.VoteRequest;
import com.example.polls.repository.PollRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.VoteRepository;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.PollService;
import com.example.polls.util.AppConstants;
import java.net.URI;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author rajeevkumarsingh
 * @date 20/11/17
 */

@RestController
@RequestMapping("/api/polls")
public class PollController {

  @Autowired
  private PollRepository pollRepository;

  @Autowired
  private VoteRepository voteRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PollService pollService;

  private static final Logger logger = LoggerFactory.getLogger(PollController.class);

  @GetMapping
  public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return pollService.getAllPolls(currentUser, page, size);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
    Poll poll = pollService.createPoll(pollRequest);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{pollId}")
        .buildAndExpand(poll.getId()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "Poll Created Successfully"));
  }


  @GetMapping("/{pollId}")
  public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
      @PathVariable Long pollId) {
    return pollService.getPollById(pollId, currentUser);
  }

  @PostMapping("/{pollId}/votes")
  @PreAuthorize("hasRole('USER')")
  public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
      @PathVariable Long pollId,
      @Valid @RequestBody VoteRequest voteRequest) {
    return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
  }

}

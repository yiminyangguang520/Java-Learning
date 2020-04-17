package com.demo.rabbitmq.controller;

import com.demo.rabbitmq.dto.TxEvent;
import com.demo.rabbitmq.service.EventService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class EventController {

  private final static Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventService eventService;

  @RequestMapping(value = "/manage", method = RequestMethod.POST)
  public Boolean manageBookingSlot(HttpServletRequest request, HttpServletResponse response,
      @RequestBody TxEvent txEvent) {

    LOGGER.info("manage slot request received = {} ", txEvent);
    return eventService.sendEvent(txEvent);
  }

}

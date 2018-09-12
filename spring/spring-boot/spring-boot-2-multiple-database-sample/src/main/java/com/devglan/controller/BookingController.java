package com.devglan.controller;

import com.devglan.model.Booking;
import com.devglan.service.BookingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author litz-a
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

  @Autowired
  private BookingService bookingService;

  @RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
  public ResponseEntity<List<Booking>> findUserBookings(@PathVariable(name = "email", value = "email") String email) {

    List<Booking> bookings = bookingService.findUserBookings(email);
    return new ResponseEntity<>(bookings, HttpStatus.OK);
  }

}

/**
 *
 */
package com.devglan.service.impl;

import com.devglan.booking.dao.BookingDao;
import com.devglan.model.Booking;
import com.devglan.model.UserDetails;
import com.devglan.service.BookingService;
import com.devglan.user.dao.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class BookingServiceImpl implements BookingService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private BookingDao bookingDao;

  @Override
  public List<Booking> findUserBookings(String emailId) {
    UserDetails userdetails = userDao.findByEmail(emailId);
    List<Booking> bookings = bookingDao.findByCreatedBy(userdetails.getId());
    return bookings;
  }
}

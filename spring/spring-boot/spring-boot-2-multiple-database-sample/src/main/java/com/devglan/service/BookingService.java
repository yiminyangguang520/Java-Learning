/**
 *
 */
package com.devglan.service;

import com.devglan.model.Booking;
import java.util.List;

/**
 * @author min
 */
public interface BookingService {

  /**
   * findUserBookings
   * @param emailId
   * @return
   */
  List<Booking> findUserBookings(String emailId);
}

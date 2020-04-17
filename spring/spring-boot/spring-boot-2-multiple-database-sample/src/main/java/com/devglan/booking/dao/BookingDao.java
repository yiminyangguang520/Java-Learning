package com.devglan.booking.dao;

import com.devglan.model.Booking;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author min
 */
public interface BookingDao extends CrudRepository<Booking, Long> {

  /**
   * findByCreatedBy
   * @param userId
   * @return
   */
  List<Booking> findByCreatedBy(Long userId);

}

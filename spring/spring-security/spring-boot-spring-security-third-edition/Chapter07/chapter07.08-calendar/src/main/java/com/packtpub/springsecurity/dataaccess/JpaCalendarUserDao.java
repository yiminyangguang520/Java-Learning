package com.packtpub.springsecurity.dataaccess;

import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.domain.Role;
import com.packtpub.springsecurity.repository.CalendarUserRepository;
import com.packtpub.springsecurity.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * A jdbc implementation of {@link CalendarUserDao}.
 *
 * @author Rob Winch
 * @author Mick Knutson
 */
@Repository
public class JpaCalendarUserDao implements CalendarUserDao {

  private static final Logger logger = LoggerFactory
    .getLogger(JpaCalendarUserDao.class);

  // --- members ---

  private CalendarUserRepository calendarUserRepository;
  private RoleRepository roleRepository;

  // --- constructors ---

  @Autowired
  public JpaCalendarUserDao(final CalendarUserRepository calendarUserRepository,
    final RoleRepository roleRepository) {
    if (calendarUserRepository == null) {
      throw new IllegalArgumentException("calendatUserRepository cannot be null");
    }
    if (roleRepository == null) {
      throw new IllegalArgumentException("roleRepository cannot be null");
    }

    this.calendarUserRepository = calendarUserRepository;
    this.roleRepository = roleRepository;
  }

  // --- CalendarUserDao methods ---

  @Override
  @Transactional(readOnly = true)
  public CalendarUser getUser(final int id) {
    return calendarUserRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(readOnly = true)
  public CalendarUser findUserByEmail(final String email) {
    if (email == null) {
      throw new IllegalArgumentException("email cannot be null");
    }
    try {
      return calendarUserRepository.findByEmail(email);
    } catch (EmptyResultDataAccessException notFound) {
      return null;
    }
  }

  // TODO FIXME Need to add a LIKE clause
  @Override
  @Transactional(readOnly = true)
  public List<CalendarUser> findUsersByEmail(final String email) {
    if (email == null) {
      throw new IllegalArgumentException("email cannot be null");
    }
    if ("".equals(email)) {
      throw new IllegalArgumentException("email cannot be empty string");
    }
    return calendarUserRepository.findAll();
  }

  @Override
  public int createUser(final CalendarUser userToAdd) {
    if (userToAdd == null) {
      throw new IllegalArgumentException("userToAdd cannot be null");
    }
    if (userToAdd.getId() != null) {
      throw new IllegalArgumentException("userToAdd.getId() must be null when creating a " + CalendarUser.class.getName());
    }

    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findById(0).orElse(null));
    userToAdd.setRoles(roles);

    CalendarUser result = calendarUserRepository.save(userToAdd);
    calendarUserRepository.flush();

    return result.getId();
  }

} // The End...

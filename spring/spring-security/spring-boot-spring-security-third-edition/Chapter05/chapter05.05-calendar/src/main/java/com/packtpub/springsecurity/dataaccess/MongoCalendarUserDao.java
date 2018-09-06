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
public class MongoCalendarUserDao implements CalendarUserDao {

  private static final Logger logger = LoggerFactory.getLogger(MongoCalendarUserDao.class);

  // --- members ----------------------------------------------------------//

  private CalendarUserRepository repository;
  private RoleRepository roleRepository;

  // --- constructors -----------------------------------------------------//

  @Autowired
  public MongoCalendarUserDao(final CalendarUserRepository repository,
      final RoleRepository roleRepository) {
    if (repository == null) {
      throw new IllegalArgumentException("repository cannot be null");
    }
    if (roleRepository == null) {
      throw new IllegalArgumentException("roleRepository cannot be null");
    }

    this.repository = repository;
    this.roleRepository = roleRepository;
  }

  // --- CalendarUserDao methods ---

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CalendarUser getUser(final int id) {
    return repository.findById(id).get();
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public CalendarUser findUserByEmail(final String email) {
    if (email == null) {
      throw new IllegalArgumentException("email cannot be null");
    }
    try {
      return repository.findByEmail(email);
    } catch (EmptyResultDataAccessException notFound) {
      return null;
    }
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<CalendarUser> findUsersByEmail(final String email) {
    if (email == null) {
      throw new IllegalArgumentException("email cannot be null");
    }
    if ("".equals(email)) {
      throw new IllegalArgumentException("email cannot be empty string");
    }
    return repository.findAll();
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
    roles.add(roleRepository.findById(0).get());
    userToAdd.setRoles(roles);

    CalendarUser result = repository.save(userToAdd);

    return result.getId();
  }

} // The End...

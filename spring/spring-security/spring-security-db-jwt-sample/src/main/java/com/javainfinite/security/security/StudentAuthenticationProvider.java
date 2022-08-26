package com.javainfinite.security.security;

import com.javainfinite.security.model.Student;
import com.javainfinite.security.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthenticationProvider implements AuthenticationProvider {

  Logger logger = LoggerFactory.getLogger(StudentAuthenticationProvider.class);

  private StudentRepository repository;

  private PasswordEncoder encoder;

  public StudentAuthenticationProvider(StudentRepository repository, PasswordEncoder encoder) {
    this.encoder = encoder;
    this.repository = repository;
  }

  /**
   * Get the username and password from authentication object and validate with password encoders matching method
   *
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    Student student = repository.findBySname(username);
    if (student == null) {
      throw new BadCredentialsException("Details not found");
    }

    if (encoder.matches(password, student.getPassword())) {
      logger.info("Successfully Authenticated the user");
      return new UsernamePasswordAuthenticationToken(username, password, getStudentRoles(student.getSrole()));
    } else {
      throw new BadCredentialsException("Password mismatch");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  public List<GrantedAuthority> getStudentRoles(String studentRoles) {
    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
    String[] roles = studentRoles.split(",");
    for (String role : roles) {
      grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
    }
    return grantedAuthorityList;
  }
}

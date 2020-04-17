package com.packtpub.springsecurity.web.access.intercept;

import static java.util.stream.Collectors.toList;

import com.packtpub.springsecurity.domain.SecurityFilterMetadata;
import com.packtpub.springsecurity.repository.SecurityFilterMetadataRepository;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository("requestConfigMappingService")
public class JpaRequestConfigMappingService implements RequestConfigMappingService {

  @Autowired
  private SecurityFilterMetadataRepository securityFilterMetadataRepository;

  @Autowired
  public JpaRequestConfigMappingService(final SecurityFilterMetadataRepository securityFilterMetadataRepository) {
    if (securityFilterMetadataRepository == null) {
      throw new IllegalArgumentException("securityFilterMetadataRepository cannot be null");
    }
    this.securityFilterMetadataRepository = securityFilterMetadataRepository;
  }

  /**
   * .antMatchers("/admin/h2/**").permitAll() .antMatchers("/").permitAll() .antMatchers("/login/*").permitAll() .antMatchers("/logout").permitAll()
   * .antMatchers("/signup/*").permitAll() .antMatchers("/errors/**").permitAll() .antMatchers("/admin/**").access("hasRole('ADMIN') and isFullyAuthenticated()")
   * .antMatchers("/events/").hasRole("ADMIN") .antMatchers("/**").hasRole("USER");
   */
  @Override
  public List<RequestConfigMapping> getRequestConfigMappings() {
    List<RequestConfigMapping> rcm = securityFilterMetadataRepository
        .findAll()
        .stream()
        .sorted(Comparator.comparingInt(SecurityFilterMetadata::getSortOrder))
        .map(md -> new RequestConfigMapping(new AntPathRequestMatcher(md.getAntPattern()), new SecurityConfig(md.getExpression())))
        .collect(toList());
    return rcm;
  }

}

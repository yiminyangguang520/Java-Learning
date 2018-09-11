package com.packtpub.springsecurity.authentication;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import com.packtpub.springsecurity.core.authority.CalendarUserAuthorityUtils;
import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;

/**
 * Social Authentication
 *
 * <pre>SocialAuthenticationUtils.authenticate(connection)</pre>
 * @author litz-a
 */
public class SocialAuthenticationUtils {

  public static void authenticate(Connection<?> connection) {

    CalendarUser user = createCalendarUserFromProvider(connection);

        /*UserProfile profile = connection.fetchUserProfile();

        CalendarUser user = new CalendarUser();

        if(profile.getEmail() != null){
            user.setEmail(profile.getEmail());
        }
        else if(profile.getUsername() != null){
            user.setEmail(profile.getUsername());
        }
        else {
            user.setEmail(connection.getDisplayName());
        }

        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());*/

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, CalendarUserAuthorityUtils.createAuthorities(user));

    SecurityContextHolder.getContext().setAuthentication(authentication);

  }

  public static CalendarUser createCalendarUserFromProvider(Connection<?> connection) {
    // TODO: FIXME: Need to put this into a Utility:
    UserProfile profile = connection.fetchUserProfile();

    CalendarUser user = new CalendarUser();

    if (profile.getEmail() != null) {
      user.setEmail(profile.getEmail());
    } else if (profile.getUsername() != null) {
      user.setEmail(profile.getUsername());
    } else {
      user.setEmail(connection.getDisplayName());
    }

    user.setFirstName(profile.getFirstName());
    user.setLastName(profile.getLastName());

    user.setPassword(randomAlphabetic(32));

    return user;

  }

}

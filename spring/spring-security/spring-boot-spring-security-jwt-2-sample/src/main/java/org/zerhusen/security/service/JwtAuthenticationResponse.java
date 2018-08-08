package org.zerhusen.security.service;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author stephan
 * @date 20.03.16
 */
@Getter
@RequiredArgsConstructor
public class JwtAuthenticationResponse implements Serializable {

  private static final long serialVersionUID = 1250166508152483573L;

  private final String token;
}

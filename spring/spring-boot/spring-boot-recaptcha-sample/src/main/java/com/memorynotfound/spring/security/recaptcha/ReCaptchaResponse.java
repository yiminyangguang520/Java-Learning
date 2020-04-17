package com.memorynotfound.spring.security.recaptcha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author min
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "success",
    "challenge_ts",
    "hostname",
    "error-codes"
})
public class ReCaptchaResponse {

  @JsonProperty("success")
  private boolean success;

  @JsonProperty("challenge_ts")
  private Date challengeTs;

  @JsonProperty("hostname")
  private String hostname;

  @JsonProperty("error-codes")
  private ErrorCode[] errorCodes;

  @JsonIgnore
  public boolean hasClientError() {
    ErrorCode[] errors = getErrorCodes();
    if (errors == null) {
      return false;
    }
    for (ErrorCode error : errors) {
      switch (error) {
        case InvalidResponse:
        case MissingResponse:
          return true;
      }
    }
    return false;
  }

  static enum ErrorCode {
    MissingSecret, InvalidSecret,
    MissingResponse, InvalidResponse;

    private static Map<String, ErrorCode> errorsMap = new HashMap<>(4);

    static {
      errorsMap.put("missing-input-secret", MissingSecret);
      errorsMap.put("invalid-input-secret", InvalidSecret);
      errorsMap.put("missing-input-response", MissingResponse);
      errorsMap.put("invalid-input-response", InvalidResponse);
    }

    @JsonCreator
    public static ErrorCode forValue(String value) {
      return errorsMap.get(value.toLowerCase());
    }
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Date getChallengeTs() {
    return challengeTs;
  }

  public void setChallengeTs(Date challengeTs) {
    this.challengeTs = challengeTs;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public ErrorCode[] getErrorCodes() {
    return errorCodes;
  }

  public void setErrorCodes(ErrorCode[] errorCodes) {
    this.errorCodes = errorCodes;
  }
}

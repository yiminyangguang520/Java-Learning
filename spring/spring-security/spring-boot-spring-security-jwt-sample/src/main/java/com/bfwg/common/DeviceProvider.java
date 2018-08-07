package com.bfwg.common;

import javax.servlet.http.HttpServletRequest;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author fanjin
 * @date 2017-10-16
 */
@Component
public class DeviceProvider {

  public Device getCurrentDevice(HttpServletRequest request) {
    return DeviceUtils.getCurrentDevice(request);
  }
}

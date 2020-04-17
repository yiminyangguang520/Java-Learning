package com.my.blog.website.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * ip工具类
 * @author min
 * Created by BlueT on 2017/3/9.
 */
public class IPKit {

  /**
   * @param request 请求
   * @return IP Address
   */
  public static String getIpAddrByRequest(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  /**
   * @return 本机IPSocketException
   */
  public static String getRealIp() throws SocketException {
    // 本地IP，如果没有配置外网IP则返回它
    String localip = null;
    // 外网IP
    String netip = null;

    Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
    InetAddress ip;
    // 是否找到外网IP
    boolean finded = false;
    while (netInterfaces.hasMoreElements() && !finded) {
      NetworkInterface ni = netInterfaces.nextElement();
      Enumeration<InetAddress> address = ni.getInetAddresses();
      while (address.hasMoreElements()) {
        ip = address.nextElement();
        // 外网IP
        if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
          netip = ip.getHostAddress();
          finded = true;
          break;
        } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
          // 内网IP
          localip = ip.getHostAddress();
        }
      }
    }

    if (netip != null && !"".equals(netip)) {
      return netip;
    } else {
      return localip;
    }
  }
}

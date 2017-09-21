/**   
 * Copyright © 2016 猪八戒网. All rights reserved.
 * 
 * @Title: IpUtil.java 
 * @Prject: mobile-wap-activity
 * @Package: com.zbj.mobile.activitywap.common.util 
 * @Description: 
 * @author: lidaming   
 * @date: 2016年7月30日 下午8:08:40 
 * @version: V1.0   
 */
package com.lhx.common.tool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName: IpUtil
 * @Description:
 * @author: lidaming
 * @date: 2016年7月30日 下午8:08:40
 */
public class IpUtil {
  public static Logger logger = LoggerFactory.getLogger(IpUtil.class);

  /**
   * 获取当前会话客户端IP地址
   * @return
   */
  public static String getSessionIpAddr() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();
    return getIpAddr(request);
  }
  /**
   * 获取ip地址
   * @param request
   * @return
   */
  public static String getIpAddr(HttpServletRequest request) {
    try {
      String ip = request.getHeader("X-Forwarded-For");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
      if (StringUtils.isEmpty(ip)) {
        ip = "127.0.0.1";
      }
      if (ip != null) {
        String[] ips = ip.split(",");
        if (ips.length > 1) {
          return ips[0];
        }
      }
      return ip;
    } catch (Exception e) {
    }
    return "127.0.0.1";
  }

}

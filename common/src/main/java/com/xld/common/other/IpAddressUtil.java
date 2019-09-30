package com.xld.common.other;


import javax.servlet.http.HttpServletRequest;

/**
 * IP地址操作工具类
 * @author xld
 * @date 2018-09-29
 */
public class IpAddressUtil {
	
    /** 
     * 获取访问用户的客户端IP（适用于公网与局域网)
     */  
    public static String getIpAddress(HttpServletRequest request){
        if (request == null) {  
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.indexOf(",") != -1){
                ip = ip.split(",")[0];
            }
        }

        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return StrUtil.trimToEmpty(ip);
    }
}

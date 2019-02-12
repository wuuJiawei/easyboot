package com.easyboot.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpServletUtils
 *
 * @name: HttpServletUtils
 * @author: 12093
 * @date: 2019/1/18
 * @time: 14:32
 */
public class HttpServletUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }

    public static HttpSession getHttpSession() {
        return getRequest().getSession();
    }

    public static Object getSessionAttribute(String var1) {
        return getHttpSession().getAttribute(var1);
    }

    public static Object getRequestAttribute(String var1) {
        return getRequest().getAttribute(var1);
    }

    /**
     * 获取所有请求参数
     *
     * @return 返回所有请求参数
     */
    protected Map<String, String> getAllParam() {
        HttpServletRequest request = getRequest();
        Map params = new HashMap<>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            String val = request.getParameter(paraName);
            params.put(paraName, val);
        }
        return params;
    }
}
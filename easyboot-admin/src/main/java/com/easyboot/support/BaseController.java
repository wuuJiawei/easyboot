package com.easyboot.support;

import com.easyboot.bean.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseController
 *
 * @name: BaseController
 * @author: 12093
 * @date: 2019/1/3
 * @time: 9:37
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取后台登录用户
     * @return
     */
    protected User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取后台登录用户id
     * @return
     */
    protected int getCurrentUserId(){
        return getCurrentUser().getUserId();
    }

    /**
     * 获取项目路径
     * @return
     */
    protected String basePath(){
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        return basePath;
    }

    /**
     * 获取所有请求参数
     *
     * @return 返回所有请求参数
     */
    protected Map<String, String> getAllParam() {
        Map params = new HashMap<>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            String val = request.getParameter(paraName);
            params.put(paraName, val);
        }
        return params;
    }

    /**
     * 获取请求参数
     *
     * @return 返回所有请求参数
     */
    protected Object getRequestParam(String key) {
        if (request != null) {
            return request.getParameter(key);
        }
        return null;
    }

    /**
     * 获得请求参数返回指定的强制转换对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> T getRequestParam(String key, Class<T> clazz) {
        Object obj = getRequestParam(key);
        if (obj != null) {
            return (T) obj;
        }
        return null;
    }

    /**
     * 获得请求参数返回字符串
     * @param key
     * @return
     */
    protected String getRequestParamString(String key) {
        Object obj = getRequestParam(key);
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

}
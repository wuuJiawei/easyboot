package com.easyboot.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * \* LoginFilter
 * \* @name: LoginFilter
 * \* @author: 12093
 * \* @date: 2018/8/7
 * \* @time: 17:07
 * \
 */
public class LoginFilter extends AuthorizationFilter {

    @Override
    public boolean preHandle(ServletRequest servletRequest, ServletResponse response) throws Exception {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Session session = SecurityUtils.getSubject().getSession();

        if (session.getAttribute("ctx") == null) {
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String path = request.getContextPath();
            String basePath = scheme + "://" + serverName + ":" + port + path;
            session.setAttribute("ctx", basePath);
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String url = httpServletRequest.getRequestURI();
        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            if (isAjax((HttpServletRequest) servletRequest)) {
                WebUtils.toHttp(servletResponse).sendError(401);
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
        String xHeader = request.getHeader("X-Requested-With");
        if (xHeader != null && xHeader.contains("XMLHttpRequest")) {
            return true;
        }
        return false;
    }
}
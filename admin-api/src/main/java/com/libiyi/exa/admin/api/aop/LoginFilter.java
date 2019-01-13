package com.libiyi.exa.admin.api.aop;


import com.libiyi.exa.common.common.RequestConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = httpServletRequest.getRequestURI();
        if(httpServletRequest.getSession().getAttribute(RequestConst.USER_INFO)==null || !url.contains("/login")){
            httpServletResponse.sendError(403," 未登录");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

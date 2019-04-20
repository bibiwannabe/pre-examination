package com.libiyi.exa.admin.api.aop;

import com.libiyi.exa.admin.api.util.RedisUtil;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeaderInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8088");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        if(session.getAttribute(RequestConst.USER_INFO)==null && (!url.contains("/login") && !url.contains("/register"))){
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("uuid")){
                        TRUserLoginInfo trUserLoginInfo = getTRUserLoginInfo(redisUtil.get(RequestConst.USER_UUID+cookie.getValue()));
                        if(trUserLoginInfo!=null) {
                            session.setAttribute(RequestConst.USER_INFO, trUserLoginInfo);
                        }
                    }
                }
            }
        }
        return true;
    }

    private TRUserLoginInfo getTRUserLoginInfo(Object userLoginInfo) {
        TRUserLoginInfo trUserLoginInfo = null;
        if(userLoginInfo instanceof TRUserLoginInfo) {
            trUserLoginInfo = (TRUserLoginInfo) userLoginInfo;
        }
        return trUserLoginInfo;
    }
}

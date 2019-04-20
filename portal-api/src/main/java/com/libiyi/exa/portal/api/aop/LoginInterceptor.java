package com.libiyi.exa.portal.api.aop;

import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import com.libiyi.exa.portal.api.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by bibi on 2019/4/20.
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        if(session.getAttribute(RequestConst.USER_INFO)==null && (!url.contains("/login") && !url.contains("/register"))){
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("sid")){
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

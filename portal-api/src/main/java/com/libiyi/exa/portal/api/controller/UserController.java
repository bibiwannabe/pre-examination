package com.libiyi.exa.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.AccountTypeEnum;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.param.*;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.common.util.IdUtil;
import com.libiyi.exa.portal.api.param.UserLoginCookieModel;
import com.libiyi.exa.portal.api.utils.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> userRegister(@RequestBody UserInfoParam userInfoParam) {
        TPUserRegisterInfo tpUserInfo = getTPUserRegisterInfo(userInfoParam);
        if(userInfoParam.getEmail() == "" || userInfoParam.getCode() == 0) {
            return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        try {
            TRResponse response = exaServerService.userRegister(tpUserInfo);
            if(response.getCode()!=CodeEnum.SUCCESS.getCode()){
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("用户注册出错了", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPUserRegisterInfo getTPUserRegisterInfo(UserInfoParam userInfoParam) {
        TPUserRegisterInfo tpUserInfo = new TPUserRegisterInfo();
        tpUserInfo.setPassword(userInfoParam.getPassword());
        tpUserInfo.setCode(userInfoParam.getCode());
        Integer code = AccountTypeEnum.STUDENT.getCode();
        if (code == null){
            logger.info("code为空");
        }
        tpUserInfo.setAccType(code);
        tpUserInfo.setEmail(userInfoParam.getEmail());
        return tpUserInfo;
    }

    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> sendEmailCode(@RequestBody SendCodeParam sendCodeParam){
        try {
            if(sendCodeParam.getEmail() == "") {
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
            TRResponse response = exaServerService.sendEmail(sendCodeParam.getEmail());
            if(response.getCode()!=CodeEnum.SUCCESS.getCode()){
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
        } catch (Throwable e) {
            logger.error(" 发送邮件出错了", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @GetMapping(value = "/checkEmailExist")
    @ResponseBody
    public Result<String> checkEmailExist(@RequestParam(value = "email") String email){
        try {
            if(!email.contains("@")) {
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
            TRResponse response = exaServerService.checkEmailExist(email);
            if(response.getCode()!=CodeEnum.SUCCESS.getCode()){
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("出错了", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<UserLoginCookieModel> userLogin(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request) {
        TPUserLoginInfo tpUserInfo = getTPUserLoginInfo(userLoginParam);
        TRUserLoginInfo trUserLoginInfo = new TRUserLoginInfo();
        if(userLoginParam.getEmail() == "" ) {
            return new Result.Builder<UserLoginCookieModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        try {
            trUserLoginInfo = exaServerService.userLogin(tpUserInfo);
        }catch (Throwable e) {
            logger.error("用户登录出错了：", e);
            return new Result.Builder<UserLoginCookieModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        if(trUserLoginInfo.getResponse().getCode()!=CodeEnum.SUCCESS.getCode()){
            return new Result.Builder<UserLoginCookieModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        UserLoginCookieModel userModel = getUserCookieModel(trUserLoginInfo);
        request.getSession().setAttribute(RequestConst.USER_INFO, trUserLoginInfo);
        return new Result.Builder<UserLoginCookieModel>(userModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private UserLoginCookieModel getUserCookieModel(TRUserLoginInfo trUserLoginInfo) {
        UserLoginCookieModel userCookieModel = new UserLoginCookieModel();
        userCookieModel.setAccType(trUserLoginInfo.getAccType());
        userCookieModel.setEmail(trUserLoginInfo.getEmail());
        userCookieModel.setId(trUserLoginInfo.getId());
        String sid = IdUtil.getUUID();
        userCookieModel.setSid(sid);
        redisUtil.set(RequestConst.USER_UUID + sid, trUserLoginInfo);
        return userCookieModel;
    }

    private UserModel getUserModel(TRUserLoginInfo trUserLoginInfo) {
        UserModel userModel = new UserModel();
        userModel.setAccType(trUserLoginInfo.getAccType());
        userModel.setEmail(trUserLoginInfo.getEmail());
        userModel.setId(trUserLoginInfo.getId());
        userModel.setName(trUserLoginInfo.getName());
        return userModel;
    }

    private TPUserLoginInfo getTPUserLoginInfo(UserLoginParam userLoginParam) {
        TPUserLoginInfo tpUserLoginInfo = new TPUserLoginInfo();
        tpUserLoginInfo.setEmail(userLoginParam.getEmail());
        tpUserLoginInfo.setPassword(userLoginParam.getPassword());
        return tpUserLoginInfo;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> userLogout(HttpSession session, HttpServletRequest request) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class) ;
        if(trUserLoginInfo == null) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        session.removeAttribute(RequestConst.USER_INFO);
        delUserInfo(request);
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private void delUserInfo(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sid")){
                    redisUtil.del(cookie.getValue()+RequestConst.USER_UUID);
                }
            }
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> userModifyName(@RequestBody UserModifyNameParam userModifyNameParam, HttpSession session, HttpServletRequest request) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        TRResponse trResponse;
        if(trUserLoginInfo == null) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        try {
            trResponse = exaServerService.updateUsername(userModifyNameParam.getName(), trUserLoginInfo.getId());
            trUserLoginInfo = exaServerService.getUserInfo(trUserLoginInfo.getId());
        }catch (Throwable e) {
            logger.error("修改用户昵称出错：", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        if(trResponse.getCode()!=CodeEnum.SUCCESS.getCode()){
            return new Result.Builder<String>().setCode(trResponse.getCode()).build();
        }
        session.setAttribute(RequestConst.USER_INFO, trUserLoginInfo);
        setUserInfo(request, trUserLoginInfo);
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }


    private void setUserInfo(HttpServletRequest request, TRUserLoginInfo trUserLoginInfo){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sid")){
                    redisUtil.set(cookie.getValue()+RequestConst.USER_UUID, trUserLoginInfo);
                }
            }
        }
    }

    @RequestMapping(value = "/myInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<UserModel> getLonginUserInfo(HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class) ;
        if(trUserLoginInfo == null) {
            return new Result.Builder<UserModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        UserModel userModel = getUserModel(trUserLoginInfo);
        return new Result.Builder<UserModel>(userModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }
}

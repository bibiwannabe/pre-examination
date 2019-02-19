package com.libiyi.exa.admin.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.admin.api.util.RedisUtil;
import com.libiyi.exa.common.common.AccountTypeEnum;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.param.SendCodeParam;
import com.libiyi.exa.common.param.UserInfoParam;
import com.libiyi.exa.common.param.UserLoginParam;
import com.libiyi.exa.common.param.UserModel;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.TPUserLoginInfo;
import com.libiyi.exa.common.thrift.TPUserRegisterInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
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
            logger.error("出错了", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPUserRegisterInfo getTPUserRegisterInfo(UserInfoParam userInfoParam) {
        TPUserRegisterInfo tpUserInfo = new TPUserRegisterInfo();
        tpUserInfo.setPassword(userInfoParam.getPassword());
        tpUserInfo.setCode(userInfoParam.getCode());
        Integer code = AccountTypeEnum.TEACHER.getCode();
        tpUserInfo.setAccType(code);
        tpUserInfo.setEmail(userInfoParam.getEmail());
        return tpUserInfo;
    }

    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> sendEmailCode(@RequestBody SendCodeParam sendCodeParam){
        try {
            if(sendCodeParam.getEmail() == "" || sendCodeParam.getEmail() == null) {
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
            TRResponse response = exaServerService.sendEmail(sendCodeParam.getEmail());
            if(response.getCode()!=CodeEnum.SUCCESS.getCode()){
                return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("出错了", e);
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
    public Result<UserModel> userLogin(@RequestBody UserLoginParam userLoginParam, HttpSession session) {
        TPUserLoginInfo tpUserInfo = getTPUserLoginInfo(userLoginParam);
        TRUserLoginInfo trUserLoginInfo = new TRUserLoginInfo();
        if(userLoginParam.getEmail() == "" ) {
            return new Result.Builder<UserModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        try {
            trUserLoginInfo = exaServerService.userLogin(tpUserInfo);
        }catch (Throwable e) {
            logger.error("用户登录出错：", e);
            return new Result.Builder<UserModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        if(trUserLoginInfo.getResponse().getCode()!=CodeEnum.SUCCESS.getCode()){
            return new Result.Builder<UserModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        if(trUserLoginInfo.getAccType()!=AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<UserModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        UserModel userModel = getUserModel(trUserLoginInfo);
        session.setAttribute(RequestConst.USER_INFO, trUserLoginInfo);
        logger.info("存储登录信息：{}", JSON.toJSONString(session.getAttribute(RequestConst.USER_INFO)));
        return new Result.Builder<UserModel>(userModel).setCode(CodeEnum.SUCCESS.getCode()).build();
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
    public Result<String> userLogout(HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class) ;
        if(trUserLoginInfo == null || trUserLoginInfo.getAccType()!= AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        session.removeAttribute(RequestConst.USER_INFO);
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @RequestMapping(value = "/myInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<UserModel> getLonginUserInfo(HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class) ;
        if(trUserLoginInfo == null || trUserLoginInfo.getAccType()!= AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<UserModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        UserModel userModel = getUserModel(trUserLoginInfo);
        return new Result.Builder<UserModel>(userModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @GetMapping(value = "/info/{userId}")
    @ResponseBody
    public Result<UserModel> getUserInfo(@PathVariable("userId") int userId) {
        TRUserLoginInfo trUserLoginInfo = null;
        try {
            trUserLoginInfo = exaServerService.getUserInfo(userId);
        }catch (Throwable e) {
            logger.error("获取用户信息失败");
            return new Result.Builder<UserModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        if(trUserLoginInfo.getResponse().getCode()!=CodeEnum.SUCCESS.getCode()){
            return new Result.Builder<UserModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        UserModel userModel = getUserModel(trUserLoginInfo);
        return new Result.Builder<UserModel>(userModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }
}

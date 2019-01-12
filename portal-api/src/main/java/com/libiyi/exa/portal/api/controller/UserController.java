package com.libiyi.exa.portal.api.controller;

import com.libiyi.exa.common.common.AccountTypeEnum;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.portal.api.common.Result;
import com.libiyi.exa.portal.api.param.SendCodeParam;
import com.libiyi.exa.portal.api.param.UserInfoParam;
import com.libiyi.exa.portal.api.param.UserLoginParam;
import com.libiyi.exa.portal.api.param.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private ExaServerService.Iface exaServerService;

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
    public Result<UserModel> userLogin(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request) {
        TPUserLoginInfo tpUserInfo = getTPUserLoginInfo(userLoginParam);
        TRUserLoginInfo trUserLoginInfo = new TRUserLoginInfo();
        if(userLoginParam.getEmail() == "" ) {
            return new Result.Builder<UserModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        try {
            trUserLoginInfo = exaServerService.userLogin(tpUserInfo);
        }catch (Throwable e) {
            logger.error("未知错误：", e);
            return new Result.Builder<UserModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        if(trUserLoginInfo.getResponse().getCode()!=CodeEnum.SUCCESS.getCode()){
            return new Result.Builder<UserModel>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        UserModel userModel = getUserModel(trUserLoginInfo);
        request.setAttribute(RequestConst.USER_INFO, trUserLoginInfo);
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

}

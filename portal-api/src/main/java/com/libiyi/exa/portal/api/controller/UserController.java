package com.libiyi.exa.portal.api.controller;

import com.libiyi.exa.common.common.AccountTypeEnum;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.TPUserInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.portal.api.common.Result;
import com.libiyi.exa.portal.api.param.UserInfoParam;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private ExaServerService.Iface exaServerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> userRegister(@RequestBody UserInfoParam userInfoParam) {
        TPUserInfo tpUserInfo = getTPUserInfo(userInfoParam);
        try {
            TRResponse response = exaServerService.userRegister(tpUserInfo);
        } catch (TException e) {
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPUserInfo getTPUserInfo(UserInfoParam userInfoParam) {
        TPUserInfo tpUserInfo = new TPUserInfo();
        tpUserInfo.setPassword(userInfoParam.getPassword());
        tpUserInfo.setAccType(AccountTypeEnum.STUDENT.getCode());
        return tpUserInfo;
    }
}

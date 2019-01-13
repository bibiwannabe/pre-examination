package com.libiyi.exa.admin.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.admin.api.param.AdminInfoModel;
import com.libiyi.exa.admin.api.param.AdminInfoParam;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.TPAdminInfo;
import com.libiyi.exa.common.thrift.TRAdminInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin_info")
public class AdminInfoController {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @PostMapping("/create")
    @ResponseBody
    public Result<String> createAdminInfo(@RequestBody AdminInfoParam adminInfoParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        logger.info("获取到登录信息：{}", JSON.toJSONString(object));
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class) ;
        if(trUserLoginInfo == null) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TPAdminInfo tpAdminInfo = getTPAdminInfo(adminInfoParam, userId);
        try {
            TRResponse trResponse = exaServerService.createAdminInfo(tpAdminInfo);
            if(trResponse.getCode()!=CodeEnum.SUCCESS.getCode()){
                return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e){
            logger.error("创建用户信息失败", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminInfo getTPAdminInfo(AdminInfoParam adminInfoParam, Integer userId) {
        TPAdminInfo tpAdminInfo = new TPAdminInfo();
        tpAdminInfo.setUserId(userId);
        tpAdminInfo.setSubject(adminInfoParam.getSubject());
        tpAdminInfo.setTeachYear(adminInfoParam.getTeachYear());
        tpAdminInfo.setTitle(adminInfoParam.getTitle());
        return tpAdminInfo;
    }

    @GetMapping("/")
    @ResponseBody
    public Result<AdminInfoModel> getAdminInfo(HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        logger.info("获取到登录信息：{}", JSON.toJSONString(object));
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class) ;
        TRAdminInfo trAdminInfo;
        if(trUserLoginInfo == null) {
            return new Result.Builder<AdminInfoModel>().setCode(CodeEnum.NO_LOGIN.getCode()).build();
        }
        try {
            trAdminInfo = exaServerService.getAdminInfo(trUserLoginInfo.getId());
            if(trAdminInfo.getResponse().getCode()!=CodeEnum.SUCCESS.getCode()){
                return new Result.Builder<AdminInfoModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e){
            logger.error("获取后台用户信息失败", e);
            return new Result.Builder<AdminInfoModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        AdminInfoModel adminInfoModel = getAdminInfoModel(trAdminInfo);
        return new Result.Builder<AdminInfoModel>(adminInfoModel).setCode(CodeEnum.SUCCESS.getCode()).build();

    }

    private AdminInfoModel getAdminInfoModel(TRAdminInfo trAdminInfo) {
        AdminInfoModel adminInfoModel = new AdminInfoModel();
        adminInfoModel.setId(trAdminInfo.getId());
        adminInfoModel.setUserId(trAdminInfo.getUserId());
        adminInfoModel.setSubject(trAdminInfo.getSubject());
        adminInfoModel.setTeachYear(trAdminInfo.getTeachYear());
        adminInfoModel.setTitle(trAdminInfo.getTitle());
        return adminInfoModel;
    }

}

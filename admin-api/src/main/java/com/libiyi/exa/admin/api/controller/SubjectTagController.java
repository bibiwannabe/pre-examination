package com.libiyi.exa.admin.api.controller;


import com.alibaba.fastjson.JSON;
import com.libiyi.exa.admin.api.param.SubjectModel;
import com.libiyi.exa.admin.api.param.SubjectTagParam;
import com.libiyi.exa.common.common.AccountTypeEnum;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.TRSubjectTagList;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import com.libiyi.exa.common.thrift.TSubjectTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/subject")
public class SubjectTagController {
    static Logger logger = LogManager.getLogger(SubjectTagController.class);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @PostMapping("/create")
    @ResponseBody
    public Result<String> createSubjectTag(@RequestBody SubjectTagParam subjectTagParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).build();
        }
        if (subjectTagParam.equals("")) {
            return new Result.Builder<String>().setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        }
        try {
            exaServerService.createSubjectTag(subjectTagParam.getSubjectName());
        } catch (Throwable e) {
            logger.error("创建科目失败", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<List<SubjectModel>> getSubjectList(HttpSession session) {
        TRSubjectTagList trSubjectTagList;
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<List<SubjectModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).build();
        }
        try {
            trSubjectTagList = exaServerService.getAllSubject();
        } catch (Throwable e) {
            logger.error("创建科目失败", e);
            return new Result.Builder<List<SubjectModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        List<SubjectModel> subjectModels = trSubjectTagList.getSubjectTagList().stream().map(this::getSubjectModel).collect(Collectors.toList());
        return new Result.Builder<List<SubjectModel>>(subjectModels).setCode(CodeEnum.SUCCESS.getCode()).build();
    }


    private SubjectModel getSubjectModel(TSubjectTag tSubjectTag) {
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setId(tSubjectTag.getId());
        subjectModel.setSubjectName(tSubjectTag.getSubjectName());
        return subjectModel;
    }

}

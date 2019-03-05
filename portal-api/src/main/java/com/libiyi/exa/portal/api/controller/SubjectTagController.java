package com.libiyi.exa.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.TRSubjectTagList;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import com.libiyi.exa.common.thrift.TSubjectTag;
import com.libiyi.exa.portal.api.param.SubjectModel;
import com.libiyi.exa.portal.api.utils.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;

    private static final String SUBJECT_TAG = "SUBJECT_TAG";


    @GetMapping("/list")
    @ResponseBody
    public Result<List<SubjectModel>> getSubjectList(HttpSession session) {
        TRSubjectTagList trSubjectTagList;
        List<TSubjectTag> tSubjectTagList;
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<List<SubjectModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).build();
        }
        if(!redisUtil.lGet(SUBJECT_TAG, 0, 1).isEmpty()) {
            tSubjectTagList = redisUtil.lGet(SUBJECT_TAG, 0 , -1).stream().map(this::getSubjectTag).collect(Collectors.toList());
            List<SubjectModel> subjectModels = tSubjectTagList.stream().map(this::getSubjectModel).collect(Collectors.toList());
            return new Result.Builder<List<SubjectModel>>(subjectModels).setCode(CodeEnum.SUCCESS.getCode()).build();
        }
        try {
            trSubjectTagList = exaServerService.getAllSubject();
        } catch (Throwable e) {
            logger.error("获取科目失败", e);
            return new Result.Builder<List<SubjectModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        List<SubjectModel> subjectModels = trSubjectTagList.getSubjectTagList().stream().map(this::getSubjectModel).collect(Collectors.toList());
        List<Object> objects = trSubjectTagList.getSubjectTagList().stream().map(this::getObject).collect(Collectors.toList());
        redisUtil.lSet(SUBJECT_TAG, objects);
        return new Result.Builder<List<SubjectModel>>(subjectModels).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private SubjectModel getSubjectModel(TSubjectTag tSubjectTag) {
        SubjectModel subjectModel = new SubjectModel();
        subjectModel.setId(tSubjectTag.getId());
        subjectModel.setSubjectName(tSubjectTag.getSubjectName());
        return subjectModel;
    }

    private TSubjectTag getSubjectTag(Object tSubjectTag) {
        TSubjectTag subjectTag = new TSubjectTag();
        if(tSubjectTag instanceof TSubjectTag) {
            subjectTag = (TSubjectTag) tSubjectTag;
        }
        return subjectTag;
    }

    private Object getObject(TSubjectTag tSubjectTag) {
        return (Object) tSubjectTag;
    }
}


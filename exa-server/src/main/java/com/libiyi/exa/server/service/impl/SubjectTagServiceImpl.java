package com.libiyi.exa.server.service.impl;

import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.thrift.TRSubjectTagList;
import com.libiyi.exa.common.thrift.TSubjectTag;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.server.dao.SubjectTagMapper;
import com.libiyi.exa.server.entity.SubjectTag;
import com.libiyi.exa.server.service.SubjectTagService;
import com.libiyi.exa.server.utils.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectTagServiceImpl implements SubjectTagService {
    static Logger logger = LogManager.getLogger(SubjectTagServiceImpl.class);


    @Autowired
    private SubjectTagMapper subjectTagMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String SUBJECT_TAG = "SUBJECT_TAG";

    /**
     * 创建科目
     * @param tagName
     * @return
     */
    @Override
    public TRResponse createSubjectTag(String tagName) {
        TRResponse trResponse = new TRResponse();

        SubjectTag subjectTag = getSubjectTag(tagName);
        try {
            subjectTagMapper.createSubjectTag(subjectTag);
        } catch (Exception e){
            logger.error("插入科目失败", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
            return trResponse;
        }
        redisUtil.del(SUBJECT_TAG);
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    private SubjectTag getSubjectTag(String tagName) {
        SubjectTag subjectTag = new SubjectTag();
        subjectTag.setSubjectName(tagName);
        subjectTag.setCreateTime(DateUtil.getNow());
        subjectTag.setUpdateTime(DateUtil.getNow());
        return subjectTag;
    }

    /**
     * 获取所有科目
     * @return
     */
    @Override
    public TRSubjectTagList getAllSubject() {
        TRResponse trResponse = new TRResponse();
        List<SubjectTag> subjectTagList = subjectTagMapper.getAll();
        TRSubjectTagList trSubjectTagList = getTRSubjectTagList(subjectTagList);
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trSubjectTagList.setResponse(trResponse);
        return trSubjectTagList;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public SubjectTag getById(Integer id) {
        return subjectTagMapper.getById(id);
    }

    private TRSubjectTagList getTRSubjectTagList(List<SubjectTag> subjectTagList) {
        TRSubjectTagList trSubjectTagList = new TRSubjectTagList();
        List<TSubjectTag> tSubjectTagList = subjectTagList.stream().map(this::getTSubjectTag).collect(Collectors.toList());
        trSubjectTagList.setSubjectTagList(tSubjectTagList);
        return trSubjectTagList;
    }
    
    private TSubjectTag getTSubjectTag(SubjectTag subjectTag) {
        TSubjectTag tSubjectTag = new TSubjectTag();
        tSubjectTag.setId(subjectTag.getId());
        tSubjectTag.setSubjectName(subjectTag.getSubjectName());
        return tSubjectTag;
    }


}

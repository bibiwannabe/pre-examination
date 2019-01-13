package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TPAdminInfo;
import com.libiyi.exa.common.thrift.TRAdminInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.server.dao.AdminInfoMapper;
import com.libiyi.exa.server.entity.AdminInfo;
import com.libiyi.exa.server.service.AdminInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    static Logger logger = LogManager.getLogger(AdminInfoServiceImpl.class);


    @Autowired
    private AdminInfoMapper adminInfoMapper;


    /**
     * 创建后台用户信息
     * @param tpAdminInfo
     * @return
     */
    @Override
    public TRResponse createAdminInfo(TPAdminInfo tpAdminInfo) {
        TRResponse trResponse = new TRResponse();
        AdminInfo adminInfo = getAdminInfoByTPAdminInfo(tpAdminInfo);
        logger.info("adminInfo:{}, tpAdminInfo:{}", JSON.toJSONString(adminInfo), JSON.toJSONString(tpAdminInfo));
        try{
            adminInfoMapper.createAdminInfo(adminInfo);
        } catch (Exception e) {
            logger.error(" 创建后台用户信息失败：adminInfo:{}", JSON.toJSONString(adminInfo), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    private AdminInfo getAdminInfoByTPAdminInfo(TPAdminInfo tpAdminInfo) {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUserId(tpAdminInfo.getUserId());
        adminInfo.setCreateTime(DateUtil.getNow());
        adminInfo.setUpdateTime(DateUtil.getNow());
        adminInfo.setSubject(tpAdminInfo.getSubject()==null?"":tpAdminInfo.getSubject());
        adminInfo.setTitle(tpAdminInfo.getTitle()==null?"":tpAdminInfo.getTitle());
        adminInfo.setTeachYear(tpAdminInfo.getTeachYear());
        return adminInfo;
    }

    /**
     * 获取后台用户信息
     * @return
     */
    @Override
    public TRAdminInfo getAdminInfo(Integer userId) {
        TRAdminInfo trAdminInfo = new TRAdminInfo();
        TRResponse trResponse = new TRResponse();
        AdminInfo adminInfo = adminInfoMapper.getByUserId(userId);
        if(adminInfo == null) {
            logger.info("用户信息不存在：userId:{}", userId);
            trResponse.setCode(CodeEnum.SUCCESS.getCode());
            trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
            trAdminInfo.setResponse(trResponse);
            return trAdminInfo;
        }
        trAdminInfo = getTrAdminInfo(adminInfo);
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        trAdminInfo.setResponse(trResponse);
        return trAdminInfo;
    }

    private TRAdminInfo getTrAdminInfo(AdminInfo adminInfo) {
        TRAdminInfo trAdminInfo = new TRAdminInfo();
        trAdminInfo.setId(adminInfo.getId());
        trAdminInfo.setSubject(adminInfo.getSubject());
        trAdminInfo.setUserId(adminInfo.getUserId());
        trAdminInfo.setTitle(adminInfo.getTitle());
        trAdminInfo.setTeachYear(adminInfo.getTeachYear());
        return trAdminInfo;
    }
}

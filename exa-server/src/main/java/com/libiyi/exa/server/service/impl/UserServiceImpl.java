package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TPUserLoginInfo;
import com.libiyi.exa.common.thrift.TPUserRegisterInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.thrift.TRUserLoginInfo;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.common.util.PasswordUtil;
import com.libiyi.exa.server.dao.UserInfoMapper;
import com.libiyi.exa.server.entity.UserInfo;
import com.libiyi.exa.server.service.UserService;
import com.libiyi.exa.server.utils.CodeUtil;
import com.libiyi.exa.server.utils.EmailUtil;
import com.libiyi.exa.server.utils.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户注册
     *
     * @param tpUserInfo
     * @return
     */
    @Override
    public TRResponse userRegister(TPUserRegisterInfo tpUserInfo) {
        logger.info("UserServiceImpl.userRegister:{}", JSON.toJSONString(tpUserInfo));
        TRResponse trResponse = new TRResponse();
        if (!verifyCode(tpUserInfo)) {
            trResponse.setCode(CodeEnum.DATA_ILEAGLE.getCode());
            trResponse.setDesc(CodeEnum.DATA_ILEAGLE.getDesc());
            return trResponse;
        }
        UserInfo userInfo = getUserInfo(tpUserInfo);
        try {
            userInfoMapper.insert(userInfo);
        } catch (Exception e) {
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    private boolean verifyCode(TPUserRegisterInfo tpUserInfo) {
        String code = String.valueOf(tpUserInfo.getCode());
        String email = tpUserInfo.getEmail();
        if (redisUtil.get(email) == null || !redisUtil.get(email).toString().equals(code)) {
            logger.info("缓存中获取code：", JSON.toJSONString(redisUtil.get(email)));
            return false;
        }
        return true;
    }

    /**
     * 检查邮箱是否已注册
     *
     * @param email
     * @return
     */
    @Override
    public TRResponse checkEmailExist(String email) {
        TRResponse trResponse = new TRResponse();
        Integer count = userInfoMapper.getEmailNumCount(email);
        if (count > 0) {
            trResponse.setDesc(CodeEnum.DATA_ILEAGLE.getDesc());
            trResponse.setCode(CodeEnum.DATA_ILEAGLE.getCode());
            return trResponse;
        }
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        return trResponse;
    }

    private UserInfo getUserInfo(TPUserRegisterInfo tpUserInfo) {
        UserInfo userInfo = new UserInfo();
        String password = PasswordUtil.MD5EncodeUtf8(tpUserInfo.getPassword());
        userInfo.setPassword(password);
        userInfo.setEmail(tpUserInfo.getEmail());
        userInfo.setAccType(tpUserInfo.getAccType());
        userInfo.setCreateTime(DateUtil.getNow());
        userInfo.setUpdateTime(DateUtil.getNow());
        userInfo.setName(tpUserInfo.getEmail());
        return userInfo;
    }

    /**
     * 用户登录
     *
     * @param userLoginInfo
     * @return
     */
    @Override
    public TRUserLoginInfo userLogin(TPUserLoginInfo userLoginInfo) {
        return userCommonLogin(userLoginInfo);
    }

    private TRUserLoginInfo userCommonLogin(TPUserLoginInfo userLoginInfo) {
        logger.info("用户登录：userLoginInfo：{}", JSON.toJSONString(userLoginInfo));
        TRUserLoginInfo trUserLoginInfo = new TRUserLoginInfo();
        UserInfo userInfo = userInfoMapper.getByEmail(userLoginInfo.getEmail());
        if (userInfo == null || !userInfo.getPassword().equals(PasswordUtil.MD5EncodeUtf8(userLoginInfo.getPassword()))) {
            TRResponse trResponse = new TRResponse();
            trResponse.setCode(CodeEnum.DATA_ILEAGLE.getCode());
            trResponse.setDesc(CodeEnum.DATA_ILEAGLE.getDesc());
            trUserLoginInfo.setResponse(trResponse);
            return trUserLoginInfo;
        }
        trUserLoginInfo = getTrUserLoginInfo(userInfo);
        return trUserLoginInfo;
    }

    private TRUserLoginInfo getTrUserLoginInfo(UserInfo userInfo) {
        TRUserLoginInfo trUserLoginInfo = new TRUserLoginInfo();
        TRResponse trResponse = new TRResponse();
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        trUserLoginInfo.setResponse(trResponse);
        trUserLoginInfo.setAccType(userInfo.getAccType());
        trUserLoginInfo.setEmail(userInfo.getEmail());
        trUserLoginInfo.setName(userInfo.getName());
        trUserLoginInfo.setId(userInfo.getId());
        return trUserLoginInfo;
    }

    /**
     * 发邮件
     *
     * @param email
     * @return
     */
    @Override
    public TRResponse sendEmail(String email) {
        TRResponse trResponse = new TRResponse();
        int code = CodeUtil.getCode();
        try {
            redisUtil.set(email, code, 300);
            logger.info(" 发送验证码：{}到邮箱:{}", code, email);
            EmailUtil.sendCode(code, email);
        } catch (Exception e) {
            logger.error("发送验证码失败，email：{} ，code:{}", email, code, e);
            logger.info("我就不信这个错打不出来" + e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public TRUserLoginInfo getUserInfo(Integer userId) {
        TRUserLoginInfo trUserLoginInfo = new TRUserLoginInfo();
        UserInfo userInfo = userInfoMapper.getById(userId);
        if (userInfo == null) {
            TRResponse trResponse = new TRResponse();
            trResponse.setCode(CodeEnum.DATA_ILEAGLE.getCode());
            trResponse.setDesc(CodeEnum.DATA_ILEAGLE.getDesc());
            trUserLoginInfo.setResponse(trResponse);
            return trUserLoginInfo;
        }
        trUserLoginInfo = getTrUserLoginInfo(userInfo);
        return trUserLoginInfo;
    }

    /**
     * 更改用户名
     * @param name
     * @param id
     * @return
     */
    @Override
    public TRResponse updateUsername(String name, Integer id) {
        TRResponse trResponse = new TRResponse();
        try{
            userInfoMapper.updateNameById(name, id);
        } catch (Exception e) {
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }
}

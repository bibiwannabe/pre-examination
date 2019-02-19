package com.libiyi.exa.server.thriftServer;

import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.server.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements ExaServerService.Iface {
    static Logger logger = LogManager.getLogger(ServerServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AdminInfoService adminInfoService;

    @Autowired
    private SubjectTagService subjectTagService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PortalPaperPaperService portalPaperPaperService;

    @Autowired
    private AdminPaperService adminPaperService;

    @Autowired
    private PortalWrongQuestionService portalWrongQuestionService;

    @Override
    public TRResponse userRegister(TPUserRegisterInfo userInfo) throws TException {
        return userService.userRegister(userInfo);
    }

    @Override
    public TRResponse checkEmailExist(String email) throws TException {
        return userService.checkEmailExist(email);
    }

    @Override
    public TRUserLoginInfo userLogin(TPUserLoginInfo userLoginInfo) throws TException {
        return userService.userLogin(userLoginInfo);
    }

    @Override
    public TRResponse sendEmail(String email) throws TException {
        return userService.sendEmail(email);
    }

    @Override
    public TRUserLoginInfo getUserInfo(int i) throws TException {
        return userService.getUserInfo(i);
    }

    @Override
    public TRPortalWrongQuestionInfoList getWrongQuestionBySubjectId(TPPortalWrongQuestionParam wrongQuestionParam) throws TException {
        return portalWrongQuestionService.getWrongQuestionBySubjectId(wrongQuestionParam);
    }

    @Override
    public TRPortalPaperInfoList getPaperListBySubjectId(TPPortalQueryPaperInfo queryPaperInfo) throws TException {
        return portalPaperPaperService.getPaperListBySubjectId(queryPaperInfo);
    }

    @Override
    public TRPortalPaperAndQuestionInfo portalGetPaperById(int id) throws TException {
        return portalPaperPaperService.portalGetPaperById(id);
    }

    @Override
    public TREvaluateResult getEvaluateResult(TPCreatPracticeRecordParam practiceParam) throws TException {
        return portalPaperPaperService.updateEvaluateResult(practiceParam);
    }

    @Override
    public TRPortalPaperInfoList getRecommendPaperList(int userId) throws TException {
        return portalPaperPaperService.getRecommendPaperList(userId);
    }

    @Override
    public TRResponse createAdminInfo(TPAdminInfo adminInfo) throws TException {
        return adminInfoService.createAdminInfo(adminInfo);
    }

    @Override
    public TRAdminInfo getAdminInfo(int userId) throws TException {
        return adminInfoService.getAdminInfo(userId);
    }

    @Override
    public TRResponse createSubjectTag(String tagName) throws TException {
        return subjectTagService.createSubjectTag(tagName);
    }

    @Override
    public TRSubjectTagList getAllSubject() throws TException {
        return subjectTagService.getAllSubject();
    }

    @Override
    public TRResponse addQuestion(TPAdminCreateQuestionInfo questionInfo) throws TException {
        return questionService.createQuestion(questionInfo);
    }

    @Override
    public TRAdminQuestionInfoList getQuestionListByParam(TPAdminQuerryQuestionInfo queryParam) throws TException {
        return questionService.getQuestionListByParam(queryParam);
    }

    @Override
    public TRResponse modifyQuestion(TPAdminModifyQuestionInfo modifyParam) throws TException {
        return questionService.modifyQuestion(modifyParam);
    }

    @Override
    public TRResponse createPaper(TPAdminCreatePaperInfo paperInfo) throws TException {
        return adminPaperService.createPaper(paperInfo);
    }

    @Override
    public TRResponse modifyPaper(TPAdminModifyPaperInfo modifyParam) throws TException {
        return adminPaperService.modifyPaper(modifyParam);
    }

    @Override
    public TRAdminPaperInfoList getPaperListByParam(TPAdminQueryPaperInfo queryParam) throws TException {
        return adminPaperService.getPaperListByParam(queryParam);
    }

    @Override
    public TRAdminPaperAndQuestionInfo getPaperById(int id) throws TException {
        return adminPaperService.getPaperById(id);
    }
}

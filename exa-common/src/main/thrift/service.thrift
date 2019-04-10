namespace java com.libiyi.exa.common.service
include "model.thrift"

service ExaServerService{

        /*用户公用模块*/
        model.TRResponse userRegister(1:model.TPUserRegisterInfo userInfo);

        model.TRResponse checkEmailExist(1:string email);

        model.TRUserLoginInfo userLogin(1:model.TPUserLoginInfo userLoginInfo);

        model.TRResponse sendEmail(1:string email);

        model.TRUserLoginInfo getUserInfo(1:i32 userId);

        model.TRResponse updateUsername(1:string name, 2:i32 id)

        /*用户获取题目模块*/
        //分页获取科目及错题数
        model.TRPraticeSubject getSubjectQuestionCount(1:model.TPUserIdAndPageParam param);
        //用户获取错题
        model.TRPortalWrongQuestionInfoList getWrongQuestionBySubjectId(1:model.TPPortalWrongQuestionParam worngQuestionParam);
        //用户获取试卷
        model.TRPortalPaperInfoList getPaperListBySubjectId(1:model.TPPortalQueryPaperInfo queryPaperInfo);
        //根据id获取试卷试题
        model.TRPortalPaperAndQuestionInfo portalGetPaperById(1:i32 id);
        //判卷
        model.TREvaluateResult getEvaluateResult(1:model.TPCreatPracticeRecordParam practiceParam);
        //获取推荐试卷
        model.TRPortalPaperInfoList getRecommendPaperList(1:i32 userId);
        //根据id获取做卷记录
        model.TREvaluateResult getEvaluateResultById(1:i32 id);
        //获取最近五次做题记录
        model.TRPracticeRecord getPracticeRecordByUserId(1: i32 userId);
        //获取最热门试卷
        model.TRPortalPaperInfoList getHotestPaperList();

        /*后台模块*/
        model.TRResponse createAdminInfo(1:model.TPAdminInfo adminInfo);

        model.TRAdminInfo getAdminInfo(1:i32 userId);

        model.TRResponse createSubjectTag(1:string tagName);

        model.TRSubjectTagList getAllSubject();
        //创建题目
        model.TRIdResult addQuestion(1:model.TPAdminCreateQuestionInfo questionInfo);
        //根据条件分页获取题目列表
        model.TRAdminQuestionInfoList getQuestionListByParam(1:model.TPAdminQuerryQuestionInfo querryParam);
        //根据关键词获取题目列表
        model.TRAdminQuestionSearchList getQuestionListBySearchKey(1:model.TPAdminSearchQuestionParam searchParam);
        //根据题目id获取题目详情
        model.TRAdminQuestionInfo getQuestionById(1:i32 id);
        //修改题目
        model.TRResponse modifyQuestion(1:model.TPAdminModifyQuestionInfo modifyParam);
        //创建试卷
        model.TRIdResult createPaper(1:model.TPAdminCreatePaperInfo paperInfo);
        //修改试卷
        model.TRResponse modifyPaper(1:model.TPAdminModifyPaperInfo modifyParam);
        //根据条件分页获取试卷列表
        model.TRAdminPaperInfoList getPaperListByParam(1:model.TPAdminQueryPaperInfo queryParam);
        //根据id获取试卷及题目
        model.TRAdminPaperAndQuestionInfo getPaperById(1:i32 id);
        //根据科目获取5个平均分最低的试卷
        model.TRAdminPaperInfoList getAvgLowestFive(1:i32 subjectId);
        //根据试卷获取错误率最高的5个试题
        model.TRPaperQuestionDataList getPaperQuestionDataList(1:i32 paperId);

}
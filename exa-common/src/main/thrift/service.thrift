namespace java com.libiyi.exa.common.service
include "model.thrift"

service ExaServerService{

        /*用户公用模块*/
        model.TRResponse userRegister(1:model.TPUserRegisterInfo userInfo);

        model.TRResponse checkEmailExist(1:string email);

        model.TRUserLoginInfo userLogin(1:model.TPUserLoginInfo userLoginInfo);

        model.TRResponse sendEmail(1:string email);

        /*用户获取题目模块*/

        //用户获取错题
        model.TRPortalWrongQuestionInfoList getWrongQuestionBySubjectId(1:model.TPPortalWrongQuestionParam worngQuestionParam);

        /*后台模块*/
        model.TRResponse createAdminInfo(1:model.TPAdminInfo adminInfo);

        model.TRAdminInfo getAdminInfo(1:i32 userId);

        model.TRResponse createSubjectTag(1:string tagName);

        model.TRSubjectTagList getAllSubject();
        //创建题目
        model.TRResponse addQuestion(1:model.TPAdminCreateQuestionInfo questionInfo);
        //根据条件分页获取题目列表
        model.TRAdminQuestionInfoList getQuestionListByParam(1:model.TPAdminQuerryQuestionInfo querryParam);
        //修改题目
        model.TRResponse modifyQuestion(1:model.TPAdminModifyQuestionInfo modifyParam);





}
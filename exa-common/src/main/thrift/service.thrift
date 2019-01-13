namespace java com.libiyi.exa.common.service
include "model.thrift"

service ExaServerService{

        //用户公用模块
        model.TRResponse userRegister(1:model.TPUserRegisterInfo userInfo);

        model.TRResponse checkEmailExist(1:string email);

        model.TRUserLoginInfo userLogin(1:model.TPUserLoginInfo userLoginInfo);

        model.TRResponse sendEmail(1:string email);

        //后台模块
        model.TRResponse createAdminInfo(1:model.TPAdminInfo adminInfo);

        model.TRAdminInfo getAdminInfo(1:i32 userId);

        model.TRResponse createSubjectTag(1:string tagName);

        model.TRSubjectTagList getAllSubject();

}
namespace java com.libiyi.exa.common.service
include "model.thrift"

service ExaServerService{

        model.TRResponse userRegister(1:model.TPUserRegisterInfo userInfo);

        model.TRResponse checkEmailExist(1:string email);

        model.TRUserLoginInfo userLogin(1:model.TPUserLoginInfo userLoginInfo);

        model.TRUserLoginInfo teacherLogin(1:model.TPUserLoginInfo userLoginInfo);

        model.TRResponse sendEmail(1:string email);

}
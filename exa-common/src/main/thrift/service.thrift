namespace java com.libiyi.exa.common.service
include "model.thrift"

service ExaServerService{
        model.TRResponse userRegister(1:model.TPUserInfo userInfo);
}
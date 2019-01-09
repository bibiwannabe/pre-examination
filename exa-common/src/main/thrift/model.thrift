namespace java com.libiyi.exa.common.thrift

struct TRResponse {
       1: i32 code;
       2: string desc;
}

struct TPUserInfo {
       1: string name;
       2: string email;
       3: i32 accType;
       4: string password;
}

struct TRUserLoginInfo {
        1: string name;
        2: string email;
        3: i32 accType;
        4: TRResponse response;
}

struct TPUserLoginInfo {
        1: string email;
        2: string password;
}

struct TPUserRegisterInfo {
        1: string email;
        2: i32 code;
        3: i32 accType;
        4: string password;
}
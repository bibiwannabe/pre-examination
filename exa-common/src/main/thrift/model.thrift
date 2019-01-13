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
        4: i32 id;
        5: TRResponse response;
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

struct TPAdminInfo {
        1: i32 userId;
        2: string title;
        3: string subject;
        4: i32 teachYear;
}

struct TRAdminInfo {
        1: i32 id;
        2: i32 userId;
        3: string title;
        4: string subject;
        5: i32 teachYear;
        6: TRResponse response;
}
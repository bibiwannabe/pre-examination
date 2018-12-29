namespace java com.libiyi.exa.common.thrift

struct TRResponse {
       1: i32 code;
       2: string desc;
}

struct TPUserInfo {
       1: string name;
       2: i64 phone;
       3: i32 accType;
       4: string password;
}
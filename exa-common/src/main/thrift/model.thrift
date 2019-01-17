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

struct TSubjectTag {
        1: i32 id;
        2: string subjectName;
}

struct TRSubjectTagList {
        1: list<TSubjectTag> subjectTagList;
        2: TRResponse response;
}

struct TPagination {
        1: i32 page;
        2: i32 size;
        3: i32 totalNum;
}

struct TRAdminQuestionInfoList {
        1: list<TAdminQuestionInfo> adminQuestionInfoList;
        2: TRResponse response;
        3: TPagination pagination;
}

struct TAdminQuestionInfo {
        1: i32 id;
        2: i32 subjectId;
        3: i32 type;
        4: string content;
        5: string options;
        6: string answer;
        7: i32 correctNum;
        8: i32 counts;
        9: i32 createUser;
        10: i64 createTime;
        11: i64 updateTime;
}

struct TPAdminQuerryQuestionInfo {
        1: i32 subjectId;
        2: i32 type;
        3: i32 querryType;
        4: TPagination pagination;
}

struct TPAdminCreateQuestionInfo {
        1: i32 subjectId;
        2: i32 type;
        3: string content;
        4: string options;
        5: string answer;
        6: i32 createUser;
}

struct TPAdminModifyQuestionInfo {
        1: i32 id;
        2: i32 subjectId;
        3: string content;
        4: string options;
        5: string answer;
}

struct TRPortalWrongQuestionInfoList {
        1: list<TPortalWrongQuestionInfo> questionInfoList;
        2: TRResponse response;
        3: TPagination pagination;
}

struct TPPortalWrongQuestionParam {
        1: i32 userId;
        2: i32 subjectId;
        3: TPagination pagination;
}

struct TPortalQuestionInfo {
        1: i32 id;
        2: i32 type;
        3: string content;
        4: string options;
        5: i32 subjectId;
}

struct TPortalWrongQuestionInfo {
        1: i32 id;
        2: i32 subjectId;
        3: i32 type;
        4: string content;
        5: string options;
        6: string answer;
        7: i32 wrongCounts;
}

struct TPaperInfo {
        1: i32 id;
        2: i32 subjectId;
        3: string name;
        4: string questionList;
        5: i32 avgPoints;
        6: i32 counts;
        7: i32 createUser;
        8: i64 createTime;
        9: i64 updateTime;
}

struct TPortalPaperInfo {
        1: i32 id;
        2: i32 subjectId;
        3: string name;
        4: string questionList;
}

struct TRAdminPaperInfoList {
        1: list<TPaperInfo> paperInfoList;
        2: TRResponse response;
        3: TPagination pagination;
}

struct TRAdminPaperInfo {
        1: TPaperInfo paperInfo;
        2: TRResponse response;
}

struct TPAdminCreatePaperInfo {
        1: i32 subjectId;
        2: string name;
        3: string questionList;
        4: i32 createUser;
}

struct TPAdminModifyPaperInfo {
        1: i32 subjectId;
        2: string name;
        3: string questionList;
        4: i32 id;
}

struct TPAdminQueryPaperInfo {
        1: i32 subjectId;
        2: i32 queryType;
        3: TPagination pagination;
}

struct TPPortalQueryPaperInfo {
        1: i32 subjectId;
        2: TPagination pagination;
}

struct TRPortalPaperInfoList {
        1: list<TPortalPaperInfo> paperInfoList;
        2: TRResponse response;
        3: TPagination pagination;
}

struct TRAdminPaperAndQuestionInfo {
        1: i32 id;
        2: i32 subjectId;
        3: string name;
        4: TQuestionPointsAndInfoList choice;
        5: TQuestionPointsAndInfoList selection;
        6: TQuestionPointsAndInfoList filling;
        7: i32 createUser;
        8: i32 avgPoints;
        9: i32 counts;
        10: i64 createTime;
        11: i64 updateTime;
        12: TRResponse response;
}

struct TQuestionPointsAndInfoList {
       1: list<TAdminQuestionInfo> questionInfoList;
       2: i32 point;
}

struct TRPortalPaperAndQuestionInfo {
        1: i32 id;
        2: i32 subjectId;
        3: string name;
        4: TPortalQuestionPointsAndInfoList choice;
        5: TPortalQuestionPointsAndInfoList selection;
        6: TPortalQuestionPointsAndInfoList filling;
        7: TRResponse response;
}

struct TPortalQuestionPointsAndInfoList {
        1: list<TPortalQuestionInfo> questionInfoList;
        2: i32 point;
}
package com.libiyi.exa.common.param

class UserInfoParam {
    var code = 0
    var password = ""
    var email = ""
}

class SendCodeParam {
    var email = ""
}

class UserLoginParam {
    var email = ""
    var password = ""
}

class UserModel {
    var name = ""
    var id = 0
    var email = ""
    var accType = 0
}

class UserModifyNameParam {
    var name = ""
}
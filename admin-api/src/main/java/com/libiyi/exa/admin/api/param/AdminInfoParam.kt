package com.libiyi.exa.admin.api.param

class AdminInfoParam {
    var title = ""
    var subject = ""
    var teachYear = 0
}

class AdminInfoModel {
    var id = 0
    var userId = 0
    var title = ""
    var subject = ""
    var teachYear = 0
}

class QuestionParam {
    var subjectId = 0
    var answer = ""
    var options = ""
    var type = 0
    var content = ""
}

class QuestionModifyParam {
    var id = 0
    var subjectId = 0
    var answer = ""
    var options = ""
    var content = ""
}


class QuestionQueryParam {
    var subjectId = 0
    var type = 0
    var page = 1
    var size = 10
}

class QuestionListModel {
    var questionList = listOf<QuestionModel>()
    var page = 1
    var size = 10
    var total = 0
}

class QuestionModel {
    var id = 0
    var subjectId = 0
    var answer = ""
    var type = 0
    var content = ""
    var options = ""
    var createUser = 0
    var correctNum = 0
    var counts = 0
}
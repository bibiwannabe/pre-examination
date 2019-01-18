package com.libiyi.exa.portal.api.param

class QuestionModel {
    var id = 0
    var subjectId = 0
    var type = 0
    var content = ""
    var options = ""
}
class QuestionAnswerModel {
    var id = 0
    var subjectId = 0
    var type = 0
    var content = ""
    var options = ""
    var answer = ""
    var wrongCount = 0
}

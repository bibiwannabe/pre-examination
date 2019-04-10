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

class SubjectQuestionModel {
    var subjectId= 0
    var subjectName = ""
    var questionCounts = 0
}

class SubjectQuestionPageModel {
    var subjectList = listOf<SubjectQuestionModel>()
    var page = 1
    var size = 10
    var total = 0
}

class PracticeRecordModel {
    var paperId = 0
    var subjectId = 0
    var practiceId = 0
    var paperName = ""
    var avgPoints = 0
    var counts = 0
}
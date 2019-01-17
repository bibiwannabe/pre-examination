package com.libiyi.exa.portal.api.param

class PaperModel {
    var id = 0
    var subjectId = 0
    var name = ""
}

class PaperListModel {
    var paperList = listOf<PaperModel>()
    var page = 1
    var size = 10
    var total = 0
}

class PaperAndQuestionInfoModel {
    var id = 0
    var subjectId = 0
    var name = ""
    var choice = QuestionInfoListAndPoints()
    var selection = QuestionInfoListAndPoints()
    var filling = QuestionInfoListAndPoints()
}

class QuestionInfoListAndPoints {
    var point = 0
    var questionList = listOf<QuestionModel>()
}
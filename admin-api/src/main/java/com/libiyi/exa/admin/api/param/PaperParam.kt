package com.libiyi.exa.admin.api.param

class PaperCreateParam {
    var subjectId = 0
    var name = ""
    var choiceList = PaperQuestionParam()
    var selectionList = PaperQuestionParam()
    var fillingList = PaperQuestionParam()
}

class PaperQuestionParam {
    var questionIdList = listOf<Integer>()
    var eachPoints = 0
}
class PaperModifyParam {
    var id = 0
    var subjectId = 0
    var name = ""
    var choiceList = PaperQuestionParam()
    var selectionList = PaperQuestionParam()
    var fillingList = PaperQuestionParam()
}

class PaperModel {
    var id = 0
    var subjectId = 0
    var name = ""
    var avgPoints = 0
    var counts = 0
    var createUser = 0
    var createTime = 0L
    var updateTime = 0L
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
    var avgPoints = 0
    var counts = 0
    var createUser = 0
    var createTime = 0L
    var updateTime = 0L
    var choice = QuestionInfoListAndPoints()
    var selection = QuestionInfoListAndPoints()
    var filling = QuestionInfoListAndPoints()
}

class QuestionInfoListAndPoints {
    var point = 0
    var questionList = listOf<QuestionModel>()
}
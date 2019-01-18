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

class PracticeModel {
    var paperId = 0
    var choice = HashMap<Integer,String>()
    var selection = HashMap<Integer,String>()
    var filling = HashMap<Integer,String>()
}

class EvaluateResultModel {
    var grades = 0
    var id = 0
    var subjectId = 0
    var name = ""
    var choice = EvaluateResultAndPointsModel()
    var selection = EvaluateResultAndPointsModel()
    var filling = EvaluateResultAndPointsModel()
}

class EvaluateResultAndPointsModel {
    var totalPoints = 0
    var questionList = listOf<QuestionResultModel>()
}

class QuestionResultModel {
    var id = 0
    var subjectId = 0 // required
    var type = 0
    var content = ""
    var options = ""
    var answer = ""
    var wrongAnswer = ""
    var isCorrect = true
}
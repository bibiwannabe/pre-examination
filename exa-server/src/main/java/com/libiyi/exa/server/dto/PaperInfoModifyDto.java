package com.libiyi.exa.server.dto;

public class PaperInfoModifyDto {
    private Integer id;
    private Integer subjectId;
    private String questionList;

    public PaperInfoModifyDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }
}

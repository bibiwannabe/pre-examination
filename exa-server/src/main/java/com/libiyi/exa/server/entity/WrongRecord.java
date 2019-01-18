package com.libiyi.exa.server.entity;

public class WrongRecord {

    private Integer id;
    private Integer userId;
    private Integer questionId;
    private Integer subjectId;
    private Integer wrongCounts;
    private Long createTime;
    private Long updateTime;

    public WrongRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getWrongCounts() {
        return wrongCounts;
    }

    public void setWrongCounts(Integer wrongCounts) {
        this.wrongCounts = wrongCounts;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}

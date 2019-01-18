package com.libiyi.exa.server.entity;

public class PracticeRecord {
    private Integer id;
    private Integer subjectId;
    private Integer paperId;
    private String answer;
    private Integer points;
    private Integer userId;
    private Long createTime;
    private Long updateTime;

    public PracticeRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
    public Integer getPaperId() {
        return paperId;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

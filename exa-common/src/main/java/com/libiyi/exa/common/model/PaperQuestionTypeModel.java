package com.libiyi.exa.common.model;

import java.util.List;

public class PaperQuestionTypeModel {
    private Integer point;
    private List<Integer> idList;

    public PaperQuestionTypeModel() {
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}

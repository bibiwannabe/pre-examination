package com.libiyi.exa.common.model;

public class PaperQuestionListModel {
    private PaperQuestionTypeModel choice;
    private PaperQuestionTypeModel selection;
    private PaperQuestionTypeModel filling;

    public PaperQuestionListModel() {
    }

    public PaperQuestionTypeModel getChoice() {
        return choice;
    }

    public void setChoice(PaperQuestionTypeModel choice) {
        this.choice = choice;
    }

    public PaperQuestionTypeModel getSelection() {
        return selection;
    }

    public void setSelection(PaperQuestionTypeModel selection) {
        this.selection = selection;
    }

    public PaperQuestionTypeModel getFilling() {
        return filling;
    }

    public void setFilling(PaperQuestionTypeModel filling) {
        this.filling = filling;
    }
}

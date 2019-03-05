package com.libiyi.exa.common.model;

import java.util.Map;

public class RecordAnswerModel {

    public RecordAnswerModel() {
    }

    private Map<Integer, String> choice;

    private Map<Integer, String> selection;

    private Map<Integer, String> filling;

    public Map<Integer, String> getChoice() {
        return choice;
    }

    public void setChoice(Map<Integer, String> choice) {
        this.choice = choice;
    }

    public Map<Integer, String> getSelection() {
        return selection;
    }

    public void setSelection(Map<Integer, String> selection) {
        this.selection = selection;
    }

    public Map<Integer, String> getFilling() {
        return filling;
    }

    public void setFilling(Map<Integer, String> filling) {
        this.filling = filling;
    }
}

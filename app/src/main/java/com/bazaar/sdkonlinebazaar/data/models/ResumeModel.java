package com.bazaar.sdkonlinebazaar.data.models;

public class ResumeModel {

    String quesId,subjectId,Position,Status,SelectedOption;     // Column I (Primary Key)

    public ResumeModel(String quesId, String subjectId, String position, String status, String selectedOption) {
        this.quesId = quesId;
        this.subjectId = subjectId;
        Position = position;
        Status = status;
        SelectedOption = selectedOption;
    }

    public String getQuesId() {
        return quesId;
    }

    public void setQuesId(String quesId) {
        this.quesId = quesId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSelectedOption() {
        return SelectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        SelectedOption = selectedOption;
    }
}

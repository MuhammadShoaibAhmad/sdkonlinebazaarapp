package com.bazaar.sdkonlinebazaar.data.responses;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionResponse {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Question")
    @Expose
    private String question;
    @SerializedName("OptionA")
    @Expose
    private String optionA;
    @SerializedName("OptionB")
    @Expose
    private String optionB;
    @SerializedName("OptionC")
    @Expose
    private String optionC;
    @SerializedName("OptionD")
    @Expose
    private String optionD;
    @SerializedName("CorrectOption")
    @Expose
    private String correctOption;
    @SerializedName("Category")
    @Expose
    private String category;


    @SerializedName("HasImage")
    @Expose
    private String hasImage;
    @SerializedName("Image")
    @Expose
    private String image;



    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getHasImage() {
        return hasImage;
    }

    public void setHasImage(String hasImage) {
        this.hasImage = hasImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

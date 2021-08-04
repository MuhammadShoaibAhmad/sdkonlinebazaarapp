package com.bazaar.sdkonlinebazaar.data.responses.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MileageResponse {


    @SerializedName("Column1")
    @Expose
    private String column1;
    @SerializedName("Day")
    @Expose
    private Integer day;
    @SerializedName("Mileage")
    @Expose
    private Double mileage;


    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

}

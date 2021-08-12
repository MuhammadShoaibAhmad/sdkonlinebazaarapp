package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ModulesResponse {

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DateEntered")
    @Expose
    private Object dateEntered;
    @SerializedName("EnteredBy")
    @Expose
    private Object enteredBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Object dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Object getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(Object enteredBy) {
        this.enteredBy = enteredBy;
    }

}
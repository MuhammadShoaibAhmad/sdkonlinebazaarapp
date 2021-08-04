package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppNotificationResponse {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("CustomersVehiclesID")
    @Expose
    private Integer customersVehiclesID;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("EventDescription")
    @Expose
    private String eventDescription;
    @SerializedName("EventDate")
    @Expose
    private String eventDate;
    @SerializedName("EventDatestr")
    @Expose
    private String eventDatestr;
    @SerializedName("EnteredDate")
    @Expose
    private String enteredDate;
    @SerializedName("RelativeNotificationDate")
    @Expose
    private String relativeNotificationDate;
    @SerializedName("Is_Sent")
    @Expose
    private Boolean isSent;
    @SerializedName("IsSeen")
    @Expose
    private Boolean isSeen;
    @SerializedName("SentTime")
    @Expose
    private String sentTime;
    @SerializedName("ClientRemarks")
    @Expose
    private String clientRemarks;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getCustomersVehiclesID() {
        return customersVehiclesID;
    }

    public void setCustomersVehiclesID(Integer customersVehiclesID) {
        this.customersVehiclesID = customersVehiclesID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDatestr() {
        return eventDatestr;
    }

    public void setEventDatestr(String eventDatestr) {
        this.eventDatestr = eventDatestr;
    }

    public String getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(String enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getRelativeNotificationDate() {
        return relativeNotificationDate;
    }

    public void setRelativeNotificationDate(String relativeNotificationDate) {
        this.relativeNotificationDate = relativeNotificationDate;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    public Boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Boolean isSeen) {
        this.isSeen = isSeen;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public String getClientRemarks() {
        return clientRemarks;
    }

    public void setClientRemarks(String clientRemarks) {
        this.clientRemarks = clientRemarks;
    }

}
package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse {

    @SerializedName("CaptureTime")
    @Expose
    private String captureTime;
    @SerializedName("Registration")
    @Expose
    private String registration;
    @SerializedName("Status_Event")
    @Expose
    private String statusEvent;

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getStatusEvent() {
        return statusEvent;
    }

    public void setStatusEvent(String statusEvent) {
        this.statusEvent = statusEvent;
    }

}
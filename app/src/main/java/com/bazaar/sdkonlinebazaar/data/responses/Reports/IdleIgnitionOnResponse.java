package com.bazaar.sdkonlinebazaar.data.responses.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdleIgnitionOnResponse {



    @SerializedName("RegNo")
    @Expose
    private String regNo;
////////////////////////
    @SerializedName("Location")
    @Expose
    private String location;
    //////////////////
    @SerializedName("EventTimestr")
    @Expose
    private String eventTimestr;
    ////////////////
    @SerializedName("EventTime")
    @Expose
    private String eventTime;
    /////////////
    @SerializedName("IdleTime")
    @Expose
    private String idleTime;
    ///////////
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    ///////////
    @SerializedName("Longitude")
    @Expose
    private Double longitude;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTimestr() {
        return eventTimestr;
    }

    public void setEventTimestr(String eventTimestr) {
        this.eventTimestr = eventTimestr;
    }



    public String getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(String idleTime) {
        this.idleTime = idleTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

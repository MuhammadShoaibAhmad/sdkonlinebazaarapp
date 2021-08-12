package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersionTrackingDataResponse {

    @SerializedName("TrackID")
    @Expose
    private Integer trackID;
    @SerializedName("PersonID")
    @Expose
    private Integer personID;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("Altitude")
    @Expose
    private Integer altitude;
    @SerializedName("Speed")
    @Expose
    private Integer speed;
    @SerializedName("Heading")
    @Expose
    private Integer heading;
    @SerializedName("Satellites")
    @Expose
    private Integer satellites;
    @SerializedName("EventTime")
    @Expose
    private String eventTime;
    @SerializedName("ReceivedDate")
    @Expose
    private String receivedDate;
    @SerializedName("EnteredDate")
    @Expose
    private String enteredDate;
    @SerializedName("Mileage")
    @Expose
    private Double mileage;
    @SerializedName("IsDisplayed")
    @Expose
    private Boolean isDisplayed;

    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
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

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getHeading() {
        return heading;
    }

    public void setHeading(Integer heading) {
        this.heading = heading;
    }

    public Integer getSatellites() {
        return satellites;
    }

    public void setSatellites(Integer satellites) {
        this.satellites = satellites;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(String enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Boolean getIsDisplayed() {
        return isDisplayed;
    }

    public void setIsDisplayed(Boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }

}

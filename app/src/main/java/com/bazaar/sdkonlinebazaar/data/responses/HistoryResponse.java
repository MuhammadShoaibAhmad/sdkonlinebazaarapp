package com.bazaar.sdkonlinebazaar.data.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse implements Parcelable  {

    @SerializedName("CaptureTime")
    @Expose
    private String captureTime;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Speed")
    @Expose
    private String speed;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;

    public HistoryResponse(String captureTime, String status, String location, String speed, String latitude, String longitude) {
        this.captureTime = captureTime;
        this.status = status;
        this.location = location;
        this.speed = speed;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public HistoryResponse() {
    }

    protected HistoryResponse(Parcel in) {
        captureTime = in.readString();
        status = in.readString();
        location = in.readString();
        speed = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<HistoryResponse> CREATOR = new Creator<HistoryResponse>() {
        @Override
        public HistoryResponse createFromParcel(Parcel in) {
            return new HistoryResponse(in);
        }

        @Override
        public HistoryResponse[] newArray(int size) {
            return new HistoryResponse[size];
        }
    };

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(captureTime);
        dest.writeString(status);
        dest.writeString(location);
        dest.writeString(speed);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}
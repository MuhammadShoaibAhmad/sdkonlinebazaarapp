package com.bazaar.sdkonlinebazaar.data.responses;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoFenceResponse implements Parcelable {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Lattitude")
    @Expose
    private Double lattitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("Radius")
    @Expose
    private Double radius;


    protected GeoFenceResponse(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            lattitude = null;
        } else {
            lattitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            radius = null;
        } else {
            radius = in.readDouble();
        }
    }

    public static final Creator<GeoFenceResponse> CREATOR = new Creator<GeoFenceResponse>() {
        @Override
        public GeoFenceResponse createFromParcel(Parcel in) {
            return new GeoFenceResponse(in);
        }

        @Override
        public GeoFenceResponse[] newArray(int size) {
            return new GeoFenceResponse[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (lattitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lattitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        if (radius == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(radius);
        }
    }
}
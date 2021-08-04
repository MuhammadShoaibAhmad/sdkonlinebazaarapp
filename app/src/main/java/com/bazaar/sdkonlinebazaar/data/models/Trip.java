package com.bazaar.sdkonlinebazaar.data.models;/*
package com.track.trackingapp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.startech.startechmainapp.data.responses.HistoryResponse;

public class Trip implements Parcelable {

    private int index;
   private HistoryResponse ignitionOn;
    private HistoryResponse ignitionOff;

    public Trip() {

    }

    public Trip(int index, HistoryResponse ignitionOn, HistoryResponse ignitionOff) {
        this.index = index;
        this.ignitionOn = ignitionOn;
        this.ignitionOff = ignitionOff;
    }

    protected Trip(Parcel in) {
        index = in.readInt();
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public HistoryResponse getIgnitionOn() {
        return ignitionOn;
    }

    public void setIgnitionOn(HistoryResponse ignitionOn) {
        this.ignitionOn = ignitionOn;
    }

    public HistoryResponse getIgnitionOff() {
        return ignitionOff;
    }

    public void setIgnitionOff(HistoryResponse ignitionOff) {
        this.ignitionOff = ignitionOff;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
    }
}
*/

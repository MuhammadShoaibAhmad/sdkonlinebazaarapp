package com.bazaar.sdkonlinebazaar.data.responses;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllVehicleResponse {

    @SerializedName("SrNo")
    @Expose
    private Integer srNo;
    @SerializedName("TrackID")
    @Expose
    private Integer trackID;
    @SerializedName("CustomersvehiclesID")
    @Expose
    private Integer customersvehiclesID;
    @SerializedName("RegNo")
    @Expose
    private String regNo;
    @SerializedName("EventName")
    @Expose
    private String eventName;
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
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("EventTime")
    @Expose
    private String eventTime;
    @SerializedName("EventTimestr")
    @Expose
    private String eventTimestr;
    @SerializedName("RelativeNotificationDate")
    @Expose
    private String relativeNotificationDate;
    @SerializedName("ReceivedDate")
    @Expose
    private String receivedDate;
    @SerializedName("ReceivedDatestr")
    @Expose
    private String receivedDatestr;
    @SerializedName("DIN1")
    @Expose
    private Boolean dIN1;
    @SerializedName("DIN2")
    @Expose
    private Boolean dIN2;
    @SerializedName("DIN3")
    @Expose
    private Boolean dIN3;
    @SerializedName("DIN4")
    @Expose
    private Boolean dIN4;
    @SerializedName("DOUT1")
    @Expose
    private Boolean dOUT1;
    @SerializedName("DOUT2")
    @Expose
    private Boolean dOUT2;
    @SerializedName("DOUT3")
    @Expose
    private Boolean dOUT3;
    @SerializedName("DOUT4")
    @Expose
    private Boolean dOUT4;
    @SerializedName("AIN1_Tempreature")
    @Expose
    private Double aIN1Tempreature;
    @SerializedName("AIN2_Fuel")
    @Expose
    private Double aIN2Fuel;
    @SerializedName("TripMileage")
    @Expose
    private Double tripMileage;
    @SerializedName("Mileage")
    @Expose
    private Double mileage;
    @SerializedName("MainBV")
    @Expose
    private Double mainBV;
    @SerializedName("BackupBV")
    @Expose
    private Double backupBV;
    @SerializedName("IsAlarm")
    @Expose
    private Boolean isAlarm;
    @SerializedName("IsDisplayed")
    @Expose
    private Boolean isDisplayed;
    @SerializedName("AttendOpenTime")
    @Expose
    private String attendOpenTime;
    @SerializedName("AttendCloseTime")
    @Expose
    private String attendCloseTime;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("RSSI")
    @Expose
    private Integer rSSI;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
    }

    public Integer getCustomersvehiclesID() {
        return customersvehiclesID;
    }

    public void setCustomersvehiclesID(Integer customersvehiclesID) {
        this.customersvehiclesID = customersvehiclesID;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getRelativeNotificationDate() {
        return relativeNotificationDate;
    }

    public void setRelativeNotificationDate(String relativeNotificationDate) {
        this.relativeNotificationDate = relativeNotificationDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivedDatestr() {
        return receivedDatestr;
    }

    public void setReceivedDatestr(String receivedDatestr) {
        this.receivedDatestr = receivedDatestr;
    }

    public Boolean getDIN1() {
        return dIN1;
    }

    public void setDIN1(Boolean dIN1) {
        this.dIN1 = dIN1;
    }

    public Boolean getDIN2() {
        return dIN2;
    }

    public void setDIN2(Boolean dIN2) {
        this.dIN2 = dIN2;
    }

    public Boolean getDIN3() {
        return dIN3;
    }

    public void setDIN3(Boolean dIN3) {
        this.dIN3 = dIN3;
    }

    public Boolean getDIN4() {
        return dIN4;
    }

    public void setDIN4(Boolean dIN4) {
        this.dIN4 = dIN4;
    }

    public Boolean getDOUT1() {
        return dOUT1;
    }

    public void setDOUT1(Boolean dOUT1) {
        this.dOUT1 = dOUT1;
    }

    public Boolean getDOUT2() {
        return dOUT2;
    }

    public void setDOUT2(Boolean dOUT2) {
        this.dOUT2 = dOUT2;
    }

    public Boolean getDOUT3() {
        return dOUT3;
    }

    public void setDOUT3(Boolean dOUT3) {
        this.dOUT3 = dOUT3;
    }

    public Boolean getDOUT4() {
        return dOUT4;
    }

    public void setDOUT4(Boolean dOUT4) {
        this.dOUT4 = dOUT4;
    }

    public Double getAIN1Tempreature() {
        return aIN1Tempreature;
    }

    public void setAIN1Tempreature(Double aIN1Tempreature) {
        this.aIN1Tempreature = aIN1Tempreature;
    }

    public Double getAIN2Fuel() {
        return aIN2Fuel;
    }

    public void setAIN2Fuel(Double aIN2Fuel) {
        this.aIN2Fuel = aIN2Fuel;
    }

    public Double getTripMileage() {
        return tripMileage;
    }

    public void setTripMileage(Double tripMileage) {
        this.tripMileage = tripMileage;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getMainBV() {
        return mainBV;
    }

    public void setMainBV(Double mainBV) {
        this.mainBV = mainBV;
    }

    public Double getBackupBV() {
        return backupBV;
    }

    public void setBackupBV(Double backupBV) {
        this.backupBV = backupBV;
    }

    public Boolean getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(Boolean isAlarm) {
        this.isAlarm = isAlarm;
    }

    public Boolean getIsDisplayed() {
        return isDisplayed;
    }

    public void setIsDisplayed(Boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }

    public String getAttendOpenTime() {
        return attendOpenTime;
    }

    public void setAttendOpenTime(String attendOpenTime) {
        this.attendOpenTime = attendOpenTime;
    }

    public String getAttendCloseTime() {
        return attendCloseTime;
    }

    public void setAttendCloseTime(String attendCloseTime) {
        this.attendCloseTime = attendCloseTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRSSI() {
        return rSSI;
    }

    public void setRSSI(Integer rSSI) {
        this.rSSI = rSSI;
    }

}
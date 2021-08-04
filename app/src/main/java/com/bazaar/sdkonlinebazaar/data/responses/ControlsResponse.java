package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ControlsResponse {

    @SerializedName("DoorUnlock")
    @Expose
    private Boolean doorUnlock;
    @SerializedName("EngineKill")
    @Expose
    private Boolean engineKill;
    @SerializedName("SirenOn")
    @Expose
    private Boolean sirenOn;
    @SerializedName("EngineRelease")
    @Expose
    private Boolean engineRelease;
    @SerializedName("SirenOff")
    @Expose
    private Boolean sirenOff;

    public Boolean getDoorUnlock() {
        return doorUnlock;
    }

    public void setDoorUnlock(Boolean doorUnlock) {
        this.doorUnlock = doorUnlock;
    }

    public Boolean getEngineKill() {
        return engineKill;
    }

    public void setEngineKill(Boolean engineKill) {
        this.engineKill = engineKill;
    }

    public Boolean getSirenOn() {
        return sirenOn;
    }

    public void setSirenOn(Boolean sirenOn) {
        this.sirenOn = sirenOn;
    }

    public Boolean getEngineRelease() {
        return engineRelease;
    }

    public void setEngineRelease(Boolean engineRelease) {
        this.engineRelease = engineRelease;
    }

    public Boolean getSirenOff() {
        return sirenOff;
    }

    public void setSirenOff(Boolean sirenOff) {
        this.sirenOff = sirenOff;
    }

}
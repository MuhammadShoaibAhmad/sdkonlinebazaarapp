package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("LoginId")
    @Expose
    private Integer loginId;
    @SerializedName("OrgID")
    @Expose
    private Integer orgID;
    @SerializedName("BranchID")
    @Expose
    private Integer branchID;
    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("CustomersVehicleID")
    @Expose
    private Integer customersVehicleID;
    @SerializedName("Response")
    @Expose
    private String response;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getCustomersVehicleID() {
        return customersVehicleID;
    }

    public void setCustomersVehicleID(Integer customersVehicleID) {
        this.customersVehicleID = customersVehicleID;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

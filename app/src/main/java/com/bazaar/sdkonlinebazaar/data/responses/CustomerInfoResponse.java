package com.bazaar.sdkonlinebazaar.data.responses;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInfoResponse {

    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("VehicleID")
    @Expose
    private Integer vehicleID;
    @SerializedName("CustomersVehiclesID")
    @Expose
    private Integer customersVehiclesID;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("DOB")
    @Expose
    private String dOB;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("OrganizationName")
    @Expose
    private String organizationName;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("MobilePhone1")
    @Expose
    private String mobilePhone1;
    @SerializedName("MobilePhone2")
    @Expose
    private String mobilePhone2;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("HomePhone")
    @Expose
    private String homePhone;
    @SerializedName("BusinessPhone")
    @Expose
    private String businessPhone;
    @SerializedName("CNIC")
    @Expose
    private String cNIC;
    @SerializedName("RegistrationNo")
    @Expose
    private String registrationNo;
    @SerializedName("EngineNo")
    @Expose
    private String engineNo;
    @SerializedName("ChassisNo")
    @Expose
    private String chassisNo;
    @SerializedName("VehicleModel")
    @Expose
    private String vehicleModel;
    @SerializedName("ManufacturerName")
    @Expose
    private String manufacturerName;
    @SerializedName("ColorName")
    @Expose
    private String colorName;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("IsWebLogin")
    @Expose
    private Boolean isWebLogin;
    @SerializedName("IsMobileAppLogin")
    @Expose
    private Boolean isMobileAppLogin;
    @SerializedName("IsAtive")
    @Expose
    private Boolean isAtive;
    @SerializedName("TerminalNo")
    @Expose
    private String terminalNo;
    @SerializedName("IsTerminalDeployed")
    @Expose
    private Boolean isTerminalDeployed;
    @SerializedName("DateEntered")
    @Expose
    private String dateEntered;
    @SerializedName("EnteredBy")
    @Expose
    private String enteredBy;
    @SerializedName("LastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("LastModifiedBy")
    @Expose
    private String lastModifiedBy;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Integer getCustomersVehiclesID() {
        return customersVehiclesID;
    }

    public void setCustomersVehiclesID(Integer customersVehiclesID) {
        this.customersVehiclesID = customersVehiclesID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMobilePhone1() {
        return mobilePhone1;
    }

    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }

    public String getMobilePhone2() {
        return mobilePhone2;
    }

    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getCNIC() {
        return cNIC;
    }

    public void setCNIC(String cNIC) {
        this.cNIC = cNIC;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsWebLogin() {
        return isWebLogin;
    }

    public void setIsWebLogin(Boolean isWebLogin) {
        this.isWebLogin = isWebLogin;
    }

    public Boolean getIsMobileAppLogin() {
        return isMobileAppLogin;
    }

    public void setIsMobileAppLogin(Boolean isMobileAppLogin) {
        this.isMobileAppLogin = isMobileAppLogin;
    }

    public Boolean getIsAtive() {
        return isAtive;
    }

    public void setIsAtive(Boolean isAtive) {
        this.isAtive = isAtive;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public Boolean getIsTerminalDeployed() {
        return isTerminalDeployed;
    }

    public void setIsTerminalDeployed(Boolean isTerminalDeployed) {
        this.isTerminalDeployed = isTerminalDeployed;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

}

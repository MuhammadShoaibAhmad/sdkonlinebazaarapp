package com.bazaar.sdkonlinebazaar.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersionResponse {

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("MotherName")
    @Expose
    private String motherName;
    @SerializedName("LoginName")
    @Expose
    private String loginName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Brother")
    @Expose
    private Integer brother;
    @SerializedName("Sister")
    @Expose
    private Integer sister;
    @SerializedName("GenderID")
    @Expose
    private Integer genderID;
    @SerializedName("Height")
    @Expose
    private String height;
    @SerializedName("CasteID")
    @Expose
    private Integer casteID;
    @SerializedName("ReligionID")
    @Expose
    private Integer religionID;
    @SerializedName("Profession")
    @Expose
    private String profession;
    @SerializedName("Education")
    @Expose
    private String education;
    @SerializedName("EducationID")
    @Expose
    private Integer educationID;
    @SerializedName("MonthlyIncome")
    @Expose
    private String monthlyIncome;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("ModuleID")
    @Expose
    private Integer moduleID;
    @SerializedName("ModulesTypesID")
    @Expose
    private Integer modulesTypesID;
    @SerializedName("DateEntered")
    @Expose
    private String dateEntered;
    @SerializedName("EnteredBy")
    @Expose
    private Integer enteredBy;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBrother() {
        return brother;
    }

    public void setBrother(Integer brother) {
        this.brother = brother;
    }

    public Integer getSister() {
        return sister;
    }

    public void setSister(Integer sister) {
        this.sister = sister;
    }

    public Integer getGenderID() {
        return genderID;
    }

    public void setGenderID(Integer genderID) {
        this.genderID = genderID;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getCasteID() {
        return casteID;
    }

    public void setCasteID(Integer casteID) {
        this.casteID = casteID;
    }

    public Integer getReligionID() {
        return religionID;
    }

    public void setReligionID(Integer religionID) {
        this.religionID = religionID;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getEducationID() {
        return educationID;
    }

    public void setEducationID(Integer educationID) {
        this.educationID = educationID;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getModuleID() {
        return moduleID;
    }

    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    public Integer getModulesTypesID() {
        return modulesTypesID;
    }

    public void setModulesTypesID(Integer modulesTypesID) {
        this.modulesTypesID = modulesTypesID;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Integer getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
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
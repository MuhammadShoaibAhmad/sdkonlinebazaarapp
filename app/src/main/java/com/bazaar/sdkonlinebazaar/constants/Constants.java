package com.bazaar.sdkonlinebazaar.constants;


import android.location.Location;

import com.bazaar.sdkonlinebazaar.data.responses.ModulesResponse;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static boolean isAlreadyBoud=true;

    public static FusedLocationProviderClient mFusedLocationClient;
    public static SettingsClient mSettingsClient;
    public static LocationRequest mLocationRequest;
    public static LocationSettingsRequest mLocationSettingsRequest;
    public static LocationCallback mLocationCallback;
    public static Location mCurrentLocation;
    public static final int REQUEST_CHECK_SETTINGS = 100;
    public static Boolean mRequestingLocationUpdates;
    public static String loadingMsg="Loading...";
    public static String verifyMsg="Verifying...";
    public static List<PersionResponse> allpersionList=new ArrayList<PersionResponse>();
    public static List<ModulesResponse> allModuleTypeList=new ArrayList<ModulesResponse>();


////Employee Data
    public static  int ID = -1;
    public static String Name="" ;
    public static String FatherName="";
    public static String DOB="";
    public static int ModuleID=-1;
    public static int ModulesTypesID=-1;
    public static int MarrageModulesTypesID=5;
    public static double Latitude=0.0;
    public static double Longitude=0.0;

    public static  String Mobile = "";
    public static String Profession="" ;
    public static String Education="";
    public static String Email="";
    public static int Gender=-1;
    public static String Salary="";
    public static String Password="";





}

package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bazaar.sdkonlinebazaar.BuildConfig;
import com.bazaar.sdkonlinebazaar.R;
import com.bazaar.sdkonlinebazaar.constants.Constants;
import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
import com.bazaar.sdkonlinebazaar.utils.Locationgetter;
import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
import com.bazaar.sdkonlinebazaar.utils.Utils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private PersionResponse per=new PersionResponse();
    private Button signin;
    private EditText Name,Email,Password;
    Locationgetter loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
    }


    private void bindViews() {
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);
        loc=new Locationgetter(LoginActivity.this);

        signin = findViewById(R.id.signin);
        Name = findViewById(R.id.username);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.password);

    }
    public void persionSignIn(View view) {

        per.setName(Name.getText().toString());

        per.setEmail(Name.getText().toString());
        per.setPassword(Password.getText().toString());


        progressDialog.showProgressDialog();
        if (Utils.isValidString(Name.getText().toString()) && Utils.isValidString(Password.getText().toString())) {
            Call<PersionResponse> loginResponseCall = RetrofitClient.getInstance().userLogin(per);
            loginResponseCall.enqueue(new Callback<PersionResponse>() {
                @Override
                public void onResponse(Call<PersionResponse> call, Response<PersionResponse> response) {
                    progressDialog.hideProgressDialog();
                    if (response.body() != null) {
                        PersionResponse signinResponse = response.body();
                        if (signinResponse !=null) {
                           Constants.ID= signinResponse.getId();
                            Constants.Name= signinResponse.getName();
                            Constants.FatherName= signinResponse.getFatherName();
                            Constants.DOB= signinResponse.getDOB();
                            Constants.ModuleID= signinResponse.getModuleID();
                            Constants.Gender= signinResponse.getGenderID();
                            Constants.ModulesTypesID= signinResponse.getModulesTypesID();
                            Constants.Latitude= signinResponse.getLatitude();
                            Constants.Longitude= signinResponse.getLongitude();

                            Constants.Mobile= signinResponse.getMobile();
                            Constants.Profession= signinResponse.getProfession();
                            Constants.Education= signinResponse.getEducation();
                            Constants.Email= signinResponse.getEmail();
                            Constants.Salary= signinResponse.getMonthlyIncome();
                            Constants.Password= signinResponse.getPassword();
                        /*    Constants.Latitude= signinResponse.getLatitude();
                            Constants.Longitude= signinResponse.getLongitude();*/
                            progressDialog.hideProgressDialog();
                            gotoMapActivity();
                            Utils.showSnackBar(LoginActivity.this, "Login Successfull..!!");
                        } else {

                            Utils.showSnackBar(LoginActivity.this, "Invalid user ..!!");
                        }
                    } else {
                        Utils.showSnackBar(LoginActivity.this, "Invalid user ..!!");
                    }
                }

                @Override
                public void onFailure(Call<PersionResponse> call, Throwable t) {
                    progressDialog.hideProgressDialog();
                    Utils.showSnackBar(LoginActivity.this, "Something went wrong ..!!");
                    Log.e("TAG", t.getMessage());
                }
            });

        }
        else {
            Utils.showSnackBar(this, "Invalid Inputs ..!!");
        }

    }
    public void gotoSignupActivity(View v){
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
    private void gotoMapActivity(){
        Intent intent = new Intent(LoginActivity.this, MainmapActivity.class);
        startActivity(intent);
        finish();
    }





}



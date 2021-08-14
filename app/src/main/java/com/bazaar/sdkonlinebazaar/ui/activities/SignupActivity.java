package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bazaar.sdkonlinebazaar.R;
import com.bazaar.sdkonlinebazaar.constants.Constants;
import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
import com.bazaar.sdkonlinebazaar.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private PersionResponse per=new PersionResponse();
    private Button signup;
    private EditText Name, FatherName,Mobile,Profession,Education,Email,Password;
    private RadioGroup radioGender,radioModules;
    private RadioButton radioButtonGender,radioButtonModules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindViews();

    }

    public void gotoLoginActivity(View v){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void bindViews() {
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);


        signup = findViewById(R.id.signup);
        Name = findViewById(R.id.Name);
        FatherName = findViewById(R.id.FatherName);
        Mobile = findViewById(R.id.Mobile);
        Profession = findViewById(R.id.Profession);
        Education = findViewById(R.id.Education);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);

        radioGender = findViewById(R.id.radio);
        radioModules = findViewById(R.id.radio1);


    }


    public void persionSignUp(View view) {



        per.setName(Name.getText().toString());
        per.setFatherName(FatherName.getText().toString());
        per.setMobile(Mobile.getText().toString());
        per.setProfession(Profession.getText().toString());
        per.setEducation(Education.getText().toString());
        per.setEmail(Email.getText().toString());
        per.setLoginName(Email.getText().toString());
        per.setPassword(Password.getText().toString());

        // get selected radio button from radioGroup
        int selectedId = radioGender.getCheckedRadioButtonId();
        int moduleId = radioModules.getCheckedRadioButtonId();
      // find the radiobutton by returned id
        radioButtonGender =  findViewById(selectedId);
        radioButtonModules =  findViewById(moduleId);
        String gender=  (radioButtonGender.getText().toString());
        String module=  (radioButtonModules.getText().toString());
        if(gender.contains("Male")){
            per.setGenderID(1);
        }else{
            per.setGenderID(2);
        }
        if(module.contains("Job")){
            per.setModuleID(1);
        }else if(module.contains("Food")){
            per.setModuleID(2);
        }else {
            per.setModuleID(3);
        }


        progressDialog.showProgressDialog();
        if (Utils.isValidString(Name.getText().toString()) && Utils.isValidString(FatherName.getText().toString()) && Utils.isValidString(Email.getText().toString())&& Utils.isValidString(Password.getText().toString())) {
            Call<PersionResponse> loginResponseCall = RetrofitClient.getInstance().CreatePersion(per);
            loginResponseCall.enqueue(new Callback<PersionResponse>() {
                @Override
                public void onResponse(Call<PersionResponse> call, Response<PersionResponse> response) {
                    progressDialog.hideProgressDialog();
                    if (response.body() != null) {
                        PersionResponse signupResponse = response.body();
                        if (signupResponse !=null) {

                            progressDialog.hideProgressDialog();
                            Utils.showSnackBar(SignupActivity.this, "User Login Successfull Created ..!!");
                        } else {

                            Utils.showSnackBar(SignupActivity.this, "Invalid user ..!!");
                        }
                    } else {
                        Utils.showSnackBar(SignupActivity.this, "Invalid user ..!!");
                    }
                }

                @Override
                public void onFailure(Call<PersionResponse> call, Throwable t) {
                    progressDialog.hideProgressDialog();
                    Utils.showSnackBar(SignupActivity.this, "Something went wrong ..!!");
                    Log.e("TAG", t.getMessage());
                }
            });

        }
        else {
            Utils.showSnackBar(this, "Please Enter All Inputs ..!!");
        }

    }


}
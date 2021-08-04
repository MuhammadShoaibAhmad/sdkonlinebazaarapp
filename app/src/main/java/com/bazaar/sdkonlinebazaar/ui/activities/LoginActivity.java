package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bazaar.sdkonlinebazaar.R;
import com.bazaar.sdkonlinebazaar.constants.Constants;
import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
import com.bazaar.sdkonlinebazaar.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private PersionResponse per=new PersionResponse();
    private Button signin;
    private EditText Name,Email,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();
    }




    private void bindViews() {
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);


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
                        PersionResponse signupResponse = response.body();
                        if (signupResponse !=null) {

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



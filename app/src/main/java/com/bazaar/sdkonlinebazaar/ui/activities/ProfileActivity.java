package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bazaar.sdkonlinebazaar.R;
import com.bazaar.sdkonlinebazaar.constants.Constants;
import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
import com.bazaar.sdkonlinebazaar.data.models.SpinnerArray;
import com.bazaar.sdkonlinebazaar.data.responses.ModulesResponse;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
import com.bazaar.sdkonlinebazaar.utils.Utils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private Spinner spin;
    private List<SpinnerArray> spinnerlist;
    private List<ModulesResponse> allModuleTypeList;
    private ProgressDialog progressDialog;
    private Button updateprofile;

    private EditText Name, FatherName,Mobile,Profession,Education,Email,Password;
    private RadioGroup radioGender,radioModules;
    private RadioButton radioMalepro,radioFemalepro,radioJobpro,radioFoodpro,radioMarriageBureaupro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bindViews();
    }

    private void bindViews() {
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);


        updateprofile = findViewById(R.id.updateprofile);
        Name = findViewById(R.id.Namepro);
        FatherName = findViewById(R.id.FatherNamepro);
        Mobile = findViewById(R.id.Mobilepro);
        Profession = findViewById(R.id.Professionpro);
        Education = findViewById(R.id.Educationpro);
        Email = findViewById(R.id.Emailpro);

  /*      Email = findViewById(R.id.Emailpro);
        Mobile = findViewById(R.id.Mobile);*/

        radioGender = findViewById(R.id.radioGenderpro);
        radioModules = findViewById(R.id.moduleradiopro);

        Name.setText(Constants.Name);
        FatherName.setText(Constants.FatherName);
        Mobile.setText(Constants.Mobile);
        Profession.setText(Constants.Profession);
        Education.setText(Constants.Education);
        Email.setText(Constants.Email);

//hello Muzammil
         String Name123="Muzammil";


        radioMalepro = findViewById(R.id.radioMalepro);
        radioFemalepro = findViewById(R.id.radioFemalepro);

        radioMalepro = findViewById(R.id.radioMalepro);
        radioFemalepro = findViewById(R.id.radioFemalepro);
        radioJobpro = findViewById(R.id.radioJobpro);
        radioFoodpro = findViewById(R.id.radioFoodpro);
        radioMarriageBureaupro = findViewById(R.id.radioMarriageBureaupro);
        if(Constants.Gender==1){
            radioMalepro.setChecked(true);
        }else {
            radioFemalepro.setChecked(true);
        }
        if(Constants.ModuleID==1){
            radioJobpro.setChecked(true);
        }else if(Constants.ModuleID==2){
            radioFoodpro.setChecked(true);
        }else{
            radioMarriageBureaupro.setChecked(true);
        }
        spin = (Spinner) findViewById(R.id.moduleTypeSpinner);

        getAllModule(1);

    }


    public void onClickedJob(View view) {
        getAllModule(1);
    }
    public void onClickedFood(View view) {
        getAllModule(2);
    }
    public void onClickedMarrage(View view) {
        getAllModule(3);
    }

    public void desplayspinnerData(SpinnerArray sa){
    /*    name = sa.getName();
        id = sa.getId();
        Toast.makeText(this,name, Toast.LENGTH_LONG).show();*/
    }

    private void setSpinnerValues() {
        spinnerlist = new ArrayList<>();
        for (int i = 0; i < Constants.allModuleTypeList.size(); i++) {
            ModulesResponse temp = Constants.allModuleTypeList.get(i);
            SpinnerArray spinner = new SpinnerArray(temp.getName(), temp.getId());
            spinnerlist.add(spinner);
        }
        ArrayAdapter<SpinnerArray> aa = new ArrayAdapter<SpinnerArray>(this, android.R.layout.simple_spinner_item, spinnerlist);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                SpinnerArray spinnerarray = (SpinnerArray) arg0.getSelectedItem();
                desplayspinnerData(spinnerarray);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void getAllModule(int ModuleID) {

        try{
            // progressDialog.showProgressDialog();
            Call<List<ModulesResponse>> getModuleCall = RetrofitClient.getInstance().getModulesTypesByModuleIDList(ModuleID);
            getModuleCall.enqueue(new Callback<List<ModulesResponse>>() {
                @Override
                public void onResponse(Call<List<ModulesResponse>> call, Response<List<ModulesResponse>> response) {
                    progressDialog.hideProgressDialog();
                    if (response != null) {
                        allModuleTypeList = response.body();
                        Constants.allModuleTypeList=allModuleTypeList;

                        setSpinnerValues();


                    } else {
                        // Utils.showSnackBar(AllVehiclesActivity.this, "Invalid Response ..!!");

                        Toast.makeText(ProfileActivity.this, "Invalid Response ..!!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<List<ModulesResponse>> call, Throwable t) {
                    progressDialog.hideProgressDialog();
                    Utils.showSnackBar(ProfileActivity.this, "Something Went Wrong ..!!");

                    Toast.makeText(ProfileActivity.this, "Something Went Wrong ..!!" +t, Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception ex){
        }


    }
}





package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private Spinner spin;
    private List<SpinnerArray> spinnerlist;
    private List<ModulesResponse> allModuleTypeList;
    private ProgressDialog progressDialog;
    private Button updateprofile;
    private PersionResponse per;
    private EditText Name, FatherName,Mobile,Profession,Education,Email,Password,Salary,DOBpro;
    private RadioGroup radioGender,radioModules;
    private RadioButton radioMalepro,radioFemalepro,radioJobpro,radioFoodpro,radioMarriageBureaupro,radioButtonGender,radioButtonModules;

    private String imagePath;
    private ImageView imgView;
    private static String[] PERMISSSION_STORAGE={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bindViews();
    }

    private void bindViews() {
        verifyStoragePermission(ProfileActivity.this);
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);

        per=new PersionResponse();
        updateprofile = findViewById(R.id.updateprofile);
        Name = findViewById(R.id.Namepro);
        FatherName = findViewById(R.id.FatherNamepro);
        Mobile = findViewById(R.id.Mobilepro);
        Profession = findViewById(R.id.Professionpro);
        Education = findViewById(R.id.Educationpro);
        Email = findViewById(R.id.Emailpro);
        Password = findViewById(R.id.Password);
        Salary = findViewById(R.id.Salary);
        DOBpro = findViewById(R.id.DOBpro);

        imgView = findViewById(R.id.img_profile);
        /*Mobile = findViewById(R.id.Mobile);*/

        radioGender = findViewById(R.id.radioGenderpro);
        radioModules = findViewById(R.id.moduleradiopro);

        Name.setText(Constants.Name);
        FatherName.setText(Constants.FatherName);
        DOBpro.setText(Constants.DOB);
        Mobile.setText(Constants.Mobile);
        Profession.setText(Constants.Profession);
        Education.setText(Constants.Education);
        Email.setText(Constants.Email);
        Password.setText(Constants.Password);
        Salary.setText(Constants.Salary);

        radioGender = findViewById(R.id.radioGenderpro);
        radioModules = findViewById(R.id.moduleradiopro);

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

    private void desplayspinnerData(SpinnerArray sa){
       String name = sa.getName();
       int id = sa.getId();

       Constants.ModulesTypesID=id;
        /*  Toast.makeText(this,name, Toast.LENGTH_LONG).show();*/
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

    private void getAllModule(int ModuleID) {

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

    public void persionUpdate(View view) {

 /*       tbl.Email = mod.Email;
        tbl.Password = mod.Password;
        tbl.MonthlyIncome = mod.MonthlyIncome;
        tbl.Mobile = mod.Mobile;
        tbl.Profession = mod.Profession;
        tbl.Education = mod.Education;
        tbl.ModuleID = mod.ModuleID;
        tbl.ModulesTypesID = mod.ModulesTypesID;
        tbl.MonthlyIncome = mod.MonthlyIncome;*/
        per.setId(Constants.ID);
        per.setName(Name.getText().toString());
        per.setFatherName(FatherName.getText().toString());
        per.setMobile(Mobile.getText().toString());
        per.setProfession(Profession.getText().toString());
        per.setEducation(Education.getText().toString());
        per.setEmail(Email.getText().toString());
        per.setLoginName(Email.getText().toString());
        per.setPassword(Password.getText().toString());
        per.setMonthlyIncome(Salary.getText().toString());
        per.setModulesTypesID(Constants.ModulesTypesID);


        // get selected radio button from radioGroup
      /*  int selectedId = radioGender.getCheckedRadioButtonId();
        int moduleId = radioModules.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioGender =  findViewById(selectedId);
        radioModules =  findViewById(moduleId);
        int gender =  (radioGender.getId());
        int module=  (radioModules.getId());
        per.setGenderID(gender);
        per.setModuleID(module);*/

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


     /*   if(gender.contains("Male")){
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
        }*/


        progressDialog.showProgressDialog();

       /* if (Utils.isValidString(Name.getText().toString()) && Utils.isValidString(FatherName.getText().toString()) && Utils.isValidString(Email.getText().toString())&& Utils.isValidString(Password.getText().toString())) {*/
            Call<String> profileUpdateResponseCall = RetrofitClient.getInstance().UpdatePersonInfo(per);
            profileUpdateResponseCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    progressDialog.hideProgressDialog();
                    if (response.body() != null) {
                        String signupResponse = response.body();

                            progressDialog.hideProgressDialog();
                            Utils.showSnackBar(ProfileActivity.this, "Profile Successfull Updated ..!!");

                    } else {
                        Utils.showSnackBar(ProfileActivity.this, "Not  Updated Please Check Fields ..!!");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    progressDialog.hideProgressDialog();
                    Utils.showSnackBar(ProfileActivity.this, "Something went wrong ..!!");
                    Log.e("TAG", t.getMessage());
                }
            });

       /* }*/
       /* else {
            Utils.showSnackBar(this, "Please Enter All Inputs ..!!");
        }
*/
    }


public void uploadprofileimage(View view){
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_PICK);
    Intent result=Intent.createChooser(intent,"choose img");
    startActivityForResult(result, 10);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == 10 && resultCode == RESULT_OK) {

                Uri uri = data.getData();
                imagePath=getRealPathFromURI(data.getData());
                imgView.setImageURI(uri);
            } // When an Video is picked
            else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


    private  String getRealPathFromURI(Uri contenturi){
        String[] proj={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),contenturi,proj,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(column_index);
        return result;

    }

    private void uploadFile() {
        progressDialog.showProgressDialog();

        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(imagePath);


        // Parsing any Media type file
        RequestBody photoContent = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(), photoContent);

        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Call<RequestBody> getpersionCall = RetrofitClient.getInstance().uploadUserProfilePhoto(photo,description);
        getpersionCall.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                RequestBody serverResponse = response.body();
                if (response.isSuccessful()) {

                    progressDialog.hideProgressDialog();
                    //assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.hideProgressDialog();
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }

    private  static void  verifyStoragePermission(Activity activity){
        int permission= ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }

}





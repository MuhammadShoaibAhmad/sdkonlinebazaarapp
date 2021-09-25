package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bazaar.sdkonlinebazaar.R;
import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilepicActivity extends AppCompatActivity {

    Button btnUpload, btnMulUpload, btnPickImage, btnPickVideo;
    String mediaPath, mediaPath1;
    private String imagePath;
    ImageView imgView;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    ProgressDialog progressDialog;
    TextView str1, str2;

    private static String[] PERMISSSION_STORAGE={
Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepic);


        verifyStoragePermission(ProfilepicActivity.this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        btnUpload =  findViewById(R.id.upload);
        btnMulUpload =  findViewById(R.id.uploadMultiple);
        btnPickImage =findViewById(R.id.pick_img);
        btnPickVideo =  findViewById(R.id.pick_vdo);
        imgView =  findViewById(R.id.preview);
        str1 =  findViewById(R.id.filename1);
        str2 =  findViewById(R.id.filename2);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 uploadFile();


            }
        });

        btnMulUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadMultipleFiles();
            }
        });

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                Intent result=Intent.createChooser(intent,"choose img");
                startActivityForResult(result, 10);
           /*     Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);*/
            }
        });

        // Video must be low in Memory or need to be compressed before uploading...
        btnPickVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });

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

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (ContextCompat.checkSelfPermission(ProfilepicActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                if (ActivityCompat.shouldShowRequestPermissionRationale(ProfilepicActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(ProfilepicActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            requestCode);
                }
            }
            // When an Image is picked
            else if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                str1.setText(mediaPath);
                // Set the Image in ImageView for Previewing the Media
                imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

            } // When an Video is picked
            else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                mediaPath1 = cursor.getString(columnIndex);
                str2.setText(mediaPath1);
                // Set the Video Thumb in ImageView Previewing the Media
                imgView.setImageBitmap(getThumbnailPathForLocalFile(ProfilepicActivity.this, selectedVideo));
                cursor.close();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }
*/

    // Providing Thumbnail For Selected Image
    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

    // Uploading Image/Video
    private void uploadFile() {
        progressDialog.show();

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

                    progressDialog.dismiss();
                    //assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private  static void  verifyStoragePermission(Activity activity){
        int permission=ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }



}

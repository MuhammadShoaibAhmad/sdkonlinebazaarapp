package com.bazaar.sdkonlinebazaar.ui.activities;


        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import com.bazaar.sdkonlinebazaar.R;

        import androidx.annotation.NonNull;
        import androidx.core.app.ActivityCompat;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.FragmentActivity;

        import android.Manifest;
        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.IntentSender;
        import android.content.pm.PackageManager;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Looper;
        import android.os.Message;
        import android.provider.Settings;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bazaar.sdkonlinebazaar.BuildConfig;
        import com.bazaar.sdkonlinebazaar.R;
        import com.bazaar.sdkonlinebazaar.constants.Constants;
        import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
        import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
        import com.bazaar.sdkonlinebazaar.ui.adapters.MarkerInfoWindowAdapter;
        import com.bazaar.sdkonlinebazaar.utils.BackgroundService;
        import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
        import com.bazaar.sdkonlinebazaar.utils.Utils;
        import com.bazaar.sdkonlinebazaar.utils.Wherebouts;
        import com.google.android.gms.common.api.ApiException;
        import com.google.android.gms.common.api.ResolvableApiException;
        import com.google.android.gms.location.LocationCallback;
        import com.google.android.gms.location.LocationRequest;
        import com.google.android.gms.location.LocationResult;
        import com.google.android.gms.location.LocationServices;
        import com.google.android.gms.location.LocationSettingsRequest;
        import com.google.android.gms.location.LocationSettingsResponse;
        import com.google.android.gms.location.LocationSettingsStatusCodes;
        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.LatLngBounds;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.database.core.Context;
        import com.karumi.dexter.Dexter;
        import com.karumi.dexter.PermissionToken;
        import com.karumi.dexter.listener.PermissionDeniedResponse;
        import com.karumi.dexter.listener.PermissionGrantedResponse;
        import com.karumi.dexter.listener.PermissionRequest;
        import com.karumi.dexter.listener.single.PermissionListener;

        import java.text.DateFormat;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;


public class MarrageActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    DrawerLayout navDrawer;
    private ProgressDialog progressDialog;
    private LinearLayout settingLayout;
    private List<PersionResponse> allpersionList;
    private LatLngBounds.Builder builder;
    private Marker mar;

    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    public final static int SENDING = 1;

    private TextView menuName;
    private PersionResponse per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marrage);
        bindViews();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_containerlive);
        mapFragment.getMapAsync(this);


        getAllPersion();

        try{
            init();
        }
        catch (Exception ex){

        }
        try{
            restoreValuesFromBundle(savedInstanceState);
        }
        catch (Exception ex){

        }
        try{

            startLocationButtonClick();
        }
        catch (Exception ex){

        }











        try{
            ImageView hamMenu = findViewById(R.id.open_drawer_button);
            hamMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navDrawer = findViewById(R.id.drawer_layoutlive);
                    // If the navigation drawer is not open then open it, if its already open then close it.
                    if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
                    else navDrawer.closeDrawer(Gravity.LEFT);
                }
            });

            mHandler = new Handler();
            startRepeatingTask();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {

                        case SENDING:

                            break;

                    }

                }
            };

            //  Wherebouts.instance().onChange(workable);
            // startService(new Intent(this, BackgroundService.class));
        }catch (Exception ex){

        }


    }

    private void bindViews() {
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);
        settingLayout=(LinearLayout) findViewById(R.id.settingLayoutlive);
        per=new PersionResponse();
        menuName = findViewById(R.id.menuName);
        menuName.setText(Constants.Name);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device.
     * This method will only be triggered once the user has installed
     Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        MarkerInfoWindowAdapter markerInfoWindowAdapter = new MarkerInfoWindowAdapter(getApplicationContext());
        mMap.setInfoWindowAdapter(markerInfoWindowAdapter);
        // Add a marker in Sydney and move the camera
    /*    LatLng TutorialsPoint = new LatLng(21, 57);
        mMap.addMarker(new
                MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));*/
    }

    protected Marker createMarker(double latitude, double longitude,String snippet,String Name,int ID) {


        if (mar != null && !hashMapMarker.isEmpty()) {

            if(hashMapMarker.containsKey(ID)){
                Marker marker = hashMapMarker.get(ID);
                Log.i("m value:", "m value " + mar);
                marker.remove();
                hashMapMarker.remove(ID);
            };

        }

        mar = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(Name)
                .snippet(snippet)
                //.icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_icon)));

        return mar;
    }


    public void centerallmarkers(View view){
        if(Constants.allpersionList.size()>1) {
            LatLngBounds bounds = builder.build();
            int padding = 60; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);
        }

        Log.i("hashMapMarker Result :", "hashMapMarker = " + hashMapMarker);
    }


    public void onZoomlive(View view){
        int id=view.getId();
        if (id == R.id.fabPluslive) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        } else if (id == R.id.fabMinuslive) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
        float zoom = mMap.getCameraPosition().zoom;
        Log.i("zoomlevel :", "zoomlevel " + zoom);
    }



    private boolean ismaptypenormal=true;
    public void ChangeMapTypelive(View view){
        if(ismaptypenormal){
            ismaptypenormal=false;
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }else {
            ismaptypenormal=true;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

    }


    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                getAllPersion(); //this function can change value of mInterval.
            }
            catch (Exception ex){


            }finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    int oldtrackid=0;
    HashMap<Integer, Marker> hashMapMarker = new HashMap<>();
    private void getAllPersion() {

        try{
            // progressDialog.showProgressDialog();
            Call<List<PersionResponse>> getpersionCall = RetrofitClient.getInstance().GetPersonByModuleId(3);
            getpersionCall.enqueue(new Callback<List<PersionResponse>>() {
                @Override
                public void onResponse(Call<List<PersionResponse>> call, Response<List<PersionResponse>> response) {
                    progressDialog.hideProgressDialog();
                    if (response != null) {
                        allpersionList = response.body();
                        Constants.allpersionList=allpersionList;

                        AsyncCaller asas=new AsyncCaller();
                        asas.doInBackground();




                        if (Utils.isValidList(Constants.allpersionList)) {
                            builder = new LatLngBounds.Builder();


                            for (int i = 0; i < Constants.allpersionList.size(); i++) {


                                PersionResponse temp = Constants.allpersionList.get(i);
                       /*     Log.d("List Result :", "index " + i);
                            Log.i("hashMapMarker :", "hashMapMarker " + hashMapMarker);

                            Log.d("List Result :", "First = " + temp.getRegNo());*/
                                // Marker marker = mMap.addMarker(markerOptions);





                              /*  if (m != null) {
                                    m.remove();
                                }*/
                                LatLng sydney = new LatLng(temp.getLatitude(), temp.getLongitude());

                                String snippet= "Name: "+temp.getName()+"\n"+
                                        "FatherName: " + temp.getFatherName()+"\n"+
                                        "Email: "+temp.getEmail()+"\n"+
                                        "Mobile: "+temp.getMobile()+"\n"+
                                        "Education: "+temp.getEducation()+"\n"+
                                        "onthlyIncome: "+temp.getMonthlyIncome()+"\n";
                                mar =createMarker(temp.getLatitude(), temp.getLongitude(),  snippet,temp.getName(),temp.getId());

                                builder.include(sydney);
                                if(Constants.allpersionList.size()==1){
                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(16).build();
                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                }
                                // LoconLocationChanged(temp.getLatitude(),temp.getLongitude());



                                /*   if(temp.getEventName().contains("Tracking"))*/
                                hashMapMarker.put(temp.getId(),mar);

                                oldtrackid=temp.getId();


                            }
                            mMap.setOnMarkerClickListener(MarrageActivity.this);
                            if(Constants.allpersionList.size()>1 && Constants.isAlreadyBoud){
                                Constants.isAlreadyBoud=false;
                                LatLngBounds bounds = builder.build();
                                int padding = 60; // offset from edges of the map in pixels
                                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                                mMap.animateCamera(cu);
                            }


                        }



                    } else {
                        // Utils.showSnackBar(AllVehiclesActivity.this, "Invalid Response ..!!");

                        Toast.makeText(MarrageActivity.this, "Invalid Response ..!!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<List<PersionResponse>> call, Throwable t) {
                    progressDialog.hideProgressDialog();
                    Utils.showSnackBar(MarrageActivity.this, "Something Went Wrong ..!!");

                    Toast.makeText(MarrageActivity.this, "Something Went Wrong ..!!" +t, Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception ex){
        }


    }



    void startRepeatingTask() {
        mStatusChecker.run();
    }
    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        //ProgressDialog pdLoading = new ProgressDialog(AsyncExample.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            //pdLoading.setMessage("\tLoading...");
            // pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

            // runlistviews();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            // pdLoading.dismiss();
        }

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

       /* if (marker.equals(m))
        {*/
        //showPersionnavigation();

        LatLng sydney = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        marker.showInfoWindow();
        //handle click here

      /*  for (int i=0; i < 5; i++)
        {
            Toast.makeText(this, marker.getSnippet(), Toast.LENGTH_LONG).show();
        }*/


        /*  }*/
        return  true;
    }

    private void showPersionnavigation(){
        settingLayout.setVisibility(View.VISIBLE);
    }
    private boolean isshow=true;
    public void hidePersionnavigation(View view){


        if(isshow==true){
            // settingLayout.setVisibility(View.VISIBLE);
            isshow=false;
        }else {
            settingLayout.setVisibility(View.INVISIBLE);
            isshow=true;
        }
    }


    public void signOut(View view) {
        Intent i = new Intent(MarrageActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MarrageActivity.this.startActivity(i);
    }









    //location getting process
    private String mLastUpdateTime;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private void init() {
        try {
            Constants.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MarrageActivity.this);
            Constants.mSettingsClient = LocationServices.getSettingsClient(MarrageActivity.this);

            Constants.mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    // location is received
                    Constants.mCurrentLocation = locationResult.getLastLocation();
                    mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                    updateLocationUI();
                }
            };

            Constants.mRequestingLocationUpdates = false;

            Constants.mLocationRequest = new LocationRequest();
            Constants.mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            Constants.mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            Constants.mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(Constants.mLocationRequest);
            Constants.mLocationSettingsRequest = builder.build();
        }
        catch (Exception ex){

        }


    }
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                Constants.mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                Constants.mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

        updateLocationUI();
    }
    private void updateLocationUI() {

        try{
            if (Constants.mCurrentLocation != null) {

                Log.e("Started location updates","Started location updates!="+Constants.mCurrentLocation.getLatitude());
         /*   per.setLatitude(Constants.mCurrentLocation.getLatitude());
            per.setLongitude(Constants.mCurrentLocation.getLongitude());
            per.setId(Constants.ID);*/
                double Latitude=Constants.mCurrentLocation.getLatitude();
                double Longitude=Constants.mCurrentLocation.getLongitude();
                int ID=Constants.ID;
                Call<String> updateResponseCall = RetrofitClient.getInstance().UpdateLatLong(ID,Latitude,Longitude);
                updateResponseCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        progressDialog.hideProgressDialog();
                        if (response.body() != null) {

                        } else {
                            Utils.showSnackBar(MarrageActivity.this, "Invalid user ..!!");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progressDialog.hideProgressDialog();
                        Utils.showSnackBar(MarrageActivity.this, "Something went wrong ..!!");
                        Log.e("TAG", t.getMessage());
                    }
                });
            }
        }
        catch (Exception ex){

        }



    }
    private void startLocationUpdates() {

        try{
            Constants.mSettingsClient
                    .checkLocationSettings(Constants.mLocationSettingsRequest)
                    .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            Log.i("TAG", "All location settings are satisfied.");

                            Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_LONG).show();

                            //noinspection MissingPermission
                            Constants.mFusedLocationClient.requestLocationUpdates(Constants.mLocationRequest,
                                    Constants.mLocationCallback, Looper.myLooper());

                            updateLocationUI();
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    Log.i("TAG", "Location settings are not satisfied. Attempting to upgrade " +
                                            "location settings ");
                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().
                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        rae.startResolutionForResult(MarrageActivity.this, Constants.REQUEST_CHECK_SETTINGS);
                                    } catch (IntentSender.SendIntentException sie) {
                                        Log.i("TAG", "PendingIntent unable to execute request.");
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    String errorMessage = "Location settings are inadequate, and cannot be " +
                                            "fixed here. Fix in Settings.";
                                    Log.e("TAG", errorMessage);

                                    Toast.makeText(MarrageActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                            }

                            updateLocationUI();
                        }
                    });
        }
        catch (Exception ex){


        }

    }
    private void startLocationButtonClick() {

        try {
            // Requesting ACCESS_FINE_LOCATION using Dexter library
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Constants.mRequestingLocationUpdates = true;
                            startLocationUpdates();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            if (response.isPermanentlyDenied()) {
                                // open device settings when the permission is
                                // denied permanently
                                openSettings();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        }
        catch (Exception ex){

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case Constants.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e("TAG", "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e("TAG", "User chose not to make required location settings changes.");
                        Constants.mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (Constants.mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();
    }
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void eventprofileclick(View view){
        Intent intent = new Intent(MarrageActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void contectUstitleclick(View view){
        Intent intent = new Intent(MarrageActivity.this, ContactusActivity.class);
        startActivity(intent);
    }

    public void gotoFoodActivity(View view){
        Intent intent = new Intent(MarrageActivity.this, FoodActivity.class);
        startActivity(intent);
    }
    public void gotoJobActivity(View view){
        Intent intent = new Intent(MarrageActivity.this, JobActivity.class);
        startActivity(intent);
    }
    public void gotoMarrageActivity(View view){
        Intent intent = new Intent(MarrageActivity.this, MarrageActivity.class);
        startActivity(intent);
    }
    public void gotoMuslimActivity(View view){
   /*     Intent intent = new Intent(MarrageActivity.this, FoodActivity.class);
        startActivity(intent);*/
    }
    public void gotoChristianActivity(View view){
     /*   Intent intent = new Intent(MarrageActivity.this, JobActivity.class);
        startActivity(intent);*/
    }

    public void gotoHinduActivity(View view){
      /*  Intent intent = new Intent(MarrageActivity.this, JobseekerActivity.class);
        startActivity(intent);*/
    }
    public void gotoSikhActivity(View view){
       /* Intent intent = new Intent(MarrageActivity.this, SpecializdActivity.class);
        startActivity(intent);*/
    }
    public void gotoBuddhistActivity(View view){
 /*       Intent intent = new Intent(MarrageActivity.this, SpecializdActivity.class);
        startActivity(intent);*/
    }

}

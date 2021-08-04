package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bazaar.sdkonlinebazaar.R;
import com.bazaar.sdkonlinebazaar.constants.Constants;
import com.bazaar.sdkonlinebazaar.data.Network.RetrofitClient;
import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;
import com.bazaar.sdkonlinebazaar.ui.adapters.MarkerInfoWindowAdapter;
import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
import com.bazaar.sdkonlinebazaar.utils.Utils;
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

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainmapActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    DrawerLayout navDrawer;
    private ProgressDialog progressDialog;
    private List<PersionResponse> allpersionList;
    LatLngBounds.Builder builder;
    private Marker mar;

    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    public final static int SENDING = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        bindViews();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_containerlive);
        mapFragment.getMapAsync(this);


        getAllPersion();











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
    }

    private void bindViews() {
        progressDialog = new ProgressDialog(this, Constants.verifyMsg);
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

    protected Marker createMarker(double latitude, double longitude,String snippet,String Name) {


     /*   if (mar != null && !hashMapMarker.isEmpty()) {

            if(hashMapMarker.containsKey(iconResID)){
                Marker marker = hashMapMarker.get(iconResID);
                Log.i("m value:", "m value " + mar);
                marker.remove();
                hashMapMarker.remove(iconResID);
            };

        }*/

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
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    int oldtrackid=0;
    HashMap<Integer, Marker> hashMapMarker = new HashMap<>();
    public void getAllPersion() {
        // progressDialog.showProgressDialog();
        Call<List<PersionResponse>> getpersionCall = RetrofitClient.getInstance().getPersionInfo();
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




                            if( oldtrackid != temp.getId()){

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
                                mar =createMarker(temp.getLatitude(), temp.getLongitude(),  snippet,temp.getName());

                                builder.include(sydney);
                                if(Constants.allpersionList.size()==1){
                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(16).build();
                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                }
                                // LoconLocationChanged(temp.getLatitude(),temp.getLongitude());


                            }
                            /*   if(temp.getEventName().contains("Tracking"))*/
                            hashMapMarker.put(temp.getId(),mar);

                            oldtrackid=temp.getId();


                        }
                        mMap.setOnMarkerClickListener(MainmapActivity.this);
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

                    Toast.makeText(MainmapActivity.this, "Invalid Response ..!!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<PersionResponse>> call, Throwable t) {
                progressDialog.hideProgressDialog();
                Utils.showSnackBar(MainmapActivity.this, "Something Went Wrong ..!!");

                Toast.makeText(MainmapActivity.this, "Something Went Wrong ..!!" +t, Toast.LENGTH_SHORT).show();
            }
        });


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
       // showSlidingnavigation();

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


    public void signOut(View view) {
        Intent i = new Intent(MainmapActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MainmapActivity.this.startActivity(i);
    }
}
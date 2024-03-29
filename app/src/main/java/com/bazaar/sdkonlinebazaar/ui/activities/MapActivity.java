package com.bazaar.sdkonlinebazaar.ui.activities;


        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.Manifest;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.Point;
        import android.graphics.drawable.BitmapDrawable;
        import android.location.Location;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.os.SystemClock;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.view.animation.Interpolator;
        import android.view.animation.LinearInterpolator;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bazaar.sdkonlinebazaar.R;
        import com.bazaar.sdkonlinebazaar.utils.ProgressDialog;
        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.common.api.PendingResult;
        import com.google.android.gms.common.api.Status;
        import com.google.android.gms.location.LocationListener;
        import com.google.android.gms.location.LocationRequest;
        import com.google.android.gms.location.LocationServices;
        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.Projection;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.LatLngBounds;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Map;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class MapActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;

    private Handler handler;
    private Marker m;

    private ProgressDialog progressDialog;


    DrawerLayout navDrawer ;



    private Bitmap smallMarker;

    LatLngBounds.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_containerlive);
        mapFragment.getMapAsync(this);




        ImageView hamMenu = findViewById(R.id.open_drawer_button);
        ImageView closeDrawer = findViewById(R.id.mainprofilepic);


        hamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navDrawer = findViewById(R.id.drawer_layoutlive);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(Gravity.LEFT);


            }
        });





    }


    private void initgmap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_containerlive);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try{
            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                private float currentZoom = -1;

                @Override
                public void onCameraChange(CameraPosition pos) {
                    if (pos.zoom != currentZoom){
                        currentZoom = pos.zoom;

                        Log.i("currentZoom:", "currentZoom " + currentZoom);
                        if(currentZoom >=1.0 && currentZoom <= 12.0){

                            int height = 55;
                            int width = 30;
                            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_default);
                            Bitmap b = bitmapdraw.getBitmap();
                            smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                        }else if(currentZoom >12.0 && currentZoom < 16.0) {
                            int height = 75;
                            int width = 40;
                            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_default);
                            Bitmap b = bitmapdraw.getBitmap();
                            smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        }else if(currentZoom >=17.0){
                            int height = 80;
                            int width = 45;
                            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_default);
                            Bitmap b = bitmapdraw.getBitmap();
                            smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        }else{
                            int height = 75;
                            int width = 40;
                            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_default);
                            Bitmap b = bitmapdraw.getBitmap();
                            smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                        }

                        // do you action here
                    }
                }
            });
        }
        catch (Exception ex){
        }


    }

    protected Marker createMarker(double latitude, double longitude, String title, String snippet, int iconResID,float rotation) {


     /*   if (m != null && !hashMapMarker.isEmpty()) {

            if(hashMapMarker.containsKey(iconResID)){
                Marker marker = hashMapMarker.get(iconResID);
                Log.i("m value:", "m value " + m);
                marker.remove();
                hashMapMarker.remove(iconResID);
            };



        }*/

        m = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .rotation(rotation)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.livecaricon)));

        return m;
    }



    public void vehicleStatusbtnClick(View view){

        finish();
        startActivity(getIntent());
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {



        LatLng sydney = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        marker.showInfoWindow();

        return  true;
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






}



package dizhang.com.example.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.ElasticSearchEvent;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.MMP;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

import static java.lang.Thread.sleep;

/**
 * Class Name: MapActivity
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */
/**
 * Represents a MapActivity
 * @version 1.0
 * @see AppCompatActivity
 * @since 1.0
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , LocationListener {

    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    ArrayList<MMP> pass2Map = new ArrayList<MMP>();
    ArrayList<MMP> mainMap = new ArrayList<MMP>();

    User currentUser = new User();
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayList<Event> userEvent = new ArrayList<Event>();
    LatLng ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(googleServicesAvailable()) {
            Log.d("googleService","Available!");
            setContentView(R.layout.activity_map);
            iniMap();
        }
        else {
            Log.d("googleService","NOT Available :(");
        }

        try{
            //String test = getIntent().toString();
            //Log.d("testIntent", getIntent().toString());
            pass2Map = (ArrayList<MMP>) getIntent().getSerializableExtra("pass2Map");
        }
        catch (Exception e){
            //nothing
        }



        }



    private void iniMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }


    /**
     *
     * @return
     *
     * https://www.youtube.com/watch?v=lchyOhPREh4&t=918s
     */

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        }
        else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        }
        else {
            Toast.makeText(this, "Cannot connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("Ready","OnMapReady!");
        mGoogleMap = googleMap;
        mGoogleMap.setMyLocationEnabled(true);
        //Location myLocation = mGoogleMap.getMyLocation();

        //LatLng myLatlng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        //ArrayList<MMP> pass2Map = (ArrayList<MMP>) getIntent().getSerializableExtra("pass2Map");

        Log.d("final","finally out of this shiet");

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                                .addApi(LocationServices.API)
                                .addConnectionCallbacks(this)
                                .addOnConnectionFailedListener(this)
                                .build();

        mGoogleApiClient.connect();



        if (pass2Map!=null) {

            for (int i = 0; i < pass2Map.size(); i++) {
                //Log.d("pass2Map", pass2Map.get(i).getTitle());
                ArrayList<String> loc = pass2Map.get(i).getLocation();
                try {
                    double lon = Double.parseDouble(loc.get(0));
                    double lat = Double.parseDouble(loc.get(1));
                    MarkerOptions options = new MarkerOptions()
                            .title(pass2Map.get(i).getTitle())
                            .position(new LatLng(lat, lon))
                            .snippet(pass2Map.get(i).getUsername());
                    mGoogleMap.addMarker(options);
                    if (i == pass2Map.size() - 1) {
                        goToLocationZoom(new LatLng(lat,lon), 8);
                    }
                } catch (NumberFormatException ex) {
                    Log.d("MapLatlng", "CANNOT CONVERT string -> double");
                }
            }
        }

        //goToLocationZoom(myLatlng, 8);
    }

    /**
     * @param ll
     * @param zoom
     */
    private void goToLocationZoom(LatLng ll, float zoom) {
        //LatLng ll = new LatLng(lat, lon);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);

//        MarkerOptions options = new MarkerOptions()
//                .title("Username")
//                .position(new LatLng(lat, lon))
//                .snippet("some habit");
//        mGoogleMap.addMarker(options);

    }

    @Override
    public void onBackPressed() {
        mGoogleMap.clear();
        super.onBackPressed();
    }

    /**
     *
     * @param lat
     * @param lon
     */

    private void goToLocation(double lat, double lon) {
        LatLng ll = new LatLng(lat, lon);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    LocationRequest mLocationRequest;

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location == null) {
            Toast.makeText(this, "cannot get location", Toast.LENGTH_LONG).show();
        }
        else{
            ll = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
            mGoogleMap.animateCamera(update);
            if (pass2Map==null){
                mGoogleMap.clear();
                for (int i = 0; i < mainMap.size(); i++) {
                    //Log.d("pass2Map", pass2Map.get(i).getTitle());
                    ArrayList<String> loc = mainMap.get(i).getLocation();
                    try {
                        double lon = Double.parseDouble(loc.get(0));
                        double lat = Double.parseDouble(loc.get(1));
                        float[] result = new float[1];
                        Location.distanceBetween(lat, lon, ll.latitude, ll.longitude, result);
                        if(result[0]<=5000){
                            MarkerOptions options = new MarkerOptions()
                                .title(mainMap.get(i).getTitle())
                                .position(new LatLng(lat, lon))
                                .snippet(mainMap.get(i).getUsername());
                            mGoogleMap.addMarker(options);
                        }
                    } catch (NumberFormatException ex) {
                        Log.d("MapLatlng", "CANNOT CONVERT string -> double");
                    }
                }

            }


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String username = LoginActivity.uname;
        ElasticSearchController.getUser getUser = new ElasticSearchController.getUser();
        getUser.execute(username);
        try{
            currentUser = getUser.get();
        }catch (Exception e){
            Log.i("Error","error getting user");
        }
        listItem.clear();
        mainMap.clear();
        //pass2Map.clear();
        //get all the name that current user is following and search each username to get all the Event
        listItem = currentUser.getFollowee();
        listItem.add(username);


        for (int i = 0 ; i < listItem.size(); i++) {
            ElasticSearchEvent.getEventTask getEventTask = new ElasticSearchEvent.getEventTask();
            getEventTask.execute(listItem.get(i));
            try{
                userEvent = getEventTask.get();
            } catch (Exception e) {
                Log.i("Error", "failed to get Event from the async object");
            }

            for(int x = 0 ; x<userEvent.size();x++){
                if (userEvent.get(x).getLocation()!=null){
                    MMP mmp = new MMP();
                    mmp.setTitle(userEvent.get(userEvent.size()-1).getTitle());
                    mmp.setLocation(userEvent.get(userEvent.size()-1).getLocation());
                    mmp.setUsername(userEvent.get(userEvent.size()-1).getUsername());
                    mainMap.add(mmp);
                    Log.d("mainMap",mainMap.get(0).getTitle());
                    Log.d("mainMap",mainMap.get(0).getUsername());
                    Log.d("mainMap",mainMap.get(0).getLocation().toString());
                }
            }

        }
    }




}

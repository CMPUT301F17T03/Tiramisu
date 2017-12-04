package dizhang.com.example.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.MMP;
import dizhang.com.example.tiramisu.R;

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

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    ArrayList<MMP> pass2Map = new ArrayList<MMP>();

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
        pass2Map = (ArrayList<MMP>) getIntent().getSerializableExtra("pass2Map");
        }
        catch (Exception e){
            //nothing
        }
//        for (int i = 0 ; i < pass2Map.size(); i++){
//            Log.d("pass2Map",pass2Map.get(i).getTitle());
//        }

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
}

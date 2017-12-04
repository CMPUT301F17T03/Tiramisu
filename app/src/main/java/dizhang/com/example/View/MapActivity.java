package dizhang.com.example.View;

import android.app.Dialog;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("Ready","OnMapReady!");
         mGoogleMap = googleMap;
         goToLocationZoom(53.542664, -113.508041, 15);
    }

    private void goToLocationZoom(double lat, double lon, float zoom) {
        LatLng ll = new LatLng(lat, lon);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);

    }

    private void goToLocation(double lat, double lon) {
        LatLng ll = new LatLng(lat, lon);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }
}

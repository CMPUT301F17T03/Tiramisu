package dizhang.com.example.Control;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ChangedPackages;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.View.EventManagerActivity;
import dizhang.com.example.View.EventTodayActivity;
import dizhang.com.example.View.HabitManagerActivity;
import dizhang.com.example.View.HabitViewActivity;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EditEventActivity
 *
 * Created by dz2 on 2017-11-06.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

/**
 * Represents a EditEventActivity
 *
 * @version 1.0
 * @see AppCompatActivity
 * @see EventManagerActivity
 * @since 1.0
 */

public class EditEventActivity extends AppCompatActivity {
    private static final String FILENAME = "Event.save";


    Button Delete, Save, changeLocation;
    EditText editCom;
    TextView editTitle, locationDisplay;
    ImageView Image;
    //Location myLocation;
    double lon, lat;
    private LocationManager locationManager;
    private LocationListener locationListener;
    ArrayList<String> newLocation = new ArrayList<String>();
    ArrayList<Event> newList = new ArrayList<Event>();
    String ImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        loadFromFile();
        int index = getIntent().getIntExtra("index", 0);
        Log.d("title", newList.get(index).getTitle());
        Log.d("comment", newList.get(index).getComment());
        String title = newList.get(index).getTitle();
        String comment = newList.get(index).getComment();
        ImageString = newList.get(index).getPicture();
        if (newList.get(index).getLocation() == null){

        }
        else{
            Log.d("else", "elseInViewheyyyyyyyyyyyyyyyy");
            newLocation = newList.get(index).getLocation();
            //int lat = (int) Math.ceil(loc.getLatitude());
            //int lon = (int) Math.ceil(loc.getLongitude());

            Log.d("read", "read okay!!!!!!!!! but setText no...");
            //locationView.setText("(" + lat + "  ,  " + lon + ")");
        }



        editTitle = (TextView) findViewById(R.id.eventTitleE);
        //locationDisplay = (TextView) findViewById(R.id.locationToday);
        editCom = (EditText) findViewById(R.id.commentE);
        Delete = (Button) findViewById(R.id.Delete);
        changeLocation = (Button) findViewById(R.id.changeLocation);
        Save = (Button) findViewById(R.id.Save);
        Image  = (ImageView)findViewById(R.id.evenImageE);
        editTitle.setText(title);
        editCom.setText(comment);

        byte[] decodedString = Base64.decode(ImageString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Image.setImageBitmap(decodedByte);


        //Image.setImageBitmap(newList.get(index).getPicture());


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //locationToday.clearComposingText();
                newLocation.clear();
                lon = Math.round(location.getLongitude() * 100.0 / 100.0);
                lat = Math.round(location.getLatitude() * 100.0 / 100.0);
                //locationDisplay.setText("(Longitude: " + lon + ", Latitude: " + lat + " )");

                String latString = Double.toString(lat);
                String lonString = Double.toString(lon);
                newLocation.add(lonString);
                newLocation.add(latString);


                Log.d("Onlocation", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("Case", "Case222222222222222222222222222222");

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("Case", "Case2222222222222222222222222222223");

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET
                }, 2);
                return;
            }
        }
        /**
         * user change the location with the change button
         */
        changeLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps", 5000, 10, locationListener);

                Toast.makeText(getBaseContext(), "Location changed to current location", Toast.LENGTH_LONG).show();
            }
        });

        /**
         * user save the event with the save button
         */
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                String comment  = editCom.getText().toString();

                if (comment.length() > 20) {
                    Toast.makeText(EditEventActivity.this, "Comment has to be not more than 20 characters", Toast.LENGTH_LONG).show();
                    return;
                }


                newList.get(index).setPicture(ImageString);

                newList.get(index).setComment(comment);
                newList.get(index).setLocation(newLocation);
                //Log.d("location", myLocation.toString());


                ElasticSearchEvent.updateEventTask updateEventTask = new ElasticSearchEvent.updateEventTask();
                updateEventTask.execute(newList.get(index));
                saveInFile();

                Intent intent = new Intent(EditEventActivity.this, EventManagerActivity.class);

                startActivity(intent);

            }
        });
        /*
            *user pick an image
         */
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
        /**
         * user delete the envent
         */
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                Intent intent = new Intent(EditEventActivity.this, EventManagerActivity.class);

                ElasticSearchEvent.delEventTask    delEventTask = new ElasticSearchEvent.delEventTask();
                delEventTask.execute(newList.get(index));

                newList.remove(index);
                saveInFile();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    //configureButton();
                return;
        }
    }


    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            newList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override

    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] byteImage = baos.toByteArray();
                ImageString = Base64.encodeToString(byteImage, Base64.DEFAULT);
                Image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(EditEventActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(EditEventActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(newList,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

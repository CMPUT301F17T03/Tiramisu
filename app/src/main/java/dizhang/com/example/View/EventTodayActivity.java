package dizhang.com.example.View;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import dizhang.com.example.Control.ElasticSearchEvent;
import dizhang.com.example.Control.ElasticSearchHabit;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EventTodayActivity
 *
 * Created by dz2 on 2017-11-13.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

/**
 * Represents a EventTodayActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see EventManagerActivity
 * @since 1.0
 */

public class EventTodayActivity extends AppCompatActivity {

    private static final String EventFile = "Event.save";
    private static final String HabitFILE = "Habit.save";
    //User CurrentUser = new User();
    ArrayList<Habit> habitList = new ArrayList<Habit>();
    ArrayList<Event> EventList = new ArrayList<Event>();
    ArrayList<String> newLocation = new ArrayList<String>();
    public String realPath,ImageString;
    Habit Current_Habit = new Habit();
    double lon, lat;
    Date date;
    Button addLocation, Complete;
    EditText comment;
    TextView eventTitle, locationToday;
    ImageView Image;
    String myLocation;
    private static int index = -1;
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name = getIntent().getStringExtra("name");
        loadFromFile();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_today);
        loadFromHabit();
        for (int i = 0; i < habitList.size(); i++) {
            if (habitList.get(i).getTitle().equals(name)) {
                Current_Habit = habitList.get(i);
                index = i;
            }
        }

        //picture = (Button) findViewById(R.id.picture);
        eventTitle = (TextView) findViewById(R.id.eventTitle);
        addLocation = (Button) findViewById(R.id.addLocation);
        comment = (EditText) findViewById(R.id.comment);
        Complete = (Button) findViewById(R.id.Complete);
        Image = (ImageView) findViewById(R.id.Image);
        locationToday = (TextView) findViewById(R.id.locationToday);
        eventTitle.setText(name);
        //ImageString = "empty";

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //locationToday.clearComposingText();
                //newLocation.clear();
                lon = location.getLongitude();
                lat = location.getLatitude();
                locationToday.setText("(Longitude: " + lon + ", Latitude: " + lat + " )");
                Log.d("Onlocation", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("Case", "Case222222222222222222222222222222");

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("Case", "Case222222222222222222222222222222");

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

        addLocation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps", 5000, 10, locationListener);
            }
        });


        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });


        Complete.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                String comm = comment.getText().toString();


                if (comment.length() > 20) {
                    Toast.makeText(EventTodayActivity.this, "Comment has to be not more than 20 characters", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(EventTodayActivity.this, EventManagerActivity.class);
                startActivity(intent);
                Calendar c = new GregorianCalendar();
                c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                date = c.getTime();
                Event newEvent = new Event(Current_Habit.getTitle(), date, comm);

                //Lon and lat converted into String here:
                String latString = Double.toString(lat);
                String lonString = Double.toString(lon);
                Log.d("dataTypeCheck", latString);

                newLocation.add(lonString);
                newLocation.add(latString);

                newEvent.setPicture(ImageString);
                newEvent.setUsername(LoginActivity.uname);

                newEvent.setLocation(newLocation);

                EventList.add(newEvent);

                ElasticSearchEvent.addEventTask addEventTask = new ElasticSearchEvent.addEventTask();
                addEventTask.execute(newEvent);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String Today_date = dateFormat.format(date);
                habitList.get(index).setLast(Today_date);

                ElasticSearchHabit.updateHabitTask updateHabitTask = new ElasticSearchHabit.updateHabitTask();
                updateHabitTask.execute(habitList.get(index));

                saveInFile();
                SaveHabit();

            }
        });

    }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case 2:
                    if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        //configureButton();
                    return;
            }
        }



    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();


                realPath=getRealPathFromURI(imageUri);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteImage = baos.toByteArray();
                ImageString = Base64.encodeToString(byteImage, Base64.DEFAULT);


                Toast.makeText(EventTodayActivity.this, realPath, Toast.LENGTH_LONG).show();

                Image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(EventTodayActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(EventTodayActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    private void loadFromFile(){
        try{

            FileInputStream fis = openFileInput(EventFile);
            Log.d("myTag", "This is my message");
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type Usertype = new TypeToken<ArrayList<Event>>(){}.getType();
            EventList = gson.fromJson(in,Usertype);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromHabit(){
        try{

            FileInputStream fis = openFileInput(HabitFILE);
            Log.d("myTag", "This is my message");
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type Usertype = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in,Usertype);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void SaveHabit(){
        try{
            FileOutputStream fos = openFileOutput(HabitFILE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(habitList,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(EventFile, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(EventList,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
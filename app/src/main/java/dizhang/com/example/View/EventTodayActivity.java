package dizhang.com.example.View;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import dizhang.com.example.Control.EditEventActivity;
import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
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
 * Represents a MapActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see EventManagerActivity
 * @since 1.0
 */

public class EventTodayActivity extends AppCompatActivity {
    private static final String FILENAME = "event.save";
    private static final String HabitFILENAME = "file.save";

    public String realPath;
    public Habit Current_Habit;
    Date date;
    Button addLocation, Complete, picture;
    EditText comment;
    TextView eventTitle, locationToday;
    ImageView Image;
    Location myLocation;
    private LocationManager locationManager;
    private LocationListener locationListener;

    ArrayList<Event> newList = new ArrayList<Event>();
    ArrayList<Habit> habitList = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name = getIntent().getStringExtra("name");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_today);
        loadFromFile();
        for (int i = 0; i < habitList.size(); i++) {
            if (habitList.get(i).getTitle().equals(name)) {
                Current_Habit = habitList.get(i);
            }
        }
        loadFromEventFile();
        picture = (Button) findViewById(R.id.picture);
        eventTitle = (TextView) findViewById(R.id.eventTitle);
        addLocation = (Button) findViewById(R.id.addLocation);
        comment = (EditText) findViewById(R.id.comment);
        Complete = (Button) findViewById(R.id.Complete);
        Image = (ImageView) findViewById(R.id.Image);
        locationToday = (TextView) findViewById(R.id.locationToday);
        eventTitle.setText(name);
        realPath = "empty";

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //locationToday.clearComposingText();
                myLocation = location;
                int lat = (int) Math.ceil(location.getLatitude());
                int lon = (int) Math.ceil(location.getLongitude());
                locationToday.setText("(" + lat + "  ,  " + lon + ")");
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
        } else {
            configureButton();
        }


        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configureButton();
            }
        });


        picture.setOnClickListener(new View.OnClickListener() {
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
                Event newEvent = new Event(Current_Habit, date, comm);
                newEvent.setTitle(Current_Habit.getTitle());
                newEvent.setPicture(realPath);
                newEvent.setLocation(myLocation);
                newList.add(newEvent);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String Today_date = dateFormat.format(date);
                Current_Habit.setLast(Today_date);
                saveInFile();
                saveHabit();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
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
            FileInputStream fis = openFileInput(HabitFILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in,listType);
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
    private void loadFromEventFile(){
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
    private void saveHabit(){
        try{
            FileOutputStream fos = openFileOutput(HabitFILENAME, 0);
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

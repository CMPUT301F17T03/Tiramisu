package dizhang.com.example.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Control.EditEventActivity;
import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EventViewActivity
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
 * Represents a EventViewActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see EditEventActivity
 * @since 1.0
 */

public class EventViewActivity extends AppCompatActivity {
    private static final String FILENAME = "Event.save";
    public Uri picture_Uri;
    private String path;
    Button Edit;
    ImageView Image;
    ArrayList<String> curLocation;
    String lat, lon;
    TextView eventTitleView,locationView,eventDateView,commentView;
    ArrayList<Event> newList = new ArrayList<Event>();
    public Event CurrntEvent;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        int index = getIntent().getIntExtra("index",0);
        eventTitleView = (TextView) findViewById(R.id.eventTitleView);
        locationView = (TextView) findViewById(R.id.locationView);
        eventDateView  = (TextView) findViewById(R.id.eventDateView);
        commentView = (TextView) findViewById(R.id.commentView);
        Image = (ImageView) findViewById(R.id.imageView);



        Edit = (Button) findViewById(R.id.EditEvent);
        loadFromFile();


        path = newList.get(index).getPicture();
        Permission();
        Toast.makeText(EventViewActivity.this, path, Toast.LENGTH_LONG).show();
        System.out.println(path);
        if ( !path.equals( "empty")) {
            showPicture();


        }

        String title = newList.get(index).getTitle();
        String des = newList.get(index).getComment();
        if (newList.get(index).getLocation() == null){

        }
        else{
            Log.d("else", "elseInViewheyyyyyyyyyyyyyyyy");
            curLocation = newList.get(index).getLocation();
            lon = curLocation.get(0);
            lat = curLocation.get(1);


            Log.d("read", "read okay!!!!!!!!! but setText no...");
            locationView.setText("Location: (Longitude: " + lon + ", Latitude: " + lat + " )");
        }

        //Location location = newList.get(index).getLocation();

        StringBuilder freq = new StringBuilder();

        Date date = newList.get(index).getDate();
        String startDate = date.toString();
        eventTitleView.setText(title);
        commentView.setText(des);
        eventDateView.setText(startDate);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                Intent edit = new Intent(getApplicationContext(), EditEventActivity.class);
                edit.putExtra("index", index);
                startActivity(edit);
            }
        });




    }
    private void showPicture(){
        File imgFile = new  File(path);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());



            Image.setImageBitmap(myBitmap);
        }
    }
    private void Permission(){
// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(EventViewActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(EventViewActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(EventViewActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1
                        );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
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
        Log.d("LoadFile","finished loadingFile");
    }


}

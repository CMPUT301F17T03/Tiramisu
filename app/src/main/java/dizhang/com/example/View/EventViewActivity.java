package dizhang.com.example.View;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * @since 1.0
 */

public class EventViewActivity extends AppCompatActivity {
    private static final String FILENAME = "event.save";

    Button Edit;
    ImageView Image;
    TextView eventTitleView,locationView,eventDateView,commentView;
    ArrayList<Event> newList = new ArrayList<Event>();

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

        Image.setImageBitmap(newList.get(index).getPicture());



        String title = newList.get(index).getTitle();
        String des = newList.get(index).getComment();

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
}

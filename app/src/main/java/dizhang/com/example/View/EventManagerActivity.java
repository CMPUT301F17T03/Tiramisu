package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.EventList;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.tiramisu.R;

import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * Class Name: EventManagerActivity
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
 * Represents a EventManagerActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see HomeActivity
 * @see EventViewActivity
 * @since 1.0
 */

public class EventManagerActivity extends AppCompatActivity {
    private static final String FILENAME = "Event.save";

    ListView eventList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<Event> newList = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_manager);

        eventList = (ListView) findViewById(R.id.eventList);


        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(EventManagerActivity.this, EventViewActivity.class);
                intent.putExtra("index",i);
                startActivity(intent);

            }
        });
    }

    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeInt);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        ElasticSearchController.GetUserProfile getUserProfile = new ElasticSearchController.GetUserProfile();
        getUserProfile.execute("what");
        try{
            userList = getUserProfile.get();

        } catch (Exception e){
            Log.i("Error","Failed to get users from the async object");
        }
        */
        //TODO get user from elasticsearch and get habit from user

        loadFromFile();
        listItem.clear();
        for (int i = 0 ; i < newList.size(); i++){
            Event event = newList.get(i);
            String title = event.getTitle();
            listItem.add(title);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item,listItem);
        eventList.setAdapter(adapter);
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

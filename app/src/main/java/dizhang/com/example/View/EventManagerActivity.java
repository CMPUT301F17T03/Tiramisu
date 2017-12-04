package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.ElasticSearchEvent;
import dizhang.com.example.Control.ElasticSearchHabit;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.EventList;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
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

    private static final String HabitFILE = "Habit.save";
    private static final String EventFile = "Event.save";
    private static final String FILENAME = "User.save";
    User user = new User();
    private static final String DelEvent = "DelEvent.save";
    private static final String DelHabit = "DelHabit.save";

    ListView eventList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<Event> EventList = new ArrayList<Event>();
    ArrayList<Event> DelEventList = new ArrayList<Event>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_manager);
        try {
            offlinecheck();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventList = (ListView) findViewById(R.id.eventList);

        /**
         * a list of event
         */
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

        loadFromEvent();
        listItem.clear();
        for (int i = 0 ; i < EventList.size(); i++){
            Event event = EventList.get(i);
            String title = event.getTitle();
            listItem.add(title);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item,listItem);
        eventList.setAdapter(adapter);
    }

    private void offlinecheck() throws ExecutionException, InterruptedException {
        loadFromUser();
        System.out.println(user.getNetwork());

        if (ConnectionCheck.isNetworkAvailable(getApplicationContext()) ){

            loadFromDelEvent();
            loadFromEvent();


            if(!DelEventList.isEmpty()){

                for (int i = 0 ; i < DelEventList.size(); i++){
                    if(DelEventList.get(i).getMark().equals("D")) {
                        ElasticSearchEvent.delEventTask delEventTask = new ElasticSearchEvent.delEventTask();
                        delEventTask.execute(DelEventList.get(i));
                    }
                }
            }
            System.out.println("In offline handle2");


            for (int i = 0 ; i < EventList.size(); i++){
                if(EventList.get(i).getMark() !=null) {
                    if (EventList.get(i).getMark().equals("U")) {

                        ElasticSearchEvent.updateEventTask updateEventTask = new ElasticSearchEvent.updateEventTask();
                        updateEventTask.execute(EventList.get(i));

                    }

                    if (EventList.get(i).getMark().equals("A")) {

                        ElasticSearchEvent.addEventTask addEventTask = new ElasticSearchEvent.addEventTask();
                        addEventTask.execute(EventList.get(i));

                    }
                }
            }




            if (user.getMark().equals("U")){

                ElasticSearchController.updateUser updateUser = new ElasticSearchController.updateUser();
                updateUser.execute(user);

            }


            saveInDelEvent();
            saveUser();
            saveInFile();

        }
    }


    private void loadFromDelEvent(){
        try{
            FileInputStream fis = openFileInput(DelEvent);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            DelEventList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromUser(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new com.google.common.reflect.TypeToken<User>(){}.getType();
            user = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveUser(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(user,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void loadFromEvent(){
        try{
            FileInputStream fis = openFileInput(EventFile);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            EventList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveInDelEvent(){
        try{
            FileOutputStream fos = openFileOutput(DelEvent, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(DelEventList,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
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




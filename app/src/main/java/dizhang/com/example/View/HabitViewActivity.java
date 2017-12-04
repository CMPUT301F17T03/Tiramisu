package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.ElasticSearchHabit;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: HabitViewActivity
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
 * Represents a HabitViewActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see EditHabitActivity
 * @see HabitManagerActivity
 * @since 1.0
 */

public class HabitViewActivity extends AppCompatActivity {
    private static final String HabitFILE = "Habit.save";
    private static final String DelHabit = "DelHabit.save";
    private static final String EventFile = "Event.save";
    private static final String FILENAME = "User.save";
    User user = new User();
    Button editHabit, deleteHabit;
    TextView titleView, descView,dateView,frequencyView;
    //ArrayList<Habit> newList = new ArrayList<Habit>();
    //ArrayList<Event> EventList = new ArrayList<Event>();
    ArrayList<Habit> userHabit = new ArrayList<Habit>();//will contain all the habit from current user
    ArrayList<Habit> DelList = new ArrayList<Habit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_view);

        titleView = (TextView) findViewById(R.id.habitTitleView);
        descView = (TextView) findViewById(R.id.habitDesView);
        dateView  = (TextView) findViewById(R.id.habitDateView);
        frequencyView = (TextView) findViewById(R.id.frequencyView);
        loadFromFile();

        String username = LoginActivity.uname;


        int index = getIntent().getIntExtra("index",0);

        String title = userHabit.get(index).getTitle();
        String des = userHabit.get(index).getDescription();
        String startDate = userHabit.get(index).getDate();
        double rate = userHabit.get(index).getRate();
        ArrayList<String> frequency = userHabit.get(index).getFrequency();

        /*
        String title = newList.get(index).getTitle();
        String des = newList.get(index).getDescription();
        String date = newList.get(index).getDate();
        String startDate = date.toString();


        ArrayList<String> frequency = newList.get(index).getFrequency();
        StringBuilder freq = new StringBuilder();
        for (String s : frequency){
            freq.append(s);
            freq.append(",");
        }
        frequencyView.setText(freq.toString());
        */

        frequencyView.setText(frequency.toString());
        titleView.setText(title);
        descView.setText(des);
        dateView.setText(startDate);
        frequencyView.setText(String.valueOf(rate)+"% of this habit is finished");





        editHabit = (Button) findViewById(R.id.editHabit);
        deleteHabit = (Button) findViewById(R.id.deleteHabit);
        //returnHabit = (Button) findViewById(R.id.returnhabit);

        /*returnHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitViewActivity.this, HabitManagerActivity.class);
                startActivity(intent);
            }
        });*/

        editHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                Intent editHabitInt = new Intent(getApplicationContext(), EditHabitActivity.class);
                editHabitInt.putExtra("index", index);
                startActivity(editHabitInt);
            }
        });
        deleteHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                Intent intent = new Intent(HabitViewActivity.this, HabitManagerActivity.class);
                if (ConnectionCheck.isNetworkAvailable(getApplicationContext())) {

                    ElasticSearchHabit.delHabitTask delHabitTask = new ElasticSearchHabit.delHabitTask();
                    delHabitTask.execute(userHabit.get(index));
                }
                else{
                    loadFromDelete();
                    userHabit.get(index).setMark("D");
                    DelList.add(userHabit.get(index));
                    saveInDelete();
                    loadFromUser();
                    user.setNetwork("N");
                    saveUser();
                }
                userHabit.remove(index);
                saveInFile();
                startActivity(intent);
            }
        });

    }

    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HabitManagerActivity.class);
        String username = getIntent().getStringExtra("username");
        homeInt.putExtra("username",username);
        startActivity(homeInt);
    }

// code from LonelyTwitter
    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(HabitFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            userHabit = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(HabitFILE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(userHabit,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromDelete(){
        try{
            FileInputStream fis = openFileInput(DelHabit);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            DelList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveInDelete(){
        try{
            FileOutputStream fos = openFileOutput(DelHabit, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(DelList,writer);
            writer.flush();

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

}

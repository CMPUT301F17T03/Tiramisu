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
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;


import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.ElasticSearchHabit;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: HabitManagerActivity
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
 * Represents a HabitManagerActivity
 * @version 1.0
 * @see HabitNewActivity
 * @see HabitViewActivity
 * @see HomeActivity
 * @since 1.0
 */

public class HabitManagerActivity extends AppCompatActivity {
    private static final String HabitFILE = "Habit.save";

    Button createNew;
    ListView habitList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<Habit> newList = new ArrayList<Habit>();
    ArrayList<User> userList = new ArrayList<>();
    //ArrayList<Habit> userHabit = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_manager);


        createNew = (Button) findViewById(R.id.createNew);
        habitList = (ListView) findViewById(R.id.habitList);


        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newHabitInt = new Intent(getApplicationContext(), HabitNewActivity.class);
                startActivity(newHabitInt);
            }
        });

        habitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HabitManagerActivity.this, HabitViewActivity.class);
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
        getUserProfile.execute();

        try{
            userList = getUserProfile.get();

        } catch (Exception e){
            Log.i("Error","Failed to get users from the async object");
        }

        ArrayList<Habit> hList = new ArrayList<Habit>();
        for (int x = 0; x < userList.size(); x++){
            hList.add(userList.get(x).getHabit());
        }

        listItem.clear();
        for (int i = 0; i <hList.size(); i++){
            String title = hList.get(i).getTitle();
            listItem.add(title);
        }


        loadFromFile();
        listItem.clear();
        for (int i = 0 ; i < newList.size(); i++){
            String title = newList.get(i).getTitle();
            listItem.add(title);
        }
        */
        loadFromFile();
        listItem.clear();
        for (int i = 0 ; i < newList.size(); i++) {
            String title = newList.get(i).getTitle();
            listItem.add(title);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item,listItem);
        habitList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(HabitFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            newList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}

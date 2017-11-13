package dizhang.com.example.View;

import android.content.Intent;
import android.provider.BlockedNumberContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Date;

import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
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

public class HabitViewActivity extends AppCompatActivity {
    private static final String FILENAME = "file.save";

    Button editHabit, deleteHabit,returnHabit;
    TextView titleView, descView,dateView,frequencyView;
    ArrayList<Habit> newList = new ArrayList<Habit>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_view);

        titleView = (TextView) findViewById(R.id.habitTitleView);
        descView = (TextView) findViewById(R.id.habitDesView);
        dateView  = (TextView) findViewById(R.id.habitDateView);
        frequencyView = (TextView) findViewById(R.id.frequencyView);
        loadFromFile();

        int index = getIntent().getIntExtra("index",0);

        String title = newList.get(index).getTitle();
        String des = newList.get(index).getDescription();
        ArrayList<String> frequency = newList.get(index).getFrequency();
        StringBuilder freq = new StringBuilder();
        for (String s : frequency){
            freq.append(s);
            freq.append(",");
        }
        Date date = newList.get(index).getDate();
        String startDate = date.toString();
        titleView.setText(title);
        descView.setText(des);
        dateView.setText(startDate);
        frequencyView.setText(freq.toString());





        editHabit = (Button) findViewById(R.id.editHabit);
        deleteHabit = (Button) findViewById(R.id.deleteHabit);
        returnHabit = (Button) findViewById(R.id.returnhabit);

        returnHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitViewActivity.this, HabitManagerActivity.class);
                startActivity(intent);
            }
        });

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
                newList.remove(index);
                saveInFile();
                startActivity(intent);
            }
        });

    }

// code from LonelyTwitter
    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
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

package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.HabitList;
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

public class HabitManagerActivity extends AppCompatActivity {
    Button createNew;
    ListView habitList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;

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
                String title = adapterView.getItemAtPosition(i).toString();

                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //getHabit from elasticSearch

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItem);
        habitList.setAdapter(adapter);

    }
}

package dizhang.com.example.Control;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.View.EventManagerActivity;
import dizhang.com.example.View.HabitManagerActivity;
import dizhang.com.example.View.HabitViewActivity;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EditEventActivity
 *
 * Created by dz2 on 2017-11-06.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */
/**
 * Represents a EditEventActivity
 *
 * @version 1.0
 * @see AppCompatActivity
 * @since 1.0
 */

public class EditEventActivity extends AppCompatActivity  {
    private static final String FILENAME = "event.save";


    Button Delete, Save,changeLocation;
    EditText editCom;
    TextView editTitle;
    ArrayList<Event> newList = new ArrayList<Event>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        loadFromFile();
        int index = getIntent().getIntExtra("index",0);
        Habit habit = newList.get(index).getHabit();
        String title = habit.getTitle();
        String comment = newList.get(index).getComment();

        editTitle = (TextView) findViewById(R.id.eventTitleE);
        editCom = (EditText) findViewById(R.id.commentE);
        Delete = (Button) findViewById(R.id.Delete);
        changeLocation = (Button) findViewById(R.id.changeLocation);
        Save = (Button) findViewById(R.id.Save);
        editTitle.setText(title);
        editCom.setText(comment);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                String comment  = editCom.getText().toString();

                if (comment.length() > 20) {
                    Toast.makeText(EditEventActivity.this, "Comment has to be not more than 20 characters", Toast.LENGTH_LONG).show();
                    return;
                }
                newList.get(index).setComment(comment);

                Intent intent = new Intent(EditEventActivity.this, EventManagerActivity.class);
                saveInFile();
                startActivity(intent);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                Intent intent = new Intent(EditEventActivity.this, EventManagerActivity.class);
                newList.remove(index);
                saveInFile();
                startActivity(intent);
            }
        });
    }

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

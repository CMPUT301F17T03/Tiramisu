package dizhang.com.example.Control;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.String;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.HabitList;
import dizhang.com.example.View.HabitManagerActivity;
import dizhang.com.example.View.HabitViewActivity;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: HabitNewActivity
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
 * Represents a HabitNewActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see DatePickerDialog.OnDateSetListener
 * @since 1.0
 */

public class HabitNewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String FILENAME = "file.save";

    ArrayList<String> dayOfWeek = new ArrayList<String>();
    Button startDate, addHabit;
    EditText newTitle, newDes;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;
    Date date;
    Boolean lError = false;
    ArrayList<Habit> newList = new ArrayList<Habit>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_new);

        startDate = (Button) findViewById(R.id.startDate);
        newTitle = (EditText) findViewById(R.id.newTitle);
        newDes = (EditText) findViewById(R.id.newDes);
        addHabit = (Button) findViewById(R.id.hdone);


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HabitNewActivity.this, HabitNewActivity.this,
                        year, month, day);
                datePickerDialog.show();

            }
        });

        addHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = newTitle.getText().toString();
                String des = newDes.getText().toString();
                if(date == null){
                    Toast.makeText(HabitNewActivity.this, "Please select a start day.", Toast.LENGTH_LONG).show();
                    lError = true;

                }
                if (title.length() > 20) {
                    Toast.makeText(HabitNewActivity.this, "Tittle has to be less than 20 characters", Toast.LENGTH_LONG).show();

                    lError = true;
                } else if (title.length() == 0) {
                    Toast.makeText(HabitNewActivity.this, "Tittle cannot be blank", Toast.LENGTH_LONG).show();
                    lError = true;
                }

                if (des.length() > 30) {
                    Toast.makeText(HabitNewActivity.this, "Reason has to be less than 20 characters", Toast.LENGTH_LONG).show();
                    lError = true;
                } else if (des.length() == 0) {
                    Toast.makeText(HabitNewActivity.this, "Reason cannot be blank", Toast.LENGTH_LONG).show();
                    lError = true;
                }

                if (title.length() <= 20 && des.length() <= 30 && title.length() > 0 && des.length() > 0 & date!=null) {
                    lError = false;
                }
                if (lError != true) {
                    if(dayOfWeek.size()==0){
                        dayOfWeek.add("Mon");
                        dayOfWeek.add("Tue");
                        dayOfWeek.add("Wed");
                        dayOfWeek.add("Thu");
                        dayOfWeek.add("Fri");
                        dayOfWeek.add("Sat");
                        dayOfWeek.add("Sun");
                    }
                    Habit newHabit = new Habit(title, des, date, dayOfWeek);

                    newList.add(newHabit);
                    saveInFile();
                    Intent intent = new Intent(HabitNewActivity.this, HabitManagerActivity.class);
                    startActivity(intent);

                }


            }
        });

    }

    /**
     * source demonstration of coding: https://www.youtube.com/watch?v=hwe1abDO2Ag
     *
     * @param datePicker
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1;
        dayFinal = i2;

        //Contains start date and the day of the week
        date = new GregorianCalendar(yearFinal, monthFinal, dayFinal).getTime();

    }


    /**
     * source demonstration of coding: https://www.youtube.com/watch?v=NGRV2qY9ZiU
     *
     * @param view
     */
    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.sunCheck:
                if (checked) {
                    dayOfWeek.add("Sun");
                } else {
                    dayOfWeek.remove("Sun");
                }
                break;
            case R.id.monCheck:
                if (checked) {
                    dayOfWeek.add("Mon");
                } else {
                    dayOfWeek.remove("Mon");
                }
                break;
            case R.id.tueCheck:
                if (checked) {
                    dayOfWeek.add("Tue");
                } else {
                    dayOfWeek.remove("Tue");
                }
                break;
            case R.id.wedCheck:
                if (checked) {
                    dayOfWeek.add("Wed");
                } else {
                    dayOfWeek.remove("Wed");
                }
                break;
            case R.id.thuCheck:
                if (checked) {
                    dayOfWeek.add("Thu");
                } else {
                    dayOfWeek.remove("Thu");
                }
                break;
            case R.id.friCheck:
                if (checked) {
                    dayOfWeek.add("Fri");
                } else {
                    dayOfWeek.remove("Fri");
                }
                break;
            case R.id.satCheck:
                if (checked) {
                    dayOfWeek.add("Sat");
                } else {
                    dayOfWeek.remove("Sat");
                }
                break;
        }

        Log.d("dayOfWeek", dayOfWeek.toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

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




package dizhang.com.example.Control;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dizhang.com.example.Model.Habit;
import dizhang.com.example.View.HabitManagerActivity;
import dizhang.com.example.View.LoginActivity;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EditHabitActivity
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
 * Represents a EditHabitActivity
 *
 * @version 1.0
 * @see AppCompatActivity
 * @see DatePickerDialog.OnDateSetListener
 * @see HabitManagerActivity
 * @since 1.0
 */


public class EditHabitActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String HabitFILE = "Habit.save";

    ArrayList<String> dayOfWeek = new ArrayList<String>();
    Button changeDate, editDone;
    EditText editTitle, editDes;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;
    Date date;
    Boolean lError = false;
    ArrayList<Habit> newList = new ArrayList<Habit>();
    //ArrayList<Habit> eList = new ArrayList<Habit>();



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);
        loadFromFile();

        int index = getIntent().getIntExtra("index",0);

        String username = LoginActivity.uname;

        String title = newList.get(index).getTitle();
        String des = newList.get(index).getDescription();

        editTitle = (EditText) findViewById(R.id.editTitle);
        editDes = (EditText) findViewById(R.id.editDescription);
        changeDate = (Button) findViewById(R.id.editDate);
        editDone = (Button) findViewById(R.id.edone);
        editTitle.setText(title);
        editDes.setText(des);
        /**
         * user change the date of a habit
         */
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditHabitActivity.this,EditHabitActivity.this,
                        year, month, day);
                datePickerDialog.show();



            }
        });

        /**
         * user finish editing the habit
         */
        editDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);


                String title = editTitle.getText().toString();
                String des  = editDes.getText().toString();
                newList.get(index).setTitle(title);
                newList.get(index).setDescription(des);
                if(dayOfWeek.size() == 0){
                    dayOfWeek.add("Mon");
                    dayOfWeek.add("Tue");
                    dayOfWeek.add("Wed");
                    dayOfWeek.add("Thu");
                    dayOfWeek.add("Fri");
                    dayOfWeek.add("Sat");
                    dayOfWeek.add("Sun");
                }
                newList.get(index).setFrequency(dayOfWeek);
                ElasticSearchHabit.updateHabitTask updateHabitTask = new ElasticSearchHabit.updateHabitTask();
                updateHabitTask.execute(newList.get(index));


                Intent intent = new Intent(EditHabitActivity.this, HabitManagerActivity.class);
                saveInFile();
                startActivity(intent);
            }
        });
    }


    @Override
    /**
     * @param i
     * @param i1
     * @param i2
     */
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1;
        dayFinal = i2;

        //Contains start date and the day of the week
        date = new GregorianCalendar(yearFinal, monthFinal, dayFinal).getTime();

    }

    /**
     *
     * @param view
     */

    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkSun:
                if (checked) {
                    dayOfWeek.add("Sun");
                } else {
                    dayOfWeek.remove("Sun");
                }
                break;
            case R.id.checkMon:
                if (checked) {
                    dayOfWeek.add("Mon");
                } else {
                    dayOfWeek.remove("Mon");
                }
                break;
            case R.id.checkTue:
                if (checked) {
                    dayOfWeek.add("Tue");
                } else {
                    dayOfWeek.remove("Tue");
                }
                break;
            case R.id.checkWed:
                if (checked) {
                    dayOfWeek.add("Wed");
                } else {
                    dayOfWeek.remove("Wed");
                }
                break;
            case R.id.checkThu:
                if (checked) {
                    dayOfWeek.add("Thu");
                } else {
                    dayOfWeek.remove("Thu");
                }
                break;
            case R.id.checkFri:
                if (checked) {
                    dayOfWeek.add("Fri");
                } else {
                    dayOfWeek.remove("Fri");
                }
                break;
            case R.id.checkSat:
                if (checked) {
                    dayOfWeek.add("Sat");
                } else {
                    dayOfWeek.remove("Sat");
                }
                break;
        }

        Log.d("dayOfWeek", dayOfWeek.toString());

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
    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(HabitFILE, 0);
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

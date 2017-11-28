package dizhang.com.example.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dizhang.com.example.Control.EditEventActivity;
import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EventTodayActivity
 *
 * Created by dz2 on 2017-11-13.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */
/**
 * Represents a MapActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see EventManagerActivity
 * @since 1.0
 */

public class EventTodayActivity extends AppCompatActivity {
    private static final String FILENAME = "event.save";
    private static final String HabitFILENAME = "file.save";

    Date date;
    Button addLocation, Complete,picture;
    EditText comment;
    TextView eventTitle,location;
    ImageView Image;

    ArrayList<Event> newList = new ArrayList<Event>();
    ArrayList<Habit> habitList = new ArrayList<Habit>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        int index = getIntent().getIntExtra("index",0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_today);
        loadFromFile();
        loadFromEventFile();
        picture = (Button) findViewById(R.id.picture);
        eventTitle = (TextView) findViewById(R.id.eventTitle);
        location = (TextView) findViewById(R.id.location);
        addLocation = (Button) findViewById(R.id.addLocation);
        comment = (EditText) findViewById(R.id.comment);
        Complete = (Button) findViewById(R.id.Complete);
        Image  = (ImageView)findViewById(R.id.Image);
        eventTitle.setText(habitList.get(index).getTitle());

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
            });

        Complete.setOnClickListener(new View.OnClickListener() {
            int index = getIntent().getIntExtra("index",0);


            @Override
            public void onClick(View view) {


                String comm  = comment.getText().toString();


                if (comment.length() > 20) {
                    Toast.makeText(EventTodayActivity.this, "Comment has to be not more than 20 characters", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(EventTodayActivity.this, EventManagerActivity.class);

                startActivity(intent);
                Calendar c = new GregorianCalendar();
                c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                date = c.getTime();
                Event newEvent = new Event(habitList.get(index), date, comm);
                newEvent.setTitle(habitList.get(index).getTitle());
                BitmapDrawable drawable = (BitmapDrawable) Image.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                newEvent.setPicture( bitmap);
                newList.add(newEvent);

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String Today_date = dateFormat.format(date);
                habitList.get(index).setLast(Today_date);
                saveInFile();
                saveHabit();
            }
        });

    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                Image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(EventTodayActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(EventTodayActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(HabitFILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadFromEventFile(){
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
    private void saveHabit(){
        try{
            FileOutputStream fos = openFileOutput(HabitFILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(habitList,writer);
            writer.flush();

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

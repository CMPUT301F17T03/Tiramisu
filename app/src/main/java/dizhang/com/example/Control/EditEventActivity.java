package dizhang.com.example.Control;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.View.EventManagerActivity;
import dizhang.com.example.View.EventTodayActivity;
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
 * @see EventManagerActivity
 * @since 1.0
 */

public class EditEventActivity extends AppCompatActivity  {
    private static final String FILENAME = "event.save";


    Button Delete, Save,changeLocation;
    EditText editCom;
    TextView editTitle;
    ImageView Image;
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
        Image  = (ImageView)findViewById(R.id.evenImageE);
        editTitle.setText(title);
        editCom.setText(comment);
        Image.setImageBitmap(newList.get(index).getPicture());

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                String comment  = editCom.getText().toString();

                if (comment.length() > 20) {
                    Toast.makeText(EditEventActivity.this, "Comment has to be not more than 20 characters", Toast.LENGTH_LONG).show();
                    return;
                }


                Intent intent = new Intent(EditEventActivity.this, EventManagerActivity.class);
                saveInFile();
                startActivity(intent);



                BitmapDrawable drawable = (BitmapDrawable) Image.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                newList.get(index).setPicture( bitmap);
                newList.get(index).setComment(comment);
                saveInFile();
            }
        });
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
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

            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            newList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
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
                Toast.makeText(EditEventActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(EditEventActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
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

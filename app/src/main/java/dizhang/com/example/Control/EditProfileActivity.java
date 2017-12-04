package dizhang.com.example.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.View.ProfileActivity;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EditProfileActivity
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
 * Represents a EditProfileActivity
 *
 * @version 1.0
 * @see AppCompatActivity
 * @see ProfileActivity
 * @since 1.0
 */

//still need something to finish
public class EditProfileActivity extends AppCompatActivity {
    private static final String FILENAME = "User.save";
    //private User user;
    EditText nickName, interest;
    TextView id;
    String gender;
    RadioButton rbGender;
    Button saveProf;
    RadioGroup rgprofile;
    User user = new User();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_edit_profile);

        loadFromFile();
        //User user = newList.get(0);
        //String nickname = user.getNickname();
        //String interests = newList.get(index).getInterests();
        //final String gender = newList.get(index).getGender();
        id = (TextView) findViewById(R.id.usernameEditProf);

        nickName = (EditText) findViewById(R.id.editNickname);
        //nickName.setText(nickname);

        interest = (EditText) findViewById(R.id.editInterest);
        //interest.setText(interests);
        id.setText(user.getUsername().toString());

        if (user.getNickname() == null) {

        }
        else{
            Log.d("seeeeeeeeeeeeeee List", "still goes to hereeeeeeeee2");

            nickName.setText(user.getNickname());
        }
        if (user.getInterests() == null) {

        }
        else{
            Log.d("seeeeeeeeeeeeeee List", "still goes to hereeeeeeeee3");

            interest.setText(user.getInterests());
        }



        saveProf = (Button) findViewById(R.id.saveProfile);

        rgprofile = (RadioGroup) findViewById(R.id.rgprofile);

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = getIntent().getIntExtra("index",0);
                String nickname = nickName.getText().toString();
                String interests = interest.getText().toString();
                user.setNickname(nickname);
                user.setInterests(interests);
                user.setGender(gender);
                saveInFile();
                Intent profileInt = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profileInt);
            }
        });


    }

    /**
     * source demonstration of coding: https://www.youtube.com/watch?v=n7c3bIWcgZo
     *
     * convert radio selection into string for gender used in Profile.
     *
     * @see User
     * @param view
     */
    public void rbClick (View view) {
        int rbID = rgprofile.getCheckedRadioButtonId();
        rbGender = (RadioButton) findViewById(rbID);

        gender = rbGender.getText().toString();

        //Toast.makeText(getBaseContext(), rbGender.getText(), Toast.LENGTH_LONG).show();
        Log.d("gender selected", gender);

    }


    @Override
    protected void onStart() {
        super.onStart();
        //loadFromFile();

    }
    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<User>(){}.getType();
            user = gson.fromJson(in,listType);
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
            gson.toJson(user,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

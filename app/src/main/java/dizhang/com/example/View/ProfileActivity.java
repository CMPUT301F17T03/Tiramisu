package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import dizhang.com.example.Control.EditProfileActivity;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: ProfileActivity
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
 * Represents a Profile Activity
 * @version 1.0
 * @see AppCompatActivity
 * @see EditProfileActivity
 * @see HomeActivity
 * @since 1.0
 */

public class ProfileActivity extends AppCompatActivity {
    private static final String FILENAME = "file.save";
    Button editProfile;
    TextView usernameViewProf, genderViewProf, interestViewProf,nicknameViewProf;
    ArrayList<User> newList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadFromFile();

        editProfile = (Button) findViewById(R.id.editProfile);
        usernameViewProf = (TextView) findViewById(R.id.usernameViewProf);
        genderViewProf = (TextView) findViewById(R.id.genderViewProf);
        interestViewProf = (TextView) findViewById(R.id.interestViewProf);
        nicknameViewProf = (TextView) findViewById(R.id.nicknameViewProf);


        User user = newList.get(0);
        usernameViewProf.setText(user.getUsername());
        genderViewProf.setText(user.getGender());
        interestViewProf.setText(user.getInterests());
        nicknameViewProf.setText(user.getNickname());


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent editProfileInt = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(editProfileInt);
            }
        });
    }

    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeInt);
    }

    protected void onStart() {
        super.onStart();
        loadFromFile();

    }
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            newList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

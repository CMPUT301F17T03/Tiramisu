package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: LoginActivity
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
 * Represents a LoginActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see HomeActivity
 * @see SignupActivity
 * @since 1.0
 */
public class LoginActivity extends AppCompatActivity {
    private static final String FILENAME = "profile.save";

    public TextView signupButton;
    public RelativeLayout loginButton;
    EditText username;
    EditText password;
    ArrayList<User> newList = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C4CD")));

        signupButton = (TextView) findViewById(R.id.signupLayout);
        loginButton = (RelativeLayout) findViewById(R.id.loginLayout);

        username = (EditText) findViewById(R.id.usernameLayout);
        password = (EditText) findViewById(R.id.passwordLayout);


        loginButton.setOnClickListener(new View.OnClickListener() {
            /* source code: https://stackoverflow.com/questions/30205771/android-login-page
             */

            public void onClick(View v) {

                if ( username.getText().toString().equals("") || username.getText().toString().equals(null)) {
                    Toast.makeText(getBaseContext(), "Please first enter Username", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().equals("") || password.getText().toString().equals(null)) {

                    Toast.makeText(getBaseContext(), "Password can't be empty", Toast.LENGTH_LONG).show();
                } else {
                    //need to create signup file so that we could call the username and password from there
                    if (username.getText().toString().equals("lalalalal") && password.getText().toString().equals("lalalalal")) {

                        Toast.makeText(getApplicationContext(), "Logging in ... ", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Username and password should be correct", Toast.LENGTH_SHORT).show();
                        User user = new User();
                        user.setUsername(username.getText().toString());
                        user.setPassword(password.getText().toString());
                        newList.clear();
                        newList.add(user);
                        saveInFile();
                        username.setText(null);
                        password.setText(null);
                    }
                    // start the activity anyway because signup is empty
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }




    public void onBackPressed(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
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

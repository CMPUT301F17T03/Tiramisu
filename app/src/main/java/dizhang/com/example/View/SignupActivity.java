package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.concurrent.ExecutionException;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: SignupActivity
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
 * Represents a SignupActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see LoginActivity
 * @since 1.0
 */
public class SignupActivity extends AppCompatActivity {

    EditText Username,Confirmpassword,Password;

    private static final String FILENAME = "User.save";
    private static final String FILENAME2 = "event.save";
    /*EditText Confirmpassword;*/
    Button finishSignup;
    ArrayList<User> newList = new ArrayList<User>();
    ArrayList<Event> eventlist = new ArrayList<Event>();

    //Boolean lError = false;
    //not working in this part, still finding methods to store data : (
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        /*final int index = getIntent().getIntExtra("index",0);*/
        //loadFromFile();
        Username = (EditText)  findViewById(R.id.usernameSignup);
        Password = (EditText)  findViewById(R.id.passwordSignup);
        Confirmpassword = (EditText)  findViewById(R.id.confirmSignup);
        finishSignup = (Button) findViewById(R.id.finishSignup);
        finishSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String cpassword = Confirmpassword.getText().toString();
                if (!password.equals(cpassword)){
                    Toast.makeText(getBaseContext(), "two password is different", Toast.LENGTH_LONG).show();
                    Password.setText(null);
                    Confirmpassword.setText(null);
                    return;
                }
                User newUser = new User(username, password);
                newList.add(newUser);
                saveInFile();
                loadFromFile();
                //ElasticSearchController Elastic = new ElasticSearchController(newUser);


                //Elastic.new Signup().execute();


                ElasticSearchController.AddUserTask addUserTask
                        = new ElasticSearchController.AddUserTask();
                addUserTask.execute(newUser);
                ElasticSearchController.IsExist isExist = new ElasticSearchController.IsExist();
                User getuser = new User();
                try {

                     getuser = isExist.execute(username).get();
                    System.out.println(getuser.getUsername());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginInt);
            }
        });
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
    public void onBackPressed(){
        Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginInt);
    }


    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME2);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            eventlist = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

    }
    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
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
*/
}

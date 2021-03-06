package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.ElasticSearchEvent;
import dizhang.com.example.Control.ElasticSearchHabit;
import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

//import android.support.v7.app.ActionBarActivity;

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
    private static final String FILENAME = "User.save";
    private static final String HabitFILE = "Habit.save";
    private static final String EventFile = "Event.save";
    ArrayList<Habit> userHabit = new ArrayList<Habit>();
    ArrayList<Event> userEvent = new ArrayList<Event>();

    public static String uname;

    public TextView signupButton;
    public RelativeLayout loginButton;
    EditText username;
    EditText password;
    User CurrentUser = new User();



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
                String UsernameString = username.getText().toString();
                String PasswordString = password.getText().toString();
                if ( UsernameString.equals("") || UsernameString.equals(null)) {
                    Toast.makeText(getBaseContext(), "Username can't be empty", Toast.LENGTH_LONG).show();
                } else if (PasswordString.equals("") || PasswordString.equals(null)) {

                    Toast.makeText(getBaseContext(), "Password can't be empty", Toast.LENGTH_LONG).show();
                } else {
                    //need to create signup file so that we could call the username and password from there
                    Toast.makeText(getApplicationContext(), "Logging in ... ", Toast.LENGTH_LONG).show();

                    ElasticSearchController.getUser isExist = new ElasticSearchController.getUser();
                    User getuser = new User();
                    try {
                        getuser = isExist.execute(UsernameString).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if(getuser != null){
                        if (PasswordString.equals(getuser.getPassword()))  {

                        CurrentUser=getuser;

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        uname=CurrentUser.getUsername();
                        if (CurrentUser.getNickname()!=null){
                            String nickname = CurrentUser.getNickname();
                            intent.putExtra("Nickname",CurrentUser.getNickname());
                            //Toast.makeText(LoginActivity.this, nickname , Toast.LENGTH_LONG).show();
                        }
                        CurrentUser.setNetwork("Y");
                        CurrentUser.setMark("F");
                        CurrentUser.setEventNetwork("Y");

                            ElasticSearchHabit.getHabitTask getHabitTask = new ElasticSearchHabit.getHabitTask();
                            getHabitTask.execute(uname);

                            try{
                                ArrayList<String> Last = new ArrayList<String>();
                                Last.add(" ");
                                userHabit = getHabitTask.get();
                                for (int i = 0 ; i < userHabit.size(); i++){
                                    if (Last.contains(userHabit.get(i).getTitle())){
                                        if (userHabit.get(i).getLast().equals("xsxs")){
                                            userHabit.remove(i);
                                        }
                                        else{
                                            Integer in=Last.indexOf(userHabit.get(i).getTitle());
                                            userHabit.remove(in-1);
                                        }
                                    }
                                    Last.add( userHabit.get(i).getTitle());

                                }
                            } catch (Exception e) {
                                Log.i("Error", "failed to get habit from the async object");
                            }

                            ElasticSearchEvent.getEventTask getEventTask = new ElasticSearchEvent.getEventTask();
                            getEventTask.execute(uname);

                            try{
                                userEvent = getEventTask.get();
                            } catch (Exception e) {
                                Log.i("Error", "failed to get habit from the async object");
                            }

                            saveUser();
                            saveHabit();
                            saveEvent();
                        intent.putExtra("username",CurrentUser.getUsername());

                        startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Elasticsearch server is in maintenance or password incorrect", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {

                        Toast.makeText(getApplicationContext(), "This user does not exist", Toast.LENGTH_SHORT).show();

                    }

                    //username.setText(null);
                    //password.setText(null);
                    // start the activity anyway because signup is empty
                    //Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    //startActivity(intent);
                }
            }
        });

        /*
            *if the user do not have an account, click signup button to register
         */
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

    private void saveUser(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(CurrentUser,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveHabit(){
        try{
            FileOutputStream fos = openFileOutput(HabitFILE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(userHabit,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveEvent(){
        try{
            FileOutputStream fos = openFileOutput(EventFile, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson.toJson(userEvent,writer);
            writer.flush();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}

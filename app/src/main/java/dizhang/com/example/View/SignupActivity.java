package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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
    /*EditText Confirmpassword;*/
    Button finishSignup;
    ArrayList<User> newList = new ArrayList<User>();
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
                User newUser = new User(username, password);
                newList.add(newUser);
                saveInFile();
                ElasticSearchController Elastic = new ElasticSearchController(newUser);

                System.out.print("Hello World" +
                        "this is in ");
                Log.d("myTag", "This is my message");

                Elastic.new Signup()
                        .execute();
                /*if (Username.getText().toString().length() < 3) {
                    Toast.makeText(SignupActivity.this, "username has to be more than 3 characters ", Toast.LENGTH_LONG).show();

                    Username.setText(null);
                } else if (Username.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "please enter a username", Toast.LENGTH_LONG).show();
                }

                if (Password.getText().toString().length() < 5) {
                    Toast.makeText(SignupActivity.this, "password has to be more than 5 characters", Toast.LENGTH_LONG).show();
                    Password.setText(null);
                } else if (Password.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "password cannot be blank", Toast.LENGTH_LONG).show();
                }
                else if (Confirmpassword.getText().length() == 0) {
                    Toast.makeText(SignupActivity.this, "password cannot be blank", Toast.LENGTH_LONG).show();

                }
                if (Confirmpassword.getText().length() != Password.getText().length()) {
                    Toast.makeText(SignupActivity.this, "please confirm the correct password", Toast.LENGTH_LONG).show();
                    Password.setText(null);
                    Confirmpassword.setText(null);

                } /*check if password and confirm password are the same
                   else if () {
                    Toast.makeText(SignupActivity.this, "password cannot be blank", Toast.LENGTH_LONG).show();
                    lError = false;
                }

                newList.get(index).setUsername(Username.getText().toString());
                newList.get(index).setPassword(Password.getText().toString());
                newList.get(index).setComfirmpassword(Confirmpassword.getText().toString());*/
                Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);
                //saveInFile();
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

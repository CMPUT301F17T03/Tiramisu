package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: ShareActivity
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
 * Represents a ShareActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see RequestActivity
 * @see HomeActivity
 * @since 1.0
 */
public class ShareActivity extends AppCompatActivity {

    private static final String FollowedFILE = "Followed.save";

    Button viewRequestButton;
    Button sendRequestButton;
    private EditText bodyText;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<Habit> followedList = new ArrayList<Habit>();
    ListView FollowedHabit;
    //ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        bodyText = (EditText) findViewById(R.id.searchUser);
        FollowedHabit = (ListView) findViewById(R.id.habitFollowedList);
        viewRequestButton = (Button) findViewById(R.id.requestList);
        sendRequestButton = (Button) findViewById(R.id.sendRequest);

        viewRequestButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ShareActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                //Intent intent = new Intent(ShareActivity.this, HabitViewActivity.class);

                //Call ElasticSearch here
                //ElasticSearchController.GetUserProfile userProfile = new ElasticSearchController.GetUserProfile();
                //userProfile.execute(text);
                try {
                    //userList.addAll(userProfile.get());
                } catch (Exception e) {
                    Log.i("Eroor", "Failed to get the user from the async object");
                }


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
        listItem.clear();
        for (int i = 0; i < followedList.size(); i++){
            String title = followedList.get(i).getTitle();
            listItem.add(title);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
        FollowedHabit.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    protected void onRestart(){
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FollowedFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            followedList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

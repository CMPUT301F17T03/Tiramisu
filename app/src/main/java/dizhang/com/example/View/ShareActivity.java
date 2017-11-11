package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.FollowedActivity;
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

public class ShareActivity extends AppCompatActivity {

    public Button requestButton;
    public Button searchButton;
    private EditText bodyText;
    private ArrayList<User> userList = new ArrayList<User>();
    //private ArrayAdapter<User> adapter;
    //private ListView searchHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        bodyText = (EditText) findViewById(R.id.searchUser);
        requestButton = (Button) findViewById(R.id.requestList);
        searchButton = (Button) findViewById(R.id.searchHabits);

        requestButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ShareActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                Intent intent = new Intent(ShareActivity.this, HabitViewActivity.class);

                //Call ElasticSearch here
                ElasticSearchController.GetUserProfile userProfile = new ElasticSearchController.GetUserProfile();
                userProfile.execute(text);
                try {
                    userList.addAll(userProfile.get());
                } catch (Exception e) {
                    Log.i("Eroor", "Failed to get the user from the async object");
                }


            }
        });

    }


}

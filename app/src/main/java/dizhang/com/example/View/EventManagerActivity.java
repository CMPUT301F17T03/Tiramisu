package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EventManagerActivity
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
 * Represents a EventManagerActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see HomeActivity
 * @since 1.0
 */

public class EventManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_manager);
    }

    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeInt);
    }
}

package dizhang.com.example.View;

import android.content.Intent;
import android.provider.BlockedNumberContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: HabitViewActivity
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class HabitViewActivity extends AppCompatActivity {
    Button editHabit, deleteHabit;
    TextView titleView, descView,dateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_view);

        titleView = (TextView) findViewById(R.id.habitTitleView);
        descView = (TextView) findViewById(R.id.habitDesView);
        dateView  = (TextView) findViewById(R.id.habitDateView);

        /**

        final String title =getIntent().getStringExtra("title");
        final String desc =getIntent().getStringExtra("des");
        final String date = getIntent().getStringExtra("date");
        titleView.setText(title);
        descView.setText(desc);
        dateView.setText(date);
        *
        */



        editHabit = (Button) findViewById(R.id.editHabit);
        deleteHabit = (Button) findViewById(R.id.deleteHabit);

        editHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editHabitInt = new Intent(getApplicationContext(), EditHabitActivity.class);
                startActivity(editHabitInt);
            }
        });
        deleteHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteHabitInt = new Intent(HabitViewActivity.this, HabitViewActivity.class);
                startActivity(deleteHabitInt);
            }
        });

    }
}

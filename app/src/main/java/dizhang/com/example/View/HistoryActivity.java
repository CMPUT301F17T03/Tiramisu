package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dizhang.com.example.tiramisu.R;


/**
 * Class Name: HistoryActivity
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class HistoryActivity extends AppCompatActivity {

    Button historyMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyMap = (Button) findViewById(R.id.historyMap);

        historyMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapInt = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(mapInt);
            }
        });
    }
}

package dizhang.com.example.View;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import dizhang.com.example.tiramisu.R;

/**
 * Class Name: RequestActivity
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
 * Represents a RequestActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see RequestActivity
 * @since 1.0
 */

public class RequestActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        listView = (ListView) findViewById(R.id.requestList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RequestActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Confirmation of Share Request");
                builder.setMessage("Select one of the following button to accept or reject this request. ");

                //setting Negative "Reject" Button
                builder.setNegativeButton("REJECT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //this part still need be modified
                        dialogInterface.cancel();
                    }
                });

                //setting Positive "Accept" Button
                builder.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //this part still need be modified
                        finish();
                    }
                });

                builder.show();

            }
        });


    }
}

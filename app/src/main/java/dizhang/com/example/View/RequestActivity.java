package dizhang.com.example.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dizhang.com.example.Model.User;
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

    private static final String RequestFILE = "Request.save";

    ListView listView;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<User> requestList = new ArrayList<User>();


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

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();

        loadFromFile();
        listItem.clear();
        for (int i = 0; i < requestList.size(); i++){
            String title = requestList.get(i).getUsername();
            listItem.add(title);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    private void loadFromFile(){
        try{
            FileInputStream file = openFileInput(RequestFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader((file)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            requestList = gson.fromJson(in, listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

package dizhang.com.example.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.common.collect.MapMaker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Marker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.MMP;
import dizhang.com.example.tiramisu.R;

//import dizhang.com.example.Control.ElasticSearchController;


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
/**
 * Represents a HistoryActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see MapActivity
 * @see HomeActivity
 * @see EventViewActivity
 * @since 1.0
 */
public class HistoryActivity extends AppCompatActivity {
    ArrayList<Event> newList = new ArrayList<Event>();
    private static final String FILENAME = "Event.save";
    Button historyMap,searchComment,searchName;
    ListView mainListView ;
    EditText input;
    ArrayList<String> listItem = new ArrayList<String>();


    //ArrayList<String> locPass = new ArrayList<String>();

    ArrayList<MMP> pass2Map = new ArrayList<MMP>();

    ArrayAdapter<String> adapter;
    ArrayList<Integer> SelectIndex = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mainListView= (ListView) findViewById(R.id.historyList);
        historyMap = (Button) findViewById(R.id.historyMap);
        input = (EditText) findViewById(R.id.searchHistory);



        historyMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapInt = new Intent(getApplicationContext(), MapActivity.class);
                mapInt.putExtra("pass2Map", pass2Map);
                startActivity(mapInt);
            }
        });


        searchComment = (Button) findViewById(R.id.searchComment);
        searchName = (Button) findViewById(R.id.searchType);

        searchName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //what if input nothing
                String Name= input.getText().toString();
                ArrayList<Integer> Select = new ArrayList<Integer>();
                listItem.clear();
                pass2Map.clear();
                for (int i = 0 ; i < newList.size(); i++){
                    String Eventcomment = newList.get(i).getTitle();
                    MMP mmp = new MMP();
                    //ArrayList<String> loc = newList.get(i).getLocation();
                    if( Eventcomment.contains(Name)) {
                        String title = newList.get(i).getTitle();
                        listItem.add(title);
                        Select.add(i);
                        if (newList.get(i).getLocation() != null){
                            mmp.setLocation(newList.get(i).getLocation());
                            mmp.setTitle(title);
                            mmp.setUsername("Myself");
                            pass2Map.add(mmp);
                        }
                    }

                }
                Collections.reverse(listItem);
                adapter.notifyDataSetChanged();
                SelectIndex = Select;
                mainListView.setAdapter(adapter);

            }
        });

        searchComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what if input nothing
                String comment= input.getText().toString();
                ArrayList<Integer> Select = new ArrayList<Integer>();

                listItem.clear();
                pass2Map.clear();
                for (int i = 0 ; i < newList.size(); i++){
                    String Eventcomment = newList.get(i).getComment();
                    MMP mmp = new MMP();
                    if( Eventcomment.contains(comment)) {
                        String title = newList.get(i).getTitle();
                        listItem.add(title);
                        Select.add(i);
                        if (newList.get(i).getLocation() != null){
                            mmp.setLocation(newList.get(i).getLocation());
                            mmp.setTitle(title);
                            pass2Map.add(mmp);
                        }
                    }

                }
                Collections.reverse(listItem);
                adapter.notifyDataSetChanged();
                SelectIndex = Select;
                mainListView.setAdapter(adapter);

            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HistoryActivity.this, EventViewActivity.class);
                intent.putExtra("index",SelectIndex.get(i));
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        /*
        ElasticSearchController.GetUserProfile getUserProfile = new ElasticSearchController.GetUserProfile();
        getUserProfile.execute("what");
        try{
            userList = getUserProfile.get();

        } catch (Exception e){
            Log.i("Error","Failed to get users from the async object");
        }
        */
        //TODO get user from elasticsearch and get habit from user
        //This part will add the habit only happend today
        loadFromEventFile();
        pass2Map.clear();
        listItem.clear();
        ArrayList<Integer> Select = new ArrayList<Integer>();

        for (int i = 0 ; i < newList.size(); i++){
            String title = newList.get(i).getTitle();
            MMP mmp = new MMP();
            listItem.add(title);
            mmp.setTitle(title);
            mmp.setLocation(newList.get(i).getLocation());
            pass2Map.add(mmp);
            Select.add(i);

        }
        Collections.reverse(listItem);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item,listItem);
        mainListView.setAdapter(adapter);
        SelectIndex = Select;


    }
    private void loadFromEventFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            newList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeInt);
    }
}


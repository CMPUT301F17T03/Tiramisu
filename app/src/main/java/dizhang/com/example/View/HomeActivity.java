package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.tiramisu.R;


import static java.sql.DriverManager.println;

/**
 * Class Name: HomeActivity
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
 * Represents a HomeActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see ProfileActivity
 * @see HabitManagerActivity
 * @see EventManagerActivity
 * @see HistoryActivity
 * @see ShareActivity
 * @see MapActivity
 * @see LoginActivity
 * @since 1.0
 */
public class HomeActivity extends AppCompatActivity {

    private static final String FILENAME = "file.save";



    ListView habitList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<Habit> newList = new ArrayList<Habit>();



    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        habitList = (ListView) findViewById(R.id.todayListview);

        habitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, EventTodayActivity.class);
                Toast.makeText(HomeActivity.this, listItem.get(i), Toast.LENGTH_LONG).show();

                intent.putExtra("name",listItem.get(i));
                startActivity(intent);

            }
        });

        //super.onCreate(savedInstanceState);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        //testcode:

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case(R.id.myProfile):
                        Intent profileInt = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(profileInt);
                        break;
                    case(R.id.manageHabit):
                        Intent habitInt = new Intent(getApplicationContext(), HabitManagerActivity.class);
                        startActivity(habitInt);
                        break;
                    case(R.id.manageEvent):
                        Intent eventInt = new Intent(getApplicationContext(), EventManagerActivity.class);
                        startActivity(eventInt);
                        break;
                    case(R.id.eventHistory):
                        Intent historyInt = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(historyInt);
                        break;
                    case(R.id.shareCenter):
                        Intent shareInt = new Intent(getApplicationContext(), ShareActivity.class);
                        startActivity(shareInt);
                        break;
                    case(R.id.map):
                        Intent mapInt = new Intent(getApplicationContext(), MapActivity.class);
                        startActivity(mapInt);
                        break;
                    case(R.id.logout):
                        Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(loginInt);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(mtoggle.onOptionsItemSelected(item)){
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
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
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String Today_date = dateFormat.format(date);
        //This part will add the habit only happend today
        List<String> days = new ArrayList<>(Arrays.asList("Sat","Sun","Mon","Tue","Wed","Thu","Fri"));
        String Today = days.get(dayOfWeek);
        loadFromFile();
        listItem.clear();
        for (int i = 0 ; i < newList.size(); i++){
            if( newList.get(i).getFrequency().contains(Today)){
                if ( newList.get(i).getLast().equals( Today_date)) {
                    continue;
                }
                else{
                    String title = newList.get(i).getTitle();
                    listItem.add(title);
                }
            }
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItem);
        habitList.setAdapter(adapter);

    }
    private void loadFromFile(){
        try{
            Log.d("myTag", "Before");
            FileInputStream fis = openFileInput(FILENAME);
            Log.d("myTag", "This is my message");
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            newList = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}

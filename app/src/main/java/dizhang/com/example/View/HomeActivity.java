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
import java.util.Locale;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

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
 * @see EventTodayActivity
 * @since 1.0
 */
public class HomeActivity extends AppCompatActivity {

    private static final String HabitFILE = "Habit.save";
    //User CurrentUser = new User();


    ListView habitList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    ArrayList<Habit> eList = new ArrayList<>();


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
                        Intent profileInt = new Intent(getApplicationContext(), ProfileActivity.class);  // go to see user's profile
                        startActivity(profileInt);
                        break;
                    case(R.id.manageHabit):
                        Intent habitInt = new Intent(getApplicationContext(), HabitManagerActivity.class);  // go to see and edit user's habit
                        startActivity(habitInt);
                        break;
                    case(R.id.manageEvent):
                        Intent eventInt = new Intent(getApplicationContext(), EventManagerActivity.class);   // go to see and edit user's event
                        startActivity(eventInt);
                        break;
                    case(R.id.eventHistory):
                        Intent historyInt = new Intent(getApplicationContext(), HistoryActivity.class);     // go to see and edit the history of an event
                        startActivity(historyInt);
                        break;
                    case(R.id.shareCenter):
                        Intent shareInt = new Intent(getApplicationContext(), ShareActivity.class); // go to see and manage the habits followed list
                        startActivity(shareInt);
                        break;
                    case(R.id.map):
                        Intent mapInt = new Intent(getApplicationContext(), MapActivity.class);  // see the map of location
                        startActivity(mapInt);
                        break;
                    case(R.id.logout):
                        Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);   //  logout
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

        //TODO get user from elasticsearch and get habit from user
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String Today_date = dateFormat.format(date);


        //This part will add the habit only happend today
        ArrayList<String> days = new ArrayList<String>(Arrays.asList(" ","Sun","Mon","Tue","Wed","Thu","Fri","Sat"));
        String Today = days.get(dayOfWeek);
        loadFromFile();

        String username = LoginActivity.uname;
        listItem.clear();
        String title = "";
        for (int i = 0 ; i < eList.size(); i++){
            if( eList.get(i).getFrequency().contains(Today)){
                String LastDate =eList.get(i).getLast();
                System.out.println(LastDate);
                System.out.println(Today_date);
                title = eList.get(i).getTitle();
                Boolean bt = LastDate.equals(Today_date);
                System.out.println("compare result is "+ bt.toString());
                if (!LastDate.equals(Today_date)) {
                    System.out.println("this is in");
                    listItem.add(title);
                }

            }


        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item,listItem);
        habitList.setAdapter(adapter);

    }
    private void loadFromFile(){
        try{

            FileInputStream fis = openFileInput(HabitFILE);
            Log.d("myTag", "This is my message");
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type Habittype = new TypeToken<ArrayList<Habit>>(){}.getType();
            eList = gson.fromJson(in,Habittype);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}

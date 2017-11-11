package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import dizhang.com.example.Control.FollowedActivity;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.History;
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

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    Button newHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        newHabit = (Button) findViewById(R.id.newHabit);
        newHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newHabitInt = new Intent(HomeActivity.this, HabitNewActivity.class);
                startActivity(newHabitInt);
            }
        });
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
                        Intent eventInt = new Intent(getApplicationContext(), EventViewActivity.class);
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
}

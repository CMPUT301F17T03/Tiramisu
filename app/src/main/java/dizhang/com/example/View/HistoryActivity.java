package dizhang.com.example.View;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Method;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Model.User;
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
/**
 * Represents a HistoryActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see MapActivity
 * @since 1.0
 */
public class HistoryActivity extends AppCompatActivity {


    Button historyMap;
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    private ElasticSearchController Elastic = new ElasticSearchController();
    private User user =  new  User();


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



        mainListView = (ListView) findViewById(R.id.historyList);
        Context context = getApplicationContext();

        ElasticSearchController.GetUserProfile elastic = new ElasticSearchController.GetUserProfile();
        Class clazz = elastic.getClass();
        String username = user.getUsername();

        try {
            Method searchResult = clazz.getDeclaredMethod("doInBackground", Class.forName(username));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeInt);
    }
}


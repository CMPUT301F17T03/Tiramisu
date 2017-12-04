package dizhang.com.example.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dizhang.com.example.Control.ElasticSearchController;
import dizhang.com.example.Control.ElasticSearchHabit;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: ShareActivity
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
 * Represents a ShareActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see RequestActivity
 * @see HomeActivity
 * @since 1.0
 */
public class ShareActivity extends AppCompatActivity {

    public Button viewRequestButton;
    public Button sendRequestButton;
    private EditText bodyText;
    private ArrayList<User> userList = new ArrayList<User>();
    User requestUser = new User();
    User currentUser = new User();
    ListView follwoingList;
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayList<String> viewList = new ArrayList<String>();
    ArrayList<Habit> userHabit = new ArrayList<>();
    ArrayAdapter<String> adapter;

    //private ArrayAdapter<User> adapter;
    //private ListView searchHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        bodyText = (EditText) findViewById(R.id.searchUser);
        follwoingList = (ListView) findViewById(R.id.habitFollowedList);
        viewRequestButton = (Button) findViewById(R.id.requestList);
        sendRequestButton = (Button) findViewById(R.id.sendRequest);



        follwoingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        viewRequestButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ShareActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(ShareActivity.this, "Enter an username to send request to.", Toast.LENGTH_LONG).show();
                }else{
                    ElasticSearchController.getUser getUser = new ElasticSearchController.getUser();
                    getUser.execute(text);
                    try{
                        requestUser = getUser.get();
                    }catch (Exception e){
                        Log.i("Error","error getting user");
                    }
                    String username = LoginActivity.uname;
                    if(requestUser.getFollower().contains(username)){
                        Toast.makeText(ShareActivity.this, "Already following this user!", Toast.LENGTH_LONG).show();

                    }else{
                        if(!requestUser.getRequest().contains(username)){
                            requestUser.addRequest(username);
                            Toast.makeText(ShareActivity.this, "Request sent!", Toast.LENGTH_LONG).show();

                            ElasticSearchController.updateUser updateUserTask = new ElasticSearchController.updateUser();
                            updateUserTask.execute(requestUser);
                        }else{
                            Toast.makeText(ShareActivity.this, "Request already sent!", Toast.LENGTH_LONG).show();

                        }
                    }



                };

                }

        });

    }

    public void onBackPressed(){
        Intent homeInt = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeInt);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String username = LoginActivity.uname;
        ElasticSearchController.getUser getUser = new ElasticSearchController.getUser();
        getUser.execute(username);
        try{
            currentUser = getUser.get();
        }catch (Exception e){
            Log.i("Error","error getting user");
        }
        listItem.clear();
        viewList.clear();
        //get all the name that current user is following and search each username to get all the habit
        listItem = currentUser.getFollowee();

        for (int i = 0 ; i < listItem.size(); i++) {
            ElasticSearchHabit.getHabitTask getHabitTask = new ElasticSearchHabit.getHabitTask();
            getHabitTask.execute(listItem.get(i));
            try{
                userHabit = getHabitTask.get();
            } catch (Exception e) {
                Log.i("Error", "failed to get habit from the async object");
            }
            for(int x = 0 ; x<userHabit.size();x++){
                viewList.add("User: "+listItem.get(i)+"            "
                        +"Habit: "+userHabit.get(x).getTitle());
            }

        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,viewList);
        follwoingList.setAdapter(adapter);
    }
}

package dizhang.com.example.View;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dizhang.com.example.Model.User;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Update;

/**
 * Class Name: ElasticSearchController
 *
 * Created by xzhuang on 2017-11-09.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */
/**
 * Represents a ElasticSearchController
 *
 * @version 1.0
 * @since 1.0
 */


public class ElasticSearchController extends AppCompatActivity {

    private static JestDroidClient client;
    private static User User;
    private static final String FILENAME = "User.save";
    /*EditText Confirmpassword;*/
   // private  ArrayList<User> newList =loadFromUserFile();
    //private static final String APP_INDEX = "cmput301f17t03";


    public  ElasticSearchController(User User) {
        this.User = User;

    }

    public static void verifySettings(){
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();

        }
    }
    // Class to setInfo
    /**
     * Represents a SetUserInfo
     * @version 1.0
     * @see AsyncTask
     * @since 1.0
     */
/*
    public class SetUserInfo extends AsyncTask<String, Void, Void> {
        private User User =  new  User();
        private String username = User.getUsername();
        @Override
        protected Void doInBackground(String... set_parameters) {
            verifySettings();
            ArrayList<User> user = new ArrayList<User>();

            PutMapping putMapping = new PutMapping.Builder(
                    "cmput301f17t03",
                    username,
                    // The format should be:
                    //"{ \"my_type\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
                    "{ \"my_type\" : { \"properties\" : { \"username\" : \""+username + "\",\"userhabit\" : \"" +set_parameters+"\"} } }"
            ).build();
            try {
                client.execute(putMapping);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Represents a GerUseProfile
     * @version 1.0
     * @see AsyncTask
     * @since 1.0
     */
    public  class Signup extends AsyncTask<String, User, User> {

        Context ApplicationContext;
        Activity mActivity;

        public void BackgroundWorker (Activity activity)
        {

            mActivity = activity;
        }
        @Override
        protected User doInBackground(String... search_parameters) {
            verifySettings();




            String username = User.getUsername();
            String password = User.getPassword();
            String[] test = {username,password};

            String[] myStringArray = {"a","b","c"};
            //test whether we can get data by search
            //info seemed not show on webpage
            Index index = new Index.Builder(User).index("cmputf17t03").type("username123").build();
            Update update = new Update.Builder("dddmcksncs").index("cmputf17t03").type("username123").id("1").build();

            try {
                    // where is the client?
                    DocumentResult result = client.execute(update);

                }

                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the tweets");
                }
/*
            // TODO Build the query
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f17t03")
                    .addType(username)
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<User> foundUser = result.getSourceAsObjectList(User.class);
                    //newList.addAll(foundUser);
                }
                else {
                    Log.i("Error","the search query failed to find any user that matched.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            //return newList;*/
            return User;
        }
    }

    public  class LoadfromElastic extends AsyncTask<String, User, Void> {

        Context ApplicationContext;
        Activity mActivity;

        public void BackgroundWorker (Activity activity)
        {

            mActivity = activity;
        }
        @Override
        protected Void doInBackground(String... search_parameters) {
            verifySettings();



            Get get = new Get.Builder("cmputf17t03", "1").type("username123").build();




            try {
                    // where is the client?
                    JestResult result = client.execute(get);
                System.out.println(result);

                //Log.d("myTag", result);
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the tweets");
                }


            return null;

        }
    }

    private void loadFromUserFile(){
        try{
            FileInputStream fis = ElasticSearchController.this.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader((fis)));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> newList = gson.fromJson(in,listType);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        //return newList;
    }

}



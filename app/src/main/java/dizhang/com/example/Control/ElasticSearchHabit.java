package dizhang.com.example.Control;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.User;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by frost on 2017-12-02.
 */


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
 * Represents a ElasticSearchHabit
 *
 * @version 1.0
 * @since 1.0
 * @see AbstractStringBuilder
 * @see AsyncTask
 */


public class ElasticSearchHabit {



    private static JestDroidClient client;
    private static String indexString = "cmput301f17t03";
    private static String typeString = "user";



    public static class getHabitTask extends AsyncTask<String, Void, ArrayList<Habit>> {

        @Override
        protected ArrayList<Habit> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Habit> habit = new ArrayList<Habit>();

            String query = "{\n" +
                    " \"query\" :{\n" +
                    " \"term\"  :{ \"username\": \"" + search_parameters[0] + "\"}}}";


            // TODO Build the query
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f17t03")
                    .addType("habit")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Habit> foundHabit = result.getSourceAsObjectList(Habit.class);
                    habit.addAll(foundHabit);
                }
                else {
                    Log.i("Error","the search query failed to find any user that matched.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return habit;
        }
    }

    public static class addHabitTask extends AsyncTask<Habit,Void,Void>{
        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();

            for (Habit habit : habits){
                Index index = new Index.Builder(habit).index("cmput301f17t03").type("habit").build();

                try{
                    DocumentResult result = client.execute(index);

                    if(result.isSucceeded()){
                        habit.setId(result.getId());
                        habit.setMark("F");
                    }else {
                        Log.i("Error", "failed to add habit");
                        habit.setMark("A");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Error","The application failed to build and send the habit");
                }
            }

            return null;
        }
    }
    public static class updateHabitTask extends AsyncTask<Habit,Void,Void>{
        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();

            for (Habit habit : habits){
                Index index = new Index.Builder(habit).index("cmput301f17t03").type("habit").id(habit.getId()).build();

                try{
                    DocumentResult result = client.execute(index);

                    if(result.isSucceeded()){
                        habit.setId(result.getId());
                        habit.setMark("F");
                    }else {
                        Log.i("Error", "failed to add habit");
                        habit.setMark("A");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Error","The application failed to build and send the habit");
                }
            }

            return null;
        }
    }


    public static class delHabitTask extends AsyncTask<Habit,Void,Void>{
        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();

            for (Habit habit : habits){
                Delete index = new Delete.Builder(habit.getId()).index("cmput301f17t03").type("habit").build();


                try{
                    DocumentResult result = client.execute(index);

                    if(result.isSucceeded()){
                        habit.setId(result.getId());
                        habit.setMark("F");
                    }else {
                        Log.i("Error", "failed to add habit");
                        habit.setMark("D");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Error","The application failed to build and send the habit");
                }
            }

            return null;
        }
    }

//http://cmput301.softwareprocess.es:8080
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("https://5b3c205796b755b5db6f9b28b41fa441.us-east-1.aws.found.io:9243/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}

// Class to setInfo
/**
 * Represents a SetUserInfo
 * @version 1.0
 * @see AsyncTask
 *
 */


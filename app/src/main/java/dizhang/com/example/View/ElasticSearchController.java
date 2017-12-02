package dizhang.com.example.View;

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


public class ElasticSearchController {



    private static JestDroidClient client;
    private static String indexString = "cmput301f17t03";
    private static String typeString = "user";

    public static class AddUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index(indexString).type(typeString).id(user.getUsername()).build();

                try {
                    // where is the client
                    DocumentResult result = client.execute(index);
                    System.out.println(result.getJsonString());
                    if (result.isSucceeded()) {
                        Log.d("In AsyncTask ID", result.getId());
                        //user.setAid(result.getId());
                    } else {
                        Log.i("Error", "Elasticsearch was not able to add the user.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("Error", "The application failed to build and send the user");
                }

            }
            return null;
        }
    }

    public static class IsExist extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... params){
            verifySettings();

            User user = new User();

            Get get = new Get.Builder(indexString, params[0]).type(typeString).build();
            Log.d("usertest", params[0]);

            try {
                JestResult result = client.execute(get);
                user = result.getSourceAsObject(User.class);
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }


            return user;
        }

    }


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
                    }else {
                        Log.i("Error", "failed to add habit");
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
                    }else {
                        Log.i("Error", "failed to add habit");
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

                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Error","The application failed to build and send the habit");
                }
            }

            return null;
        }
    }


    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
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


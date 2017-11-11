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
import io.searchbox.core.DocumentResult;
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

public class ElasticSearchController {

    private static JestDroidClient client;

    //private static final String APP_INDEX = "cmput301f17t03";


    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();

        }
    }
    public static class addHabitTask extends AsyncTask<Habit, Void, Void> {
        @Override
        protected Void doInBackground(Habit... habits) {
            for (Habit habit : habits) {

                Index index = new Index.Builder(habit).index("cmput301f17t03").type("user").build();

                try {
                    client.execute(index);

                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send habits");
                }


            }
            return null;
        }
    }



    public static class GetUserProfile extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> user = new ArrayList<User>();


            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"username\" : \""  +  search_parameters[0] + "\"} \n" +
                    "    }\n" +
                    "}";


            // TODO Build the query
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f17t03")
                    .addType("user")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<User> foundUser = result.getSourceAsObjectList(User.class);
                    user.addAll(foundUser);
                }
                else {
                    Log.i("Error","the search query failed to find any user that matched.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return user;
        }
    }


}



package dizhang.com.example.Control;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dizhang.com.example.Model.User;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.mapping.PutMapping;

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
    public static class SetUserInfo extends AsyncTask<String, Void, Void> {
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
    public static class GetUserProfile extends AsyncTask<String, Void, ArrayList<User>> {
        private User User =  new  User();
        private String username = User.getUsername();
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
                    .addType(username)
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



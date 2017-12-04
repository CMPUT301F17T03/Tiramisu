package dizhang.com.example.Control;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import dizhang.com.example.Model.Event;
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
 * @see AsyncTask
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

    public static class getUser extends AsyncTask<String, Void, User> {
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

    public static class updateUser extends AsyncTask<User,Void,Void>{
        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users){
                Index index = new Index.Builder(user).index(indexString).type(typeString).id(user.getUsername()).build();

                try{
                    DocumentResult result = client.execute(index);

                    if(result.isSucceeded()){
                        Log.i("Success", "update user successfully");
                    }else {
                        Log.i("Error", "failed to update user");
                    }
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




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
 * Represents a ElasticSearchController
 *
 * @version 1.0
 * @since 1.0
 */


public class ElasticSearchEvent {



    private static JestDroidClient client;
    private static String indexString = "cmput301f17t03";
    private static String typeString = "event";


    public static class getEventTask extends AsyncTask<String, Void, ArrayList<Event>> {

        @Override
        protected ArrayList<Event> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Event> events = new ArrayList<Event>();

            String query = "{\n" +
                    " \"query\" :{\n" +
                    " \"term\"  :{ \"username\": \"" + search_parameters[0] + "\"}}}";


            // TODO Build the query
            Search search = new Search.Builder(query)
                    .addIndex(indexString)
                    .addType(typeString)
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Event> foundHabit = result.getSourceAsObjectList(Event.class);
                    events.addAll(foundHabit);
                }
                else {
                    Log.i("Error","the search query failed to find any user that matched.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return events;
        }
    }

    public static class addEventTask extends AsyncTask<Event,Void,Void>{
        @Override
        protected Void doInBackground(Event... events) {
            verifySettings();

            for (Event event : events){
                Index index = new Index.Builder(event).index(indexString).type(typeString).build();

                try{
                    DocumentResult result = client.execute(index);

                    if(result.isSucceeded()){
                        event.setId(result.getId());
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
    public static class updateEventTask extends AsyncTask<Event,Void,Void>{
        @Override
        protected Void doInBackground(Event... events) {
            verifySettings();

            for (Event event : events){
                Index index = new Index.Builder(event).index(indexString).type(typeString).id(event.getId()).build();

                try{
                    DocumentResult result = client.execute(index);

                    if(result.isSucceeded()){
                        event.setId(result.getId());
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

    public static class delHabitTask extends AsyncTask<Event,Void,Void>{
        @Override
        protected Void doInBackground(Event... events) {
            verifySettings();

            for (Event event : events){
                Delete index = new Delete.Builder(event.getId()).index(indexString).type(typeString).build();

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


package dizhang.com.example.Model;

import android.support.v7.app.AppCompatActivity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

import io.searchbox.annotations.JestId;

/**
 * Class Name: Habit
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
   *Represents a Habit
 * @version 1.0
 * @since 1.0
 */
public class Habit {
    private String title;
    private String description;
    private String date;
    private ArrayList<String> frequency;
    private EventList events = new EventList();
    private String LastDate;
    private String username;
    @JestId
    private String Id;
    /**
    * @pamram title
    * @param description
    * @param date
    * @param frequency
     */
    public Habit(){}
    public Habit(String title, String description, String date, ArrayList<String> frequency, String username){
        this.title = title;
        this.description = description;
        this.date = date;
        this.frequency = frequency;
        this.username=username;
    }
    /**
     *@return
     */

    public  String getTitle() {
        return title;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    /**
     *@param title
     */



    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    /**
     *@return
     */


    public String getDescription() {
        return description;
    }

    /**
     *@param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     *@return
     */

    public String getDate() {
        return date;
    }
    /**
    *@param date
     */

    public void setDate(String date) {
        this.date = date;
    }
    /**
     *@return
     */

    public ArrayList<String> getFrequency() {
        return frequency;
    }
    /**
     *@param frequency
     */
    public void setFrequency(ArrayList<String> frequency) {
        this.frequency = frequency;
    }
    /**
     *@return
     */
    public String getLast() {
        return LastDate;
    }

    public void setLast(String date){
        this.LastDate = date;
    }
    public EventList getEvents() {
        return events;
    }
    /**
     *@param events
     */
    public void setEvents(EventList events) {
        this.events = events;
    }
}

package dizhang.com.example.Model;

import android.support.v7.app.AppCompatActivity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

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
    private Date date;
    private ArrayList<String> frequency;
    private EventList events = new EventList();
    private String LastDate;

    /**
    * @pamram title
    * @param description
    * @param date
    * @param frequency
     */
    public Habit(){}
    public Habit(String title, String description, Date date, ArrayList<String> frequency){
        this.title = title;
        this.description = description;
        this.date = date;
        this.frequency = frequency;
    }
    /**
     *@return
     */

    public  String getTitle() {
        return title;
    }
    /**
     *@param title
     */

    public void setTitle(String title) {
        this.title = title;
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

    public Date getDate() {
        return date;
    }
    /**
    *@param date
     */

    public void setDate(Date date) {
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

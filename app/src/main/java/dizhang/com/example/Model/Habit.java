package dizhang.com.example.Model;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
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

public class Habit implements Serializable {
    private String title;
    private String description;
    private Date date;
    private ArrayList<String> frequency;
    private EventList events = new EventList();
    @JestId
    private String id;

    public String getId() {
        return id;
    }

    public Habit(){};

    public void setId(String id) {
        this.id = id;
    }

    public Habit(String title, String description, Date date, ArrayList<String> frequency){
        this.title = title;
        this.description = description;
        this.date = date;
        this.frequency = frequency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getFrequency() {
        return frequency;
    }

    public void setFrequency(ArrayList<String> frequency) {
        this.frequency = frequency;
    }

    public EventList getEvents() {
        return events;
    }

    public void setEvents(EventList events) {
        this.events = events;
    }
}

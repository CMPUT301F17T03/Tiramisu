package dizhang.com.example.Model;

import android.support.v7.app.AppCompatActivity;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ggranked on 2017-10-23.
 */

public class Habit {
    private String title;
    private String description;
    private Date date;
    private ArrayList<String> frequency;
    private EventList events = new EventList();


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

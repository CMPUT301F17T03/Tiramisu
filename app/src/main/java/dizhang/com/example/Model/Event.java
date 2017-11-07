package dizhang.com.example.Model;

import android.graphics.Picture;
import android.location.Location;

import java.util.Date;

/**
 * Created by ggranked on 2017-10-23.
 */

public class Event {
    private Habit habit;
    private String comment;
    private Date date;
    private Picture picture;
    private Location location;


    public Event(Habit habit, Date date) {
        this.habit = habit;
        this.date = date;
    }

    public Event(Habit habit, Date date, String comment) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
    }

    public Event(Habit habit, Date date, Picture picture) {
        this.habit = habit;
        this.date = date;
        this.picture = picture;
    }


    public Event(Habit habit, Date date, Location location) {
        this.habit = habit;
        this.date = date;
        this.location = location;
    }

    public Event(Habit habit, Date date, String comment, Location location) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.location = location;
    }

    public Event(Habit habit, Date date, String comment, Picture picture) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.picture = picture;
    }

    public Event(Habit habit, Date date, Picture picture, Location location) {
        this.habit = habit;
        this.date = date;
        this.picture = picture;
        this.location = location;
    }

    public Event(Habit habit, Date date, String comment, Picture picture, Location location) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.picture = picture;
        this.location = location;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}

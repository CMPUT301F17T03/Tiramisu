package dizhang.com.example.Model;

import android.graphics.Picture;
import android.location.Location;

import java.util.Date;

/**
 * Class Name: Event
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
 * Represents an Event
 * @version 1.0
 * @since 1.0
 */

public class Event {
    private Habit habit;
    private String comment;
    private Date date;
    private Picture picture;
    private Location location;

    /**
     *@param habit
     * @param date
     */

    public Event(Habit habit, Date date) {
        this.habit = habit;
        this.date = date;
    }

    /**
    * @param habit
    * @param date
    * @param comment
    */
    public Event(Habit habit, Date date, String comment) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
    }
    /**
    * @param habit
     * @param date
     * @param picture
     */

    public Event(Habit habit, Date date, Picture picture) {
        this.habit = habit;
        this.date = date;
        this.picture = picture;
    }

    /**
    *@param habit
     * @param date
     * @param location
     */
    public Event(Habit habit, Date date, Location location) {
        this.habit = habit;
        this.date = date;
        this.location = location;
    }
    /**
     *@param habit
     * @param date
     * @param comment
     * @param location
     */

    public Event(Habit habit, Date date, String comment, Location location) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.location = location;
    }
    /**
     * @param habit
     * @param date
     * @param comment
     * @param picture
     */
    public Event(Habit habit, Date date, String comment, Picture picture) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.picture = picture;
    }
    /**
     *@param habit
     * @param date
     * @param picture
     *@param location
     */
    public Event(Habit habit, Date date, Picture picture, Location location) {
        this.habit = habit;
        this.date = date;
        this.picture = picture;
        this.location = location;
    }
    /**
     *@param habit
     * @param date
     * @param comment
     * @param picture
     * @param location
     */
    public Event(Habit habit, Date date, String comment, Picture picture, Location location) {
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.picture = picture;
        this.location = location;
    }
    /**
     *@return
     */
    public Habit getHabit() {
        return habit;
    }
    /**
     *@param habit
     */

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
    /**
     *@return
     */

    public String getComment() {
        return comment;
    }
    /**
     *@param comment
     */


    public void setComment(String comment) {
        this.comment = comment;
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


    public Picture getPicture() {
        return picture;
    }
    /**
     *@param picture
     */


    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    /**
     *@return
     */

    public Location getLocation() {
        return location;
    }
    /**
     *@param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}

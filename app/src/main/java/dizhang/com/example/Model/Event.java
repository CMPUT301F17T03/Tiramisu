package dizhang.com.example.Model;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import io.searchbox.annotations.JestId;

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

    private String comment;
    private Date date;
    private String picture;
    private ArrayList<String> location;
    private String title;
    private String username;
    @JestId
    private String Id;

    /**
     *@param habit
     * @param date
     */


    /**
    * @param title
    * @param date
    * @param comment
    */
    public Event(String title, Date date, String comment) {
        this.title = title;
        this.date = date;
        this.comment = comment;
    }
    /**
    * @param habit
     * @param date
     * @param picture
     */

    /**
    *@param habit
     * @param date
     * @param location
     */
    /**
     * @param habit
     * @param date
     * @param comment
     * @param picture
     */
    /**
     *@param habit
     * @param date
     * @param picture
     *@param location
     */

    /**
     *@param habit
     * @param date
     * @param comment
     * @param picture
     * @param location
     */
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

    /**
     *@param
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title){ this.title = title;}

    public String getTitle() {
        return title;
    }

    /**
     *@return
     */


    public String getPicture() {
        return picture;
    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    /**
     *@return
     */

    public ArrayList<String> getLocation() {
        return location;
    }
    /**
     *@param location
     */
    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

}

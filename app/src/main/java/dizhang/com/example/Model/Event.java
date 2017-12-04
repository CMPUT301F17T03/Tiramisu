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
    private String mark;
    @JestId
    private String Id;



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
     *
     * @param date
     */

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @param title
     */

    public void setTitle(String title){ this.title = title;}

    /**
     *
     * @return
     */

    public String getTitle() {
        return title;
    }

    /**
     *@return
     */


    public String getPicture() {
        return picture;
    }


    /**
     *
     * @return
     */
    public String getId() {
        return Id;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }


    /**
     *
     * @param id
     */
    public void setId(String id) {
        Id = id;
    }

    /**
     *
     * @return
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param picture
     */

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

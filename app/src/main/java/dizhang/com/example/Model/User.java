package dizhang.com.example.Model;

import android.graphics.Picture;

import java.util.ArrayList;

/**
 * Class Name: User
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class User {
    private String username; //unique ID
    private String nickname;
    private String gender;
    private String interests;
    private Picture profilePic;
    private HabitList habitlist;
    private EventList eventlist;
    private HistoryList history;
    private String password;
    private String cpassword;
    private UserList fans = new UserList();     //users who follows me
    private UserList requests = new UserList(); //requests sent to me
    private UserList following = new UserList(); //the users that I followed
<<<<<<< HEAD
    private ArrayList searchInfo;
    //public User(String username){
    //  this.username = username;
    //}
=======

    public User(String username, String password){
      this.username = username;
      this.password = password;
    }
    public User(){}
>>>>>>> f88e2593e1f3b7fce3b55f8741945d41ed8d7bf5

    /**
     * @return
     */

    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return
     */
    public String getInterests() {
        return interests;
    }

    /**
     * @param interests
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * @return
     */
    public Picture getProfilePic() {
        return profilePic;
    }

    /**
     * @param profilePic
     */
    public void setProfilePic(Picture profilePic) {
        this.profilePic = profilePic;
    }

    /**
     * @return
     */
    public HabitList getHabitlist() {
        return habitlist;
    }

    /**
     * @param habitlist
     */
    public void setHabitlist(HabitList habitlist) {
        this.habitlist = habitlist;
    }

    /**
     * @return
     */
    public EventList getEventlist() {
        return eventlist;
    }

    /**
     * @param eventlist
     */
    public void setEventlist(EventList eventlist) {
        this.eventlist = eventlist;
    }

    /**
     * @return
     */
    public HistoryList getHistory() {
        return history;
    }

    /**
     * @param history
     */
    public void setHistory(HistoryList history) {
        this.history = history;
    }

    /**
     * @return
     */
    public UserList getFans() {
        return fans;
    }

    /**
     * @param fans
     */
    public void setFans(UserList fans) {
        this.fans = fans;
    }

    /**
     * @return
     */
    public UserList getRequests() {
        return requests;
    }

    /**
     * @param requests
     */
    public void setRequests(UserList requests) {
        this.requests = requests;
    }

    /**
     * @return
     */
    public UserList getFollowing() {
        return following;
    }

    /**
     * @param following
     */
    public void setFollowing(UserList following) {
        this.following = following;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return
     */
    public String getComfirmpassword() {
        return cpassword;
    }

    /**
     * @param cpassword
     */
    public void setComfirmpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public ArrayList getsearchInfo(){ return searchInfo;}

    public void setsearchInfo(ArrayList searchInfo){ this.searchInfo = searchInfo;}


}

package dizhang.com.example.Model;

import android.graphics.Picture;

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
    private UserList fans = new UserList();     //users who follows me
    private UserList requests = new UserList(); //requests sent to me
    private UserList following = new UserList(); //the users that I followed

    public User(String username){
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Picture getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Picture profilePic) {
        this.profilePic = profilePic;
    }

    public HabitList getHabitlist() {
        return habitlist;
    }

    public void setHabitlist(HabitList habitlist) {
        this.habitlist = habitlist;
    }

    public EventList getEventlist() {
        return eventlist;
    }

    public void setEventlist(EventList eventlist) {
        this.eventlist = eventlist;
    }

    public HistoryList getHistory() {
        return history;
    }

    public void setHistory(HistoryList history) {
        this.history = history;
    }

    public UserList getFans() {
        return fans;
    }

    public void setFans(UserList fans) {
        this.fans = fans;
    }

    public UserList getRequests() {
        return requests;
    }

    public void setRequests(UserList requests) {
        this.requests = requests;
    }

    public UserList getFollowing() {
        return following;
    }

    public void setFollowing(UserList following) {
        this.following = following;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

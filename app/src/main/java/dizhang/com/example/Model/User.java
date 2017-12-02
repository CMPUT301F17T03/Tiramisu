package dizhang.com.example.Model;

import java.util.ArrayList;

import io.searchbox.annotations.JestId;

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
    @JestId

    private String username; //unique ID
    private String nickname;
    private String gender;
    private String interests;
    //private Picture profilePic;
    private ArrayList<Habit> habits = new ArrayList<Habit>();
    private ArrayList<Event> events = new ArrayList<Event>();
    //private HistoryList history;
    private String password;

    //private UserList fans = new UserList();     //users who follows me
    //private UserList requests = new UserList(); //requests sent to me
    //private UserList following = new UserList(); //the users that I followed
    //private ArrayList searchInfo;

    public User(String username, String password){
      this.username = username;
      this.password = password;
    }
    public User(){

    }

    private ArrayList searchInfo;

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
    public String getNickname() { return nickname; }

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

    public void addHabit(Habit habit) {
        habits.add(habit);
    }
    public ArrayList<Habit> getHabitlist() {
        return habits;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
    public ArrayList<Event> getEventlist() {
        return events;
    }
    /**
     * @return
     *//*
    public Picture getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(Picture profilePic) {
        this.profilePic = profilePic;
    }
        public HistoryList getHistory() {
        return history;
    }

    public void setHistory(HistoryList history) {
        this.history = history;
    }

    /*


    public void setFans(UserList fans) {
        this.fans = fans;
    }

    //public UserList getFans() {
        return fans;
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


      @param following

    public void setFollowing(UserList following) {
        this.following = following;
    }
*/




    /**
     * @param profilePic
     */

    /**
     * @return
     */

    /**
     * @param habitlist
     */

    /**
     * @return
     */

    /**
     * @param eventlist
     */

    /**
     * @return
     */

    /**
     * @param history
     */
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
    /**
     * @return
     */

    public ArrayList getsearchInfo(){ return searchInfo;}

    /**
     *
     * @param searchInfo
     */

    public void setsearchInfo(ArrayList searchInfo){ this.searchInfo = searchInfo;}


}

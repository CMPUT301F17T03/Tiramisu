package dizhang.com.example.Model;

import android.graphics.Picture;

/**
 * Created by ggranked on 2017-11-06.
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
}

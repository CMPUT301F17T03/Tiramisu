package dizhang.com.example.Model;

/**
 * Created by ggranked on 2017-11-06.
 */

public class User {
    private String username; //unique ID
    private HabitList habitlist;
    private EventList eventlist;
    private HistoryList history;
    private UserList fans = new UserList();     //users who follows me
    private UserList requests = new UserList(); //requests sent to me
    private UserList following = new UserList(); //the users that I followed





}

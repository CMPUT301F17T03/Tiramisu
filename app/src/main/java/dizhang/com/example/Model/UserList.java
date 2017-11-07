package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Created by ggranked on 2017-11-06.
 */

public class UserList {
    private ArrayList<User> users = new ArrayList<User>();

    public UserList(){}

    public User getuser(int index) {
        return users.get(index);
    }

    public boolean hasuser (User user){
        return users.contains(user);
    }

    public void add(User user){
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
    }
}

package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Class Name: UserList
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
 * Represents a UserList
 * @version 1.0
 * @since 1.0
 */

public class UserList {
    private ArrayList<User> users = new ArrayList<User>();

    public UserList(){}
    /**
     *@param index
     * @return
     */
    public User getUser(int index) {
        return users.get(index);
    }
    /**
     *@param user
     * @return
     */
    public boolean hasUser (User user){
        return users.contains(user);
    }
    /**
     *@param user
     */
    public void add(User user){
        users.add(user);
    }
    /**
     *@param user
     */
    public void delete(User user) {
        users.remove(user);
    }
}

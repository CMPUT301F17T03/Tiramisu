package dizhang.com.example.tiramisu;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.History;
import dizhang.com.example.Model.HistoryList;
import dizhang.com.example.Model.User;
import dizhang.com.example.Model.UserList;

/**
 * Created by ggranked on 2017-11-13.
 */

public class UserListUnitTest extends ActivityInstrumentationTestCase2{
    public UserListUnitTest() {super(dizhang.com.example.View.HomeActivity.class);}

    public void testAddUser() {
        User user = new User("testUsername","testPassword");
        UserList userList = new UserList();
        userList.add(user);
        assertTrue(userList.hasUser(user));
    }

    public void testDeleteUser() {
        User user = new User("testUsername","testPassword");
        UserList userList = new UserList();
        userList.delete(user);
        assertFalse(userList.hasUser(user));

    }

    public void testGetHistory() {
        User user = new User("testUsername","testPassword");
        UserList userList = new UserList();
        userList.add(user);
        assertEquals(userList.getUser(0),user);

    }

    public void testHasHistory() {
        User user = new User("testUsername","testPassword");
        UserList userList = new UserList();
        userList.add(user);
        assertTrue(userList.hasUser(user));

        assertTrue(userList.hasUser(user));

    }





}
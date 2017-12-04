package dizhang.com.example.Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ggranked on 2017-12-03.
 *
 * represents elements passed to MapActivity
 */

public class MMP implements Serializable {
    private String username, title;
    private ArrayList<String> location;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }
}

package dizhang.com.example.Control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import dizhang.com.example.Model.User;
import dizhang.com.example.Model.UserList;
import dizhang.com.example.View.HabitViewActivity;
import dizhang.com.example.View.RequestActivity;
import dizhang.com.example.tiramisu.R;

public class FollowedActivity extends AppCompatActivity {

    public Button requestButton;
    public Button searchButton;
    private EditText bodyText;
    private ArrayList<User> userList = new ArrayList<User>();
    private ListView followedHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followed);

    }

}

package dizhang.com.example.View;

import android.content.Intent;
import android.provider.BlockedNumberContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.tiramisu.R;

public class HabitViewActivity extends AppCompatActivity {
    Button createNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_manager);

        createNew = (Button) findViewById(R.id.createNew);

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newHabitInt = new Intent(getApplicationContext(), HabitNewActivity.class);
                startActivity(newHabitInt);
            }
        });
    }
}

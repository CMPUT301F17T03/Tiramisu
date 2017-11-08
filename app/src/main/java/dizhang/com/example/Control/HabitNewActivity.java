package dizhang.com.example.Control;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import dizhang.com.example.tiramisu.R;

public class HabitNewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button startDate;
    EditText newTitle, newDes;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_new);

        startDate = (Button) findViewById(R.id.startDate);
        newTitle = (EditText) findViewById(R.id.newTitle);
        newDes = (EditText) findViewById(R.id.newDes);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HabitNewActivity.this, HabitNewActivity.this,
                        year, month, day);
                datePickerDialog.show();

            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1+1;
        dayFinal = i2;
        

    }
}

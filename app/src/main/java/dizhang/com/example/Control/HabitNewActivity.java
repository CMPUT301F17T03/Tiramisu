package dizhang.com.example.Control;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dizhang.com.example.tiramisu.R;

public class HabitNewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ArrayList<String> dayOfWeek = new ArrayList<String>();
    Button startDate;
    EditText newTitle, newDes;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;
    Date date;



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
        monthFinal = i1;
        dayFinal = i2;

        //Contains start date and the day of the week
        date = new GregorianCalendar(yearFinal, monthFinal, dayFinal).getTime();
        System.out.println(date);

    }

    public void selectItem(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.sunCheck:
                if (checked){
                    dayOfWeek.add("Sun");
                }
                else{
                    dayOfWeek.remove("Sun");
                }
                break;
            case R.id.monCheck:
                if (checked){
                    dayOfWeek.add("Mon");
                }
                else{
                    dayOfWeek.remove("Mon");
                }
                break;
            case R.id.tueCheck:
                if (checked){
                    dayOfWeek.add("Tue");
                }
                else{
                    dayOfWeek.remove("Tue");
                }
                break;
            case R.id.wedCheck:
                if (checked){
                    dayOfWeek.add("Wed");
                }
                else{
                    dayOfWeek.remove("Wed");
                }
                break;
            case R.id.thuCheck:
                if (checked){
                    dayOfWeek.add("Thu");
                }
                else{
                    dayOfWeek.remove("Thu");
                }
                break;
            case R.id.friCheck:
                if (checked){
                    dayOfWeek.add("Fri");
                }
                else{
                    dayOfWeek.remove("Fri");
                }
                break;
            case R.id.satCheck:
                if (checked){
                    dayOfWeek.add("Sat");
                }
                else{
                    dayOfWeek.remove("Sat");
                }
                break;
        }
        Log.d("dayOfWeek", dayOfWeek.toString());

    }
}

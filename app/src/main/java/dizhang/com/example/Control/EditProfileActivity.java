package dizhang.com.example.Control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import dizhang.com.example.Model.User;
import dizhang.com.example.tiramisu.R;

/**
 * Created by ggranked on 2017-11-06.
 */

public class EditProfileActivity extends AppCompatActivity {
    //private User user;
    EditText nickName, interest;
    String gender;
    RadioButton rbGender;
    Button saveProf;
    RadioGroup rgprofile;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nickName = (EditText) findViewById(R.id.editNickname);
        interest = (EditText) findViewById(R.id.editInterest);

        saveProf = (Button) findViewById(R.id.saveProfile);

        rgprofile = (RadioGroup) findViewById(R.id.rgprofile);


    }
    public void rbClick (View view) {
        int rbID = rgprofile.getCheckedRadioButtonId();
        rbGender = (RadioButton) findViewById(rbID);

        gender = rbGender.getText().toString();

        Toast.makeText(getBaseContext(), rbGender.getText(), Toast.LENGTH_LONG).show();
        Log.d("gender selected", gender);

    }
}

package dizhang.com.example.Control;

import android.content.Intent;
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
import dizhang.com.example.View.ProfileActivity;
import dizhang.com.example.tiramisu.R;

/**
 * Class Name: EditProfileActivity
 *
 * Created by dz2 on 2017-11-06.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class EditProfileActivity extends AppCompatActivity {
    private User user;
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

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileInt = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profileInt);
            }
        });


    }

    /**
     * source demonstration of coding: https://www.youtube.com/watch?v=n7c3bIWcgZo
     *
     * convert radio selection into string for gender used in Profile.
     *
     * @see User
     * @param view
     */
    public void rbClick (View view) {
        int rbID = rgprofile.getCheckedRadioButtonId();
        rbGender = (RadioButton) findViewById(rbID);

        gender = rbGender.getText().toString();

        //Toast.makeText(getBaseContext(), rbGender.getText(), Toast.LENGTH_LONG).show();
        Log.d("gender selected", gender);

    }
}

package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import dizhang.com.example.tiramisu.R;

/**
 * Class Name: SignupActivity
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
 * Represents a SignupActivity
 * @version 1.0
 * @see AppCompatActivity
 * @see LoginActivity
 * @since 1.0
 */
public class SignupActivity extends AppCompatActivity {
    Button finishSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        finishSignup = (Button) findViewById(R.id.finishSignup);

        finishSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginInt);
            }
        });
    }

    public void onBackPressed(){
        Intent loginInt = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginInt);
    }
}

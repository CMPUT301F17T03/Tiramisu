package dizhang.com.example.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dizhang.com.example.Control.EditProfileActivity;
import dizhang.com.example.tiramisu.R;

public class ProfileActivity extends AppCompatActivity {

    Button editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfile = (Button) findViewById(R.id.editProfile);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editProfileInt = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(editProfileInt);
            }
        });
    }
}

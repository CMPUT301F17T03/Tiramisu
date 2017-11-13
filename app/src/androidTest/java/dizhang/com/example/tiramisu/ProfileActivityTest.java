package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dizhang.com.example.Control.EditProfileActivity;
import dizhang.com.example.View.HomeActivity;
import dizhang.com.example.View.ProfileActivity;
import dizhang.com.example.View.RequestActivity;
import dizhang.com.example.View.ProfileActivity;

/**
 * Created by ggranked on 2017-11-13.
 */

public class ProfileActivityTest extends ActivityInstrumentationTestCase2<ProfileActivity>

{
    private Solo solo;

    public ProfileActivityTest() {super(ProfileActivity.class);}


    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("Setup", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testClickOnButton() {
        ProfileActivity activity = (ProfileActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", ProfileActivity.class);

        solo.clickOnButton("edit my profile");

        solo.assertCurrentActivity("Wrong Activity", EditProfileActivity.class);

        solo.enterText((EditText) solo.getView(R.id.editNickname), "TestNickname");
        solo.clickOnRadioButton(1);
        solo.clickOnRadioButton(2);
        solo.clickOnRadioButton(0);
        solo.enterText((EditText) solo.getView(R.id.editInterest), "Test example of my interests");
        solo.clickOnButton("Save");

        solo.assertCurrentActivity("Wrong Activity", ProfileActivity.class);

        //solo.clickInList(0, 1);
        //solo.clickInList(0, 2);
        solo.goBack();

        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
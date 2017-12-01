package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dizhang.com.example.View.HomeActivity;
import dizhang.com.example.View.LoginActivity;
import dizhang.com.example.View.SignupActivity;

/**
 * Created by ggranked on 2017-10-22.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public LoginActivityTest(){
        super(LoginActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("Setup", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testClick(){
        LoginActivity activity = (LoginActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);

        solo.clickOnText("Sign up here");
        solo.assertCurrentActivity("Wrong Activity", SignupActivity.class);
        //assertTrue(solo.waitForText("signupLayout"));

        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.usernameLayout), "Admin");
        solo.enterText((EditText) solo.getView(R.id.passwordLayout), "Admin");

        solo.clickOnView(solo.getView(R.id.loginLayout));
        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);
        assertTrue(solo.waitForText("Today's Event"));

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
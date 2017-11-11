package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dizhang.com.example.Control.FollowedActivity;
import dizhang.com.example.View.RequestActivity;
import dizhang.com.example.View.ShareActivity;

/**
 * Created by ggranked on 2017-10-23.
 */

public class ShareActivityTest extends ActivityInstrumentationTestCase2<ShareActivity>

{
    private Solo solo;

    public ShareActivityTest() {
        super(ShareActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("Setup", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testClickOnButton() {
        ShareActivity activity = (ShareActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", ShareActivity.class);

        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity", FollowedActivity.class);
        solo.waitForText("Test follow");

        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity", ShareActivity.class);

        solo.enterText((EditText) solo.getView(R.id.searchUser), "TestUsername");
        solo.clickOnButton("Sent a Request");

        solo.clickOnButton("Check Request From Others");
        solo.assertCurrentActivity("Wrong Activity", RequestActivity.class);

        solo.clickInList(0, 1);
        solo.clickInList(0, 2);
        solo.goBack();

        solo.assertCurrentActivity("Wrong Activity", ShareActivity.class);

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}

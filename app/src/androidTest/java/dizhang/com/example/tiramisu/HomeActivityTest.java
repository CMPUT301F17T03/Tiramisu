package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

/**
 * Created by ggranked on 2017-10-23.
 */

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;

    public HomeActivityTest(){
        super(dizhang.com.example.tiramisu.HomeActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("Setup", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testClickOnItem(){
        HomeActivity activity = (HomeActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);

        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity", EventViewActivity.class);
        assertTrue(solo.waitForText("Event Manager"));
        solo.goBack();

        solo.clickOnView(solo.getView(R.id.drawerLayout));
        solo.clickOnMenuItem("myProfile");
        solo.assertCurrentActivity("Wrong Activity", ProfileActivity.class);
        solo.goBack();


        solo.clickOnMenuItem("manageHabit");
        solo.assertCurrentActivity("Wrong Activity", HabitViewActivity.class);
        solo.goBack();

        solo.clickOnMenuItem("eventHistory");
        solo.assertCurrentActivity("Wrong Activity", HistoryActivity.class);
        solo.goBack();

        solo.clickOnMenuItem("shareCenter");
        solo.assertCurrentActivity("Wrong Activity", ShareActivity.class);
        solo.goBack();

        solo.clickOnMenuItem("map");
        solo.assertCurrentActivity("Wrong Activity", MapActivity.class);
        solo.goBack();

        solo.clickOnMenuItem("logout");
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);

        solo.clickOnView(solo.getView("loginLayout"));
        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);
        assertTrue(solo.waitForText("Today's Event"));

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
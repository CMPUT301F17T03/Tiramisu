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

public class MapActivityTest extends ActivityInstrumentationTestCase2<MapActivity>

    {
        private Solo solo;

    public MapActivityTest() {
        super(dizhang.com.example.tiramisu.MapActivity.class);
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
        MapActivity activity = (MapActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", MapActivity.class);

        solo.clickOnButton("Followed");
        solo.sleep(100000);
        //wait for result to load on the screen

        solo.clickOnButton("Highlight");
        solo.sleep(100000);
        //wait for result to load on the screen

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}


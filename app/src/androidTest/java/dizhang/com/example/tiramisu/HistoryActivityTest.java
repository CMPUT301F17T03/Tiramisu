package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dizhang.com.example.View.EventViewActivity;
import dizhang.com.example.View.HistoryActivity;
import dizhang.com.example.View.MapActivity;

/**
 * Created by ggranked on 2017-10-23.
 */

public class HistoryActivityTest extends ActivityInstrumentationTestCase2<HistoryActivity> {
    private Solo solo;

    public HistoryActivityTest() {
        super(HistoryActivity.class);
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
        HistoryActivity activity = (HistoryActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", HistoryActivity.class);

        //solo.enterText((EditText) solo.getView(R.id.editText), "Test Search");
        solo.clickOnButton("type");
        solo.clickOnButton("comment");
        solo.clickOnButton("See the Result in Map");

        solo.assertCurrentActivity("Wrong Activity", MapActivity.class);
        solo.goBack();

        solo.assertCurrentActivity("Wrong Activity", HistoryActivity.class);

        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity", EventViewActivity.class);

        //this will delete the item from historyList:
        //solo.clickLongInList(0);

        //check if it really delete item in the historyList
        /*
        final ListView HistoryList = activity.getHistoryList();
        Event event = (Event) HistoryList.getItemAtPosition(0);
        assertEquals("Test Search", event.getTitle());
        */

        //will be made testable once historyList is implemented

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}


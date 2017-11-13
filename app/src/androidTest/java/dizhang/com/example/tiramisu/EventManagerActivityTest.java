package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dizhang.com.example.Control.EditEventActivity;
import dizhang.com.example.View.EventManagerActivity;
import dizhang.com.example.View.EventViewActivity;

/**
 * Created by ggranked on 2017-10-23.
 */

public class EventManagerActivityTest extends ActivityInstrumentationTestCase2<EventManagerActivity> {
    private Solo solo;

    public EventManagerActivityTest(){
        super(EventManagerActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("Setup", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }
    /*

    public void testClickOnButton(){
        EventManagerActivity activity = (EventManagerActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", EventManagerActivity.class);

        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity", EventViewActivity.class);

        solo.clickOnButton("Edit This Event");
        solo.assertCurrentActivity("Wrong Activity", EditEventActivity.class);

        //assertTrue(solo.waitForText("Habit New"));
        solo.clickOnButton("Add photo");
        solo.clickOnCheckBox(0);
        //solo.enterText((EditText) solo.getView(R.id.editText), "Test Event");
        //solo.enterText((EditText) solo.getView(R.id.EditComment), "Test event comment");
        //solo.enterText((EditText) solo.getView(R.id.editText2), "Test Habit!");
        solo.clickOnButton("Done");

        //BELOW IS TO CHECK IF ITEM ACTUALLY ADDED TO THE LIST, CANNOT
        //PERFORM RIGHT NOW, BUT WILL BE TESTABLE LATER!!!!


        /*
        final ListView EventList = activity.getEventList();
        Event event = (Event) EventList.getItemAtPosition(0);
        assertEquals("Test event comment", event.getComment());
        */
    /*


        solo.assertCurrentActivity("Wrong Activity", EventViewActivity.class);

        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity", EventManagerActivity.class);

    }
    */


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


}

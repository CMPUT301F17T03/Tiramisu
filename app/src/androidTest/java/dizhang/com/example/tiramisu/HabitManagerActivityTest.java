package dizhang.com.example.tiramisu;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dizhang.com.example.Control.EditHabitActivity;
import dizhang.com.example.Control.HabitNewActivity;
import dizhang.com.example.View.HabitManagerActivity;
import dizhang.com.example.View.HomeActivity;

/**
 * Created by ggranked on 2017-10-23.
 */

public class HabitManagerActivityTest extends ActivityInstrumentationTestCase2<HabitManagerActivity> {
    private Solo solo;

    public HabitManagerActivityTest(){
        super(HabitManagerActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Log.d("Setup", "setUp()");
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testClickOnButton(){
        HabitManagerActivity activity = (HabitManagerActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", HabitManagerActivity.class);

        solo.clickOnButton("Create New");
        solo.assertCurrentActivity("Wrong Activity", HabitNewActivity.class);
        assertTrue(solo.waitForText("Habit New"));
        solo.enterText((EditText) solo.getView(R.id.editText), "Test Habit!");
        solo.enterText((EditText) solo.getView(R.id.editText), "Test Habit!");
        solo.enterText((EditText) solo.getView(R.id.editText2), "Test Habit!");
        solo.clickOnButton("Done");


        //BELOW IS TO CHECK IF ITEM ACTUALLY ADDED TO THE LIST, CANNOT
        //PERFORM RIGHT NOW, BUT WILL BE TESTABLE LATER!!!!


        /*
        final ListView HabitList = activity.getHabitList();
        Habit habit = (Habit) HabitList.getItemAtPosition(0);
        assertEquals("Test Habit!", event.getTitle());
        */

        solo.assertCurrentActivity("Wrong Activity", HabitManagerActivity.class);

        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity", EditHabitActivity.class);
        solo.enterText((EditText) solo.getView(R.id.editText), "Test Edit Habit!");
        solo.enterText((EditText) solo.getView(R.id.editText), "Test Edit Habit!");
        solo.enterText((EditText) solo.getView(R.id.editText2), "Test Edit Habit!");
        solo.clickOnButton("Done");

        //BELOW IS TO CHECK IF ITEM ACTUALLY CHANGED ON THE LIST, CANNOT
        //PERFORM RIGHT NOW, BUT WILL BE TESTABLE LATER!!!!


        /*
        final ListView HabitList = activity.getHabitList();
        Habit habit = (Habit) HabitList.getItemAtPosition(0);
        assertEquals("Test Habit!", event.getTitle());
        */



        solo.assertCurrentActivity("Wrong Activity", HabitManagerActivity.class);

        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity", EditHabitActivity.class);

        solo.clickOnButton("Delete");
        solo.assertCurrentActivity("Wrong Activity", HabitManagerActivity.class);

        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);

    }


    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
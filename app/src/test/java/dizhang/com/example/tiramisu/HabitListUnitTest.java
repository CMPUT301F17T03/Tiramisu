package dizhang.com.example.tiramisu;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.HabitList;
import dizhang.com.example.Model.User;
import dizhang.com.example.Model.UserList;

/**
 * Created by ggranked on 2017-11-13.
 */

public class HabitListUnitTest extends ActivityInstrumentationTestCase2 {
    public HabitListUnitTest() {super(dizhang.com.example.View.HabitManagerActivity.class);}

    Date date = new Date();
    ArrayList<String> freq = new ArrayList<String>();

    public void testAddHabit() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HabitList habitList = new HabitList();
        habitList.add(habit);
        assertTrue(habitList.hasHabit(habit));
    }

    public void testDeleteHabit() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HabitList habitList = new HabitList();
        habitList.delete(habit);
        assertFalse(habitList.hasHabit(habit));

    }

    public void testGetHabit() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HabitList habitList = new HabitList();
        habitList.add(habit);
        assertEquals(habitList.getHabit(0), habit);

    }

    public void testHasHabit() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HabitList habitList = new HabitList();
        habitList.add(habit);
        assertTrue(habitList.hasHabit(habit));

    }





}
package dizhang.com.example.tiramisu;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.EventList;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.HabitList;

/**
 * Created by ggranked on 2017-11-13.
 */

public class EventListUnitTest extends ActivityInstrumentationTestCase2 {
    public EventListUnitTest() {
        super(dizhang.com.example.View.EventManagerActivity.class);
    }

    Date date = new Date();
    ArrayList<String> freq = new ArrayList<String>();

    public void testAddEvent() {
        Habit habit = new Habit("testHabit", "testing reason", date, freq);
        Event event = new Event(habit, date);
        EventList eventList = new EventList();
        eventList.add(event);
        assertTrue(eventList.hasEvent(event));
    }

    public void testDeleteEvent() {
        Habit habit = new Habit("testHabit", "testing reason", date, freq);
        Event event = new Event(habit, date);
        EventList eventList = new EventList();
        eventList.delete(event);
        assertFalse(eventList.hasEvent(event));

    }

    public void testGetEvent() {
        Habit habit = new Habit("testHabit", "testing reason", date, freq);
        Event event = new Event(habit, date);
        EventList eventList = new EventList();
        eventList.add(event);
        assertEquals(eventList.getEvent(0), event);

    }

    public void testHasEvent() {
        Habit habit = new Habit("testHabit", "testing reason", date, freq);
        Event event = new Event(habit, date);
        EventList eventList = new EventList();
        eventList.add(event);
        assertTrue(eventList.hasEvent(event));

    }
}






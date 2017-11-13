package dizhang.com.example.tiramisu;

import android.test.ActivityInstrumentationTestCase2;

import org.apache.http.conn.HttpInetSocketAddress;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import dizhang.com.example.Model.Event;
import dizhang.com.example.Model.Habit;
import dizhang.com.example.Model.History;
import dizhang.com.example.Model.HistoryList;

import static org.junit.Assert.assertEquals;

/**
 * Created by ggranked on 2017-11-13.
 */

public class HistoryListUnitTest extends ActivityInstrumentationTestCase2{
    public HistoryListUnitTest() {super(dizhang.com.example.View.HistoryActivity.class);}

    Date date = new Date();
    ArrayList<String> freq = new ArrayList<String>();

    public void testAddHistory() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HistoryList historyList = new HistoryList();
        Event event = new Event(habit,date);
        History history = new History(event, date);
        historyList.add(history);

        assertTrue(historyList.hasHistory(history));
    }

    public void testDeleteHistory() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HistoryList historyList = new HistoryList();
        Event event = new Event(habit,date);
        History history = new History(event, date);
        historyList.add(history);
        historyList.delete(history);

        assertFalse(historyList.hasHistory(history));
    }

    public void testGetHistory() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HistoryList historyList = new HistoryList();
        Event event = new Event(habit,date);
        History history = new History(event, date);
        historyList.add(history);

        assertEquals(historyList.getHistory(0), history);

    }

    public void testHasHistory() {
        Habit habit = new Habit ("testHabit", "testing reason",date,freq);
        HistoryList historyList = new HistoryList();
        Event event = new Event(habit,date);
        History history = new History(event, date);
        historyList.add(history);

        assertTrue(historyList.hasHistory(history));

    }





}

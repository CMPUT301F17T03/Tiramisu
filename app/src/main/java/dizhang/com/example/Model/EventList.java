package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Class Name: EventList
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class EventList {
    private ArrayList<Event> events = new ArrayList<Event>();

    public EventList(){}

    public Event getEvent(int index) {
        return events.get(index);
    }

    public boolean hasEvent (Event event){
        return events.contains(event);
    }

    public void add(Event event){
        events.add(event);
    }

    public void delete(Event event) {
        events.remove(event);
    }
}

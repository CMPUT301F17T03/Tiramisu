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

/**
 * Represents an EventList
 * @version 1.0
 * @since 1.0
 */
public class EventList {
    private ArrayList<Event> events = new ArrayList<Event>();

    public EventList(){}
    /**
     *@param index
     * @return
     */

    public Event getEvent(int index) {
        return events.get(index);
    }
    /**
     *@return
     * @param event
     */

    public boolean hasEvent (Event event){
        return events.contains(event);
    }
    /**
     *@return
     * @param event
     */

    public void add(Event event){
        events.add(event);
    }
    /**
     *@param event
     *@return
     */

    public void delete(Event event) {
        events.remove(event);
    }
}

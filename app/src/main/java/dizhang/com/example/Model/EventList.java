package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Created by ggranked on 2017-11-06.
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

package dizhang.com.example.Model;

import java.util.Date;

/**
 * Created by ggranked on 2017-11-06.
 */

public class History {
    private Event event;
    private Date finishDate;

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

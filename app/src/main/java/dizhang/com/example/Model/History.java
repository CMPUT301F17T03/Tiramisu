package dizhang.com.example.Model;

import java.util.Date;

/**
 * Class Name: History
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
 *Represents a History
 * @version 1.0
 * @since 1.0
 */
public class History {
    private Event event;
    private Date finishDate;

    public History (Event event, Date finishDate) {
        this.event = event;
        this.finishDate = finishDate;
    }
    /**
     *@return
     */
    public Date getFinishDate() {
        return finishDate;
    }
    /**
     *@param finishDate
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    /**
     * @return
     */
    public Event getEvent() {
        return event;
    }
    /**
     *@param event
     */

    public void setEvent(Event event) {
        this.event = event;
    }
}

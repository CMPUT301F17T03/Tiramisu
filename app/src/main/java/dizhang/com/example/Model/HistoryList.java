package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Class Name: HistoryList
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class HistoryList {
    private ArrayList<History> historys = new ArrayList<History>();

    public HistoryList(){}

    public History getHistory(int index) {
        return historys.get(index);
    }

    public boolean hasHistory (History history){
        return historys.contains(history);
    }

    public void add(History history){
        historys.add(history);
    }

    public void delete(History history) {
        historys.remove(history);
    }
}

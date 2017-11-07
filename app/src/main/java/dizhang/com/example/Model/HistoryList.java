package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Created by ggranked on 2017-11-06.
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

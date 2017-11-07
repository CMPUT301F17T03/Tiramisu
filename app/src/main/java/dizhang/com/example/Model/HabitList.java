package dizhang.com.example.Model;

import java.util.ArrayList;

/**
 * Created by ggranked on 2017-11-06.
 */

public class HabitList {
    private ArrayList<Habit> habits = new ArrayList<Habit>();

    public HabitList(){}

    public Habit getHabit(int index) {
        return habits.get(index);
    }

    public boolean hasHabit (Habit habit){
        return habits.contains(habit);
    }

    public void add(Habit habit){
        habits.add(habit);
    }

    public void delete(Habit habit) {
        habits.remove(habit);
    }

}

package dizhang.com.example.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Name: HabitList
 *
 * Created by dz2 on 2017-10-23.
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */

public class HabitList implements Serializable {
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

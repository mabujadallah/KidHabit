package com.example.kidhabit.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.kidhabit.data.Habit;
import com.example.kidhabit.data.HabitRepository;

import java.util.List;

public class HabitChecklistViewModel extends AndroidViewModel {

    private HabitRepository mHabitRepository;
    private LiveData<List<Habit>> mAllHabits;

    public HabitChecklistViewModel(Application application) {
        super(application);
        mHabitRepository = new HabitRepository(application);
        mAllHabits = mHabitRepository.getAllHabits();
        
        // Insert default habits if the database is empty
        mAllHabits.observeForever(habits -> {
            if (habits == null || habits.isEmpty()) {
                mHabitRepository.insert(new Habit("Brush Teeth", false));
                mHabitRepository.insert(new Habit("Do Homework", false));
                mHabitRepository.insert(new Habit("Drink Water", false));
                mHabitRepository.insert(new Habit("Clean Room", false));
            }
        });
    }

    public LiveData<List<Habit>> getAllHabits() {
        return mAllHabits;
    }

    public void toggleHabit(Habit habit, RewardsViewModel rewardsViewModel) {
        boolean wasCompleted = habit.isCompleted();
        habit.setCompleted(!wasCompleted);
        mHabitRepository.update(habit);

        if (!wasCompleted) {
            rewardsViewModel.addStar();
        }
    }
}

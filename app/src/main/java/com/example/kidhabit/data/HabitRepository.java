package com.example.kidhabit.data;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HabitRepository {

    private HabitDao mHabitDao;
    private LiveData<List<Habit>> mAllHabits;
    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    public HabitRepository(Application application) {
        HabitDatabase db = HabitDatabase.getDatabase(application);
        mHabitDao = db.habitDao();
        mAllHabits = mHabitDao.getHabits();
    }

    public LiveData<List<Habit>> getAllHabits() {
        return mAllHabits;
    }

    public void insert(Habit habit) {
        mExecutor.execute(() -> {
            mHabitDao.insertHabit(habit);
        });
    }

    public void update(Habit habit) {
        mExecutor.execute(() -> {
            mHabitDao.updateHabit(habit);
        });
    }
}

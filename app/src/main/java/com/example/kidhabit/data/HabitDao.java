package com.example.kidhabit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HabitDao {

    @Query("SELECT * FROM habits")
    LiveData<List<Habit>> getHabits();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHabit(Habit habit);

    @Update
    void updateHabit(Habit habit);
}

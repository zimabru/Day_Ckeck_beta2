package com.udemy_3.daycheckbeta2.ROOM.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.udemy_3.daycheckbeta2.ROOM.Tables.Day;

import java.util.List;

@Dao
public interface DayDao {

    @Insert
    void insertDay(Day day);

    @Update
    void updateDay(Day day);

    @Delete
    void deleteDay(Day day);

    @Query("SELECT *FROM day_table")
    LiveData<List<Day>> getDay();



}

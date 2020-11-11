package com.udemy_3.daycheckbeta2.ROOM.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.udemy_3.daycheckbeta2.ROOM.Tables.Day;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Statistic;

import java.util.List;

@Dao
public interface StatisticDao {
    @Insert
    void insert(Statistic statistic);

    @Update
    void update(Statistic statistic);

    @Query("SELECT *FROM statistic_table")
    LiveData<List<Statistic>> getStatistic();

}

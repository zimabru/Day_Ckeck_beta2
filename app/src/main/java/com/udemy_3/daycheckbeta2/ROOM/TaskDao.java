package com.udemy_3.daycheckbeta2.ROOM;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.udemy_3.daycheckbeta2.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Query("DELETE FROM task_table")
    int deleteAll();

    @Delete
    int deleteTask(Task task);

    @Update
    int update(Task task);

    @Query("SELECT *FROM task_table ORDER BY id DESC")
    LiveData<List<Task>> getAllTasks();


}

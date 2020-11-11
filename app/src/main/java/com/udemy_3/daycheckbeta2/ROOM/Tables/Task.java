package com.udemy_3.daycheckbeta2.ROOM.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name_task")
    private String nameTask;

    @ColumnInfo(name = "time_task")
    private String timeTask;


    @ColumnInfo(name ="color_task")
    private String Color;


    public Task() {
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getTimeTask(String timeTask) {
        return timeTask;
    }

    public void setTimeTask(String timeTask) {
        this.timeTask = timeTask;
    }

    public String getTimeTask() {
        return timeTask;
    }



}

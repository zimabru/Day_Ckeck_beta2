package com.udemy_3.daycheckbeta2.ROOM.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "day_table")
public class Day {



    @PrimaryKey(autoGenerate= true)
    private int id_day;

    @ColumnInfo(name ="number_task")
    private int numberTask;



    @ColumnInfo(name="time_day")
    private String timeDay;

    @ColumnInfo(name="num_task_checked")
    private int numTaskChecked;

    @ColumnInfo(name="Day_checked")
    private boolean DayChecked;

    public Day() {
    }
    public void setId_day(int id_day) {
        this.id_day = id_day;
    }


    public String getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(String timeDay) {
        this.timeDay = timeDay;
    }

    public int getId_day(){
        return id_day;
    }

    public int getNumberTask() {
        return numberTask;
    }

    public void setNumberTask(int numberTask) {
        this.numberTask = numberTask;
    }

    public int getNumTaskChecked() {
        return numTaskChecked;
    }

    public void setNumTaskChecked(int numTaskChecked) {
        this.numTaskChecked = numTaskChecked;
    }

    public boolean getDayChecked() {
        return DayChecked;
    }

    public void setDayChecked(boolean dayChecked) {
        DayChecked = dayChecked;
    }
}

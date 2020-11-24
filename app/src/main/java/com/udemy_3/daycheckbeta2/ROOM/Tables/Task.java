package com.udemy_3.daycheckbeta2.ROOM.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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


    @ColumnInfo(name ="checked_task")
    private boolean checkedTask;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getId() == task.getId() &&
                Objects.equals(getNameTask(), task.getNameTask()) &&
                Objects.equals(getTimeTask(), task.getTimeTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameTask(), getTimeTask());
    }

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

    public boolean isCheckedTask() {
        return checkedTask;
    }

    public void setCheckedTask(boolean checkedTask) {
        this.checkedTask = checkedTask;
    }


}

package com.udemy_3.daycheckbeta2.ROOM.Tables;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Statistic_table")
public class Statistic{


    public void setId_count(int id_count) {
        this.id_count = id_count;
    }

    @PrimaryKey(autoGenerate = true)
    private int id_count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @ColumnInfo(name = "count")
    private int count;


    public Statistic() {
    }

    public int getId_count() {
        return id_count;
    }



}

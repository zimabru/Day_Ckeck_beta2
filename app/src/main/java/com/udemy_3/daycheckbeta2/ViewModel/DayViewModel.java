package com.udemy_3.daycheckbeta2.ViewModel;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.udemy_3.daycheckbeta2.ROOM.REPOSITORIES.Repository_Day;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Day;

import java.util.List;

public class DayViewModel extends AndroidViewModel {
    private Repository_Day repository_day;
    private LiveData<List<Day>> theDay;

    public DayViewModel(Application application){
        super(application);
        repository_day = new Repository_Day(application);
        theDay = repository_day.getTheDay();
    }


    public void insertDay(Day day){
        repository_day.InsertDay(day);
    }

    public void updateDay(Day day){
        repository_day.UpdateDay(day);
    }

    public void deleteDay(Day day){
        repository_day.UpdateDay(day);
    }

    public  LiveData<List<Day>> getTheDay(){
        return theDay;
    }



}

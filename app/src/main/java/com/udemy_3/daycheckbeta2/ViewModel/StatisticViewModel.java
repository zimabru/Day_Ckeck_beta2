package com.udemy_3.daycheckbeta2.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.udemy_3.daycheckbeta2.ROOM.REPOSITORIES.Repository_Statistic;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Statistic;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Task;

import java.util.List;

public class StatisticViewModel {
    private Repository_Statistic repository_statistic;
    private LiveData<List<Statistic>> theStatistic;


    public StatisticViewModel(Application application){
        repository_statistic = new Repository_Statistic(application);
        theStatistic = repository_statistic.getTheStatistic();
    }

    public void insertStatistic (Statistic statistic){
        repository_statistic.InsertStatistic(statistic);
    }
    
    public void updateStatistic(Statistic statistic){
        repository_statistic.UpdateStatistic(statistic);
    }

    public LiveData<List<Statistic>> getTheStatistic(){
        return  theStatistic;
    }
}

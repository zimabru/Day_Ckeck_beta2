package com.udemy_3.daycheckbeta2.ROOM.REPOSITORIES;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.udemy_3.daycheckbeta2.ROOM.Dao.StatisticDao;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Statistic;
import com.udemy_3.daycheckbeta2.ROOM.TaskDatabase;

import java.util.List;

public class Repository_Statistic {
    private StatisticDao statisticDao;
    private LiveData<List<Statistic>> theStatistic;


    public Repository_Statistic(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        statisticDao = taskDatabase.statisticDao();
        theStatistic = statisticDao.getStatistic();
    }


    public void InsertStatistic(Statistic statistic) {
        new InsertStatisticAsyncTask(statisticDao).execute(statistic);
    }

    private static class InsertStatisticAsyncTask extends AsyncTask<Statistic, Void, Void> {
        private StatisticDao statisticDao;

        public InsertStatisticAsyncTask(StatisticDao statisticDao) {
            this.statisticDao = statisticDao;
        }

        @Override
        protected Void doInBackground(Statistic... statistics) {
            statisticDao.insert(statistics[0]);
            return null;
        }
    }


    public void UpdateStatistic(Statistic statistic) {
        new UpdateStatisticAsyncTask(statisticDao).execute(statistic);
    }

    private static class UpdateStatisticAsyncTask extends AsyncTask<Statistic, Void, Void> {
        private StatisticDao statisticDao;

        public UpdateStatisticAsyncTask(StatisticDao statisticDao) {
            this.statisticDao = statisticDao;
        }

        @Override
        protected Void doInBackground(Statistic... statistics) {
            statisticDao.insert(statistics[0]);
            return null;
        }
    }

    public LiveData<List<Statistic>> getTheStatistic() {
        return theStatistic;
    }
}


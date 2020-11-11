package com.udemy_3.daycheckbeta2.ROOM.REPOSITORIES;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Delete;

import com.udemy_3.daycheckbeta2.ROOM.Dao.DayDao;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Day;
import com.udemy_3.daycheckbeta2.ROOM.TaskDatabase;

import java.util.List;

public class Repository_Day {
    private DayDao dayDao;
    private LiveData<List<Day>> theDay;

   public  Repository_Day(Application application){
       TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
       dayDao = taskDatabase.dayDao();
       theDay= dayDao.getDay();
   }

   public void UpdateDay(Day day){
       new UpdateDayAsyncTask(dayDao).execute(day);
   }

   private static class UpdateDayAsyncTask extends AsyncTask<Day, Void, Void> {
       private DayDao dayDao;

       public UpdateDayAsyncTask(DayDao dayDao){
           this.dayDao = dayDao;
       }

       @Override
       protected Void doInBackground(Day... days) {
           dayDao.updateDay(days[0]);
           return null;
       }
   }


   public void InsertDay(Day day){
        new UpdateDayAsyncTask(dayDao).execute(day);
    }

    private static class InsertDayAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        public InsertDayAsyncTask(DayDao dayDao){
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... days) {
            dayDao.insertDay(days[0]);
            return null;
        }
    }



    public void DeleteDay(Day day){
        new UpdateDayAsyncTask(dayDao).execute(day);
    }

    private static class DeleteDayAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        public DeleteDayAsyncTask(DayDao dayDao){
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... days) {
            dayDao.deleteDay(days[0]);
            return null;
        }
    }

    public LiveData<List<Day>> getTheDay(){
       return theDay;
    }
}

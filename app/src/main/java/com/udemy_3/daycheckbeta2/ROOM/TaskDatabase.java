package com.udemy_3.daycheckbeta2.ROOM;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.udemy_3.daycheckbeta2.ROOM.Dao.DayDao;
import com.udemy_3.daycheckbeta2.ROOM.Dao.StatisticDao;
import com.udemy_3.daycheckbeta2.ROOM.Dao.TaskDao;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Day;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Statistic;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Task;

@Database(entities ={Task.class, Day.class, Statistic.class}, version =4,exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase INSTANCE;
    public abstract TaskDao taskDao();
    public abstract DayDao dayDao();
    public abstract  StatisticDao statisticDao();

    public static synchronized TaskDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class,
                    "task_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;
        private DayDao dayDao;
        private StatisticDao statisticDao;

        public PopulateDbAsyncTask(TaskDatabase db) {
            taskDao = db.taskDao();
            dayDao = db.dayDao();
            statisticDao = db.statisticDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}

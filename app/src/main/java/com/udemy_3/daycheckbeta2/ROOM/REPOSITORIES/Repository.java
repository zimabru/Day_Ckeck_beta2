package com.udemy_3.daycheckbeta2.ROOM.REPOSITORIES;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.udemy_3.daycheckbeta2.ROOM.Dao.TaskDao;
import com.udemy_3.daycheckbeta2.ROOM.Tables.Task;
import com.udemy_3.daycheckbeta2.ROOM.TaskDatabase;

import java.util.List;

public class Repository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTask;

    public Repository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();
        allTask = taskDao.getAllTasks();

    }

    public void insert(Task task) {
        new InsertTaskAsyncTask(taskDao).execute(task);

    }

    public void update(Task task) {
        new UpdateTaskAsyncTask(taskDao).execute(task);

    }

    public void deleteTask(Task task) {
        new DeleteTaskTaskAsyncTask(taskDao).execute(task);
    }

    public void deleteAll() {
        new DeleteAllTaskAsyncTask(taskDao).execute();

    }

    public LiveData<List<Task>> getAllTask() {
        return allTask;
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public DeleteTaskTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.deleteTask(tasks[0]);
            return null;
        }
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;

        public DeleteAllTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            taskDao.deleteAll();
            return null;
        }


    }

}

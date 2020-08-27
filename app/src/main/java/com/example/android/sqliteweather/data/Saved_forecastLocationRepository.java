package com.example.android.sqliteweather.data;

import android.app.Application;
import android.os.AsyncTask;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class Saved_forecastLocationRepository {
    private Saved_forecastLocationDao mSavedForecastLocationDao;

    public Saved_forecastLocationRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mSavedForecastLocationDao = db.saved_forecastLocationDao();
    }
    public void insertSavedForecastLocation(Saved_forecastLocation location){
        new InsertAsyncTask(mSavedForecastLocationDao).execute(location);
    }
    public void deleteSavedForecastLocation(Saved_forecastLocation location){
        new DeleteAsyncTask(mSavedForecastLocationDao).execute(location);
    }
    public LiveData<List<Saved_forecastLocation>> getAllLocations(){
        return mSavedForecastLocationDao.getAllLocations();
    }
    public Saved_forecastLocation getSavedLocationsByName(String location){
        Saved_forecastLocation savedForecastLocation = new Saved_forecastLocation();
        try{
            savedForecastLocation = new getSavedLocationsByNameAsyncTask(mSavedForecastLocationDao).execute(location).get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return savedForecastLocation;
    }

    private static class getSavedLocationsByNameAsyncTask extends AsyncTask<String, Void, Saved_forecastLocation>{
        private Saved_forecastLocationDao mAsyncTaskDao;
        getSavedLocationsByNameAsyncTask(Saved_forecastLocationDao dao)
        {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Saved_forecastLocation doInBackground(String...strings){
            return mAsyncTaskDao.getSavedLocationsByName(strings[0]);
        }
    }
    private static class InsertAsyncTask extends AsyncTask<Saved_forecastLocation, Void, Void>{
        private Saved_forecastLocationDao mAsyncTaskDao;
        InsertAsyncTask(Saved_forecastLocationDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Saved_forecastLocation... locations){
            mAsyncTaskDao.insert(locations[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Saved_forecastLocation, Void, Void>{
        private Saved_forecastLocationDao mAsyncTaskDao;
        DeleteAsyncTask(Saved_forecastLocationDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Saved_forecastLocation... locations){
            mAsyncTaskDao.delete(locations[0]);
            return null;
        }
    }
}

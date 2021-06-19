package com.example.to_dolist;

import android.app.Application;

import androidx.room.Room;

import com.example.to_dolist.room.AppDataBase;
import com.example.to_dolist.room.AppDataBaseBackLog;

public class App extends Application {
    public static App instance;
    private AppDataBase database;
    private AppDataBaseBackLog appDataBaseBackLog;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database").allowMainThreadQueries().build();
        appDataBaseBackLog = Room.databaseBuilder(this, AppDataBaseBackLog.class, "databaseBackLog").allowMainThreadQueries().build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }

    public AppDataBaseBackLog getAppDataBaseBackLog() {
        return appDataBaseBackLog;
    }
}

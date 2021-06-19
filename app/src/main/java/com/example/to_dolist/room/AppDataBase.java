package com.example.to_dolist.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.to_dolist.TodayModel;

@Database(entities = {TodayModel.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TodayDao todayDao();
}

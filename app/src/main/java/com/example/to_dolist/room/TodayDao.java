package com.example.to_dolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.to_dolist.TodayModel;

import java.util.List;

@Dao
public interface TodayDao {
    @Query("SELECT * FROM todayModel")
    List<TodayModel> getAll();

    @Query("SELECT * FROM todayModel")
    LiveData<List<TodayModel>> getAllLive();

    @Insert
    void insert(TodayModel todayModel);

    @Update
    void update(TodayModel todayModel);

    @Delete
    void delete(TodayModel todayModel);
}
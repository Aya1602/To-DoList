package com.example.to_dolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.to_dolist.DateModel;
import com.example.to_dolist.TodayModel;

import java.util.List;

@Dao
public interface TodayDao {

    @Query("SELECT * FROM todayModel")
    List<TodayModel> getAll();

    @Query("SELECT * FROM todayModel where date = :date")
    List<TodayModel> getAllToday(String date);

    @Query("SELECT * FROM todayModel")
    LiveData<List<TodayModel>> getAllLive();

    @Query("SELECT * FROM todayModel where date = :date")
    LiveData<List<TodayModel>> getAllLiveToday(String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodayModel todayModel);

    @Update
    void update(TodayModel todayModel);

    @Delete
    void delete(TodayModel todayModel);

//    @Query("SELECT * FROM todaymodel as List<TodayModel> group by date")
//    List<DateModel> getHistory();

}
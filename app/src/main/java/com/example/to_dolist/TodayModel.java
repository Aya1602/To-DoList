package com.example.to_dolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class TodayModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private boolean isDone;
    private String date;
    private boolean isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TodayModel(String title, boolean isDone, String date) {
        this.title = title;
        this.isDone = isDone;
        this.date = date;
    }

}

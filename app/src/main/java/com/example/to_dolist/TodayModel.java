package com.example.to_dolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodayModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private boolean isDone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TodayModel(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }
}

package com.example.to_dolist;

public interface Listener {
    void onClick(String s);

    void onDelete(TodayModel model);
}
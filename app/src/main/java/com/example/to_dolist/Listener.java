package com.example.to_dolist;

public interface Listener {
    void onClick(String s);

    void ondoneClick(TodayModel todayModel);

    void onDelete(int position,TodayModel model);

    void addToBacklog(int position,TodayModel model);
}
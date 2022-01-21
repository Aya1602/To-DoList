package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    List<TodayModel> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//        history.addAll(App.getInstance().getDatabase().todayDao().getHistory());

//        Log.e("history",String.valueOf(history.size()) + "size");
//
//        for (TodayModel f : history){
//            Log.e("history",f.getTitle());
//        }

    }


}

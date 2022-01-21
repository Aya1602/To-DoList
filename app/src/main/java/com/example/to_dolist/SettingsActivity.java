package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.WidgetContainer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.to_dolist.adapter.Adapter;

public class SettingsActivity extends AppCompatActivity implements Listener, EditTextListener {

    private Adapter adapter;
    private ImageView imgExitSettings;
    private TextView history, review, sendFeedback;
    private View goals;
    private Button howWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        adapter = new Adapter();
        adapter.setListener(this);
        adapter.setEditTextListener(this);
        imgExitSettings = findViewById(R.id.img_exit_settings);
        howWork = findViewById(R.id.how_work);
        history = findViewById(R.id.history);
        review = findViewById(R.id.review);
        sendFeedback = findViewById(R.id.feedback);
        goals = findViewById(R.id.goals);

        imgExitSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, TodayActivity.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, HistoryActivity.class);
                startActivity(i);
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.tezsat.tezsat"));
                startActivity(i);
            }
        });

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"aygerim.m1602@gmail.com"});
                startActivity(Intent.createChooser(intent, "Write your feedback please )"));
            }
        });

         goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.tezsat.tezsat"));
                startActivity(i);
            }

        });

         howWork.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(SettingsActivity.this,WebActivity.class);
                 startActivity(i);
             }
         });
    }

    @Override
    public void onClick(String s) {

    }

    @Override
    public void ondoneClick(TodayModel todayModel) {

    }

    @Override
    public void onDelete(int position,TodayModel model) { // не нужно
    }

    @Override
    public void addToBacklog(int position,TodayModel model) {

    }

    @Override
    public void addItem(String s) {

    }
}

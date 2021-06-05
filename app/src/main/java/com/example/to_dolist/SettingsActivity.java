package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity implements OnClickAdd {

    private Adapter adapter;
    private ImageView imgExitSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        adapter = new Adapter();
        adapter.setOnClickAdd(this);

        imgExitSettings = findViewById(R.id.img_exit_settings);

        imgExitSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, Main4Activity.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(String s) {

    }
}

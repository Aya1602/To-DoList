package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity implements OnClickAdd {
    private Adapter adapter;
    private RecyclerView recyclerView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = findViewById(R.id.rv);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnClickAdd(this);

        textView = findViewById(R.id.priorities_text);
    }

    @Override
    public void onClick(String s) {
        adapter.addItem(s);
        Log.e("tag", "onClick: " + s);
        textView.setText(s);
    }
}

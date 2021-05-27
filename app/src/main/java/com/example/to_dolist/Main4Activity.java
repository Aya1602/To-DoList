package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity implements OnClickAdd {
    private Adapter adapter;
    private EditText editText;
    private RecyclerView recyclerView;
    private Button cancel,addBtn;

    private String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = findViewById(R.id.rv);
        adapter = new Adapter();
        adapter.setOnClickAdd(this);
        recyclerView.setAdapter(adapter);

        editText = findViewById(R.id.et_rv);

        cancel = findViewById(R.id.btn_cancel);
        addBtn = findViewById(R.id.btn_add_the_text);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(editText.getText().toString());
                editText.setText("");

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });
    }

    @Override
    public void onClick(String s) {
        Log.e("test2333", "onClick: "+ s );

    }
}

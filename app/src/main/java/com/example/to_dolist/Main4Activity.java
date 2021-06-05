package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Main4Activity extends AppCompatActivity implements OnClickAdd {
    private Adapter adapter;
    private EditText editText;
    private RecyclerView recyclerView;
    private Button cancel, addBtn;

    //private String value;

    private ImageView imgSettings;
    private ImageView addImg;

    private FrameLayout firstContainer, secondContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = findViewById(R.id.rv);
        imgSettings = findViewById(R.id.img_more);
        addImg = findViewById(R.id.add_img);
        firstContainer = findViewById(R.id.layout_1);
        secondContainer = findViewById(R.id.bottom_container);
        adapter = new Adapter();
        adapter.setOnClickAdd(this);
        recyclerView.setAdapter(adapter);

        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, SettingsActivity.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, Backlog.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });

        editText = findViewById(R.id.et_rv);

        cancel = findViewById(R.id.btn_cancel);
        addBtn = findViewById(R.id.btn_add_the_text);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(editText.getText().toString());
                editText.setText("");
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                firstContainer.setVisibility(View.GONE);
                secondContainer.setVisibility(View.VISIBLE);

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

        firstContainer.setVisibility(View.GONE);
        secondContainer.setVisibility(View.VISIBLE);


            }
        });

        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (isOpen) {
                    firstContainer.setVisibility(View.VISIBLE);
                    secondContainer.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(String s) {
        Log.e("test2333", "onClick: " + s);

    }
}

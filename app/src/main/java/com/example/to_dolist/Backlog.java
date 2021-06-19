package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

import com.example.to_dolist.adapter.Adapter;
import com.google.android.material.snackbar.Snackbar;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.List;

public class Backlog extends AppCompatActivity implements Listener, EditTextListener {

    private Adapter adapter;
    private ImageView imgExitBacklog;
//    private EditText editText;
    private RecyclerView recyclerView;
    private Button btnCancelBacklog, btnAddTheTaskBacklog;
    private Button addToTodayBacklog;

    private String textFromET;

    private FrameLayout firstContainer, secondContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog);
        adapter = new Adapter();
        adapter.setListener(this);
        adapter.setEditTextListener(this);
        recyclerView = findViewById(R.id.rv_backlog);
        recyclerView.setAdapter(adapter);


        addToTodayBacklog = findViewById(R.id.add_to_today_backlog);
        addToTodayBacklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(addToTodayBacklog, "Please select at least 1 task.", Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                snackbar.setTextColor(0XFFFFFFFF);
                snackbar.setBackgroundTint(0XFF181717);
                snackbar.setActionTextColor(0XFF096DBE);
                snackbar.show();
            }
        });

        App.getInstance().getAppDataBaseBackLog().todayDao().getAllLive().observe(this, new Observer<List<TodayModel>>() {
            @Override
            public void onChanged(List<TodayModel> list) {
                adapter.addItem(list);
            }
        });


        imgExitBacklog = findViewById(R.id.img_exit_backlog);
        imgExitBacklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Backlog.this, TodayActivity.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });


        firstContainer = findViewById(R.id.layout_backlog);
        secondContainer = findViewById(R.id.bottom_container_backlog);

//        editText = findViewById(R.id.et_rv_backlog);
        btnCancelBacklog = findViewById(R.id.btn_cancel_backlog);
        btnAddTheTaskBacklog = findViewById(R.id.btn_add_the_task_backlog);


        btnAddTheTaskBacklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                adapter.addItem(editText.getText/().toString());
//                editText.setText("");
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                firstContainer.setVisibility(View.GONE);
                secondContainer.setVisibility(View.VISIBLE);

                if (!textFromET.isEmpty()) {
                    TodayModel todayModel = new TodayModel(textFromET, false);
                    textFromET = "";
                    App.getInstance().getAppDataBaseBackLog().todayDao().insert(todayModel);
                } else {
                    Log.e("test1232", "onClick: empty text" );
                }

            }
        });

        btnCancelBacklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editText.setText("");
                adapter.notifyDataSetChanged();
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

    }

    @Override
    public void onDelete(TodayModel model) {
        App.getInstance().getAppDataBaseBackLog().todayDao().delete(model);
    }

    @Override
    public void addItem(String s) {
        textFromET = s;
    }
}

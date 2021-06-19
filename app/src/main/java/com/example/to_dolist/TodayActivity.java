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
import android.widget.Toast;

import com.example.to_dolist.adapter.Adapter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.List;

public class TodayActivity extends AppCompatActivity implements Listener, EditTextListener {
    private Adapter adapter;
//    private EditText editText;
    private RecyclerView recyclerView;
    private Button cancel, addBtn;
    private TodayModel todayModel;
    //private String value;

    private String textFromET;

    private ImageView imgSettings;
    private ImageView addImg;

    private FrameLayout firstContainer, secondContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        recyclerView = findViewById(R.id.rv);
        imgSettings = findViewById(R.id.img_more);
        addImg = findViewById(R.id.add_img);
        firstContainer = findViewById(R.id.layout_1);
        secondContainer = findViewById(R.id.bottom_container);
        adapter = new Adapter();

        adapter.addItem(App.getInstance().getDatabase().todayDao().getAll());

        App.getInstance().getDatabase().todayDao().getAllLive().observe(this, new Observer<List<TodayModel>>() {
            @Override
            public void onChanged(List<TodayModel> list) {
                adapter.addItem(list);
            }
        });

        adapter.setListener(this);
        adapter.setEditTextListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayActivity.this, SettingsActivity.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayActivity.this, Backlog.class);
                overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
                startActivity(intent);
            }
        });

//        editText = findViewById(R.id.et_rv);
        cancel = findViewById(R.id.btn_cancel);
        addBtn = findViewById(R.id.btn_add_the_text);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    firstContainer.setVisibility(View.GONE);
                    secondContainer.setVisibility(View.VISIBLE);
                if (!textFromET.isEmpty()) {
                    todayModel = new TodayModel(textFromET, false);
                    textFromET = "";
                    App.getInstance().getDatabase().todayDao().insert(todayModel);
                } else {
                    Log.e("test1232", "onClick: empty text" );
                }

                adapter.notifyDataSetChanged();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        Log.e("test2333", "onClick: " + s);

    }

    @Override
    public void onDelete(TodayModel model) {
        App.getInstance().getDatabase().todayDao().delete(model);
    }

    public List<TodayModel> getDataFromDataBase() {
        List<TodayModel> list;
        list = App.getInstance().getDatabase().todayDao().getAll();
        return list;
    }

    @Override
    public void addItem(String s) {
        textFromET = s;
    }

}

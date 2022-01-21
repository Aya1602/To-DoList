package com.example.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.adapter.Adapter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodayActivity extends AppCompatActivity implements Listener, EditTextListener {
    private Adapter adapter;
    //    private EditText editText;
    private RecyclerView recyclerView;
    private Button cancel, addBtn;
    private TodayModel todayModel;
    //private String value;

    private String textFromET;

    private int lastPosition = -1;

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

        Date d = new Date(System.currentTimeMillis());
        String date = new SimpleDateFormat("yyyyy.MMMMM.dd").format(d);
        adapter.addItem(App.getInstance().getDatabase().todayDao().getAllToday(date));

        App.getInstance().getDatabase().todayDao().getAllLiveToday(date).observe(this, new Observer<List<TodayModel>>() {
            @Override
            public void onChanged(List<TodayModel> list) {
                adapter.addItem(list);
            }
        });

        adapter.setListener(this);
        adapter.setEditTextListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        imgSettings.setOnClickListener(v -> {
            Intent intent = new Intent(TodayActivity.this, SettingsActivity.class);
            overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
            startActivity(intent);
        });

        addImg.setOnClickListener(v -> {
            Intent intent = new Intent(TodayActivity.this, Backlog.class);
            overridePendingTransition(R.anim.animation, R.anim.slide_out_right);
            startActivity(intent);
        });

//        editText = findViewById(R.id.et_rv);
        cancel = findViewById(R.id.btn_cancel);
        addBtn = findViewById(R.id.btn_add_the_text);

        addBtn.setOnClickListener(v -> {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            firstContainer.setVisibility(View.GONE);
            secondContainer.setVisibility(View.VISIBLE);
            if (!textFromET.isEmpty()) {
                Date d1 = new Date(System.currentTimeMillis());
                String date1 = new SimpleDateFormat("yyyyy.MMMMM.dd").format(d1);
                todayModel = new TodayModel(textFromET, false, date1);
                textFromET = "";
                App.getInstance().getDatabase().todayDao().insert(todayModel);
            } else {
                Log.e("test1232", "onClick: empty text");
            }

            adapter.notifyDataSetChanged();
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

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                if (position < adapter.getList().size()) {
                    if (lastPosition != -1) adapter.getList().get(lastPosition).setDelete(false);
                    lastPosition = position;
                    if (swipeDir == ItemTouchHelper.LEFT) {
                        adapter.getList().get(position).setDelete(true);
                    } else {
                        adapter.getList().get(position).setDelete(false);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.notifyDataSetChanged();
                }
//                App.getInstance().getDatabase().todayDao().delete(adapter.getList().get(position));
//                adapter.notifyDataSetChanged();

            }
        };

        ItemTouchHelper itemTouchHelpers = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelpers.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(String s) {
        Log.e("test2333", "onClick: " + s);

    }

    @Override
    public void ondoneClick(TodayModel todayModel) {
        todayModel.setDone(!todayModel.isDone());
        App.getInstance().getDatabase().todayDao().update(todayModel);

    }

    @Override
    public void onDelete(int position,TodayModel model) {
        if (position == lastPosition) lastPosition = -1;
        App.getInstance().getDatabase().todayDao().delete(model);
    }

    @Override
    public void addToBacklog(int position,TodayModel model) {
        if (position == lastPosition) lastPosition = -1;
        App.getInstance().getDatabase().todayDao().delete(model);
        model.setDelete(false);
        App.getInstance().getAppDataBaseBackLog().todayDao().insert(model);
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

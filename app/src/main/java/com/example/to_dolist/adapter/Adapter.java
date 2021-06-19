package com.example.to_dolist.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.EditTextListener;
import com.example.to_dolist.Listener;
import com.example.to_dolist.R;
import com.example.to_dolist.TodayModel;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Listener listener;
    private EditTextListener editTextListener;
    private List<TodayModel> list = new ArrayList<>();

    private final int ITEM1 = 1;
    private final int ITEM2 = 2;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setEditTextListener(EditTextListener listener) {
        this.editTextListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM1) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false); //добавить второй item
            return new MainVH(view, listener);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item2, parent, false); //добавить второй item
            return new EditTextVH(view, editTextListener);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < list.size())  ((MainVH) holder).onBind(list.get(position)); else
            ((EditTextVH) holder).onBind();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < list.size()) {
            return ITEM1;
        } else return ITEM2;
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public void addItem(List<TodayModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    class MainVH extends RecyclerView.ViewHolder {
        private TextView textEpta;
        private ImageView deleteIV;
        private TodayModel model;

        MainVH(@NonNull View itemView, final Listener listener) {
            super(itemView);
            textEpta = itemView.findViewById(R.id.tv_rv);
            deleteIV = itemView.findViewById(R.id.iv_delete);
            textEpta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(textEpta.getText().toString());
                }
            });
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDelete(model);
                }
            });
        }

        void onBind(TodayModel todayModel) {
            textEpta.setText(todayModel.getTitle());
            model = todayModel;
        }
    }


    class EditTextVH extends RecyclerView.ViewHolder {

        private EditText editText;

        EditTextVH(@NonNull View itemView, final EditTextListener listener) {
            super(itemView);
            editText = itemView.findViewById(R.id.add_new_priority);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    listener.addItem(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        public void onBind(){
            editText.setText("");
        }
    }

}

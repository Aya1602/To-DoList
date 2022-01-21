package com.example.to_dolist.adapter;

import android.graphics.Paint;
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

    private boolean isFromBacklog = false;

    public List<TodayModel> getList() {
        return list;
    }

    public void setFromBacklog(boolean i) {
        isFromBacklog = i;
    }

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
        if (position < list.size()) ((MainVH) holder).onBind(list.get(position));
        else
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

    public void removeNote(int position) {

    }


    public class MainVH extends RecyclerView.ViewHolder {
        private TextView textEpta, deleteTitle, backlogTitle;
        public ImageView deleteIV, backlog;
        private TodayModel model;
        private ImageView checkBtn;


        MainVH(@NonNull View itemView, final Listener listener) {
            super(itemView);
            textEpta = itemView.findViewById(R.id.tv_rv);
            deleteTitle = itemView.findViewById(R.id.title_delete);
            backlogTitle = itemView.findViewById(R.id.title_backlog);
            deleteIV = itemView.findViewById(R.id.iv_delete);
            backlog = itemView.findViewById(R.id.iv_backlog);
            checkBtn = itemView.findViewById(R.id.checkBtn);
            itemView.setOnTouchListener((v, event) -> true
            );
            checkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBtn.setImageResource(R.drawable.ic_checked);
                    listener.ondoneClick(model);
                }
            });
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDelete(getAdapterPosition(), model);
                }
            });
            backlog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.addToBacklog(getAdapterPosition(), model);
                }
            });
        }

        void onBind(TodayModel todayModel) {
            if (todayModel.isDone()) {
                checkBtn.setImageResource(R.drawable.ic_checked);
                textEpta.setPaintFlags(textEpta.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                checkBtn.setImageResource(R.drawable.ic_radio_button_unchecked);
                textEpta.setPaintFlags(0);
            }
            if (isFromBacklog) {
                if (todayModel.isDelete()) {
                    deleteIV.setVisibility(View.VISIBLE);
                    deleteTitle.setVisibility(View.VISIBLE);
                } else {
                    deleteIV.setVisibility(View.GONE);
                    deleteTitle.setVisibility(View.GONE);
                }
            } else {
                if (todayModel.isDelete()) {
                    deleteIV.setVisibility(View.VISIBLE);
                    backlog.setVisibility(View.VISIBLE);
                    backlogTitle.setVisibility(View.VISIBLE);
                    deleteTitle.setVisibility(View.VISIBLE);
                } else {
                    deleteIV.setVisibility(View.GONE);
                    backlog.setVisibility(View.GONE);
                    backlogTitle.setVisibility(View.GONE);
                    deleteTitle.setVisibility(View.GONE);
                }
            }
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

        public void onBind() {
            editText.setText("");
        }
    }

}

package com.example.to_dolist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MainVH> {

    private OnClickAdd onClickAdd;
    private List<String> list = new ArrayList<>();

//    public void addList(Model homeModel) {
//        list.add(homeModel);
//        notifyDataSetChanged();
//    }

    public void setOnClickAdd(OnClickAdd onClickAdd) {
        this.onClickAdd = onClickAdd;
    }

    @NonNull
    @Override
    public MainVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        view.setOnClickListener((View.OnClickListener) onClickAdd);
        return new MainVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainVH holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(String s) {
        this.list.add(s);
        notifyDataSetChanged();
    }


    class MainVH extends RecyclerView.ViewHolder{
        private TextView textEpta;
        private EditText editTextEpta;
        private Button btn  , btnCancel;

        MainVH(@NonNull View itemView) {
            super(itemView);
            textEpta = itemView.findViewById(R.id.tv_rv);
            editTextEpta = itemView.findViewById(R.id.et_rv);
            btn = itemView.findViewById(R.id.btn_add_the_text);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
        }

        void onBind(int position) {
            textEpta.setText(list.get(position));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag", "onClick: Working ");
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag", "onClick: Working ");
                    String et = editTextEpta.getText().toString();
                    onClickAdd.onClick(et);
                }
            });
        }
    }
}


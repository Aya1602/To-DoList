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


    public void setOnClickAdd(OnClickAdd onClickAdd) {
        this.onClickAdd = onClickAdd;
    }

    @NonNull
    @Override
    public MainVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
//        view.setOnClickListener((View.OnClickListener) onClickAdd);
        return new MainVH(view,onClickAdd);
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
        OnClickAdd onClickAdd;



        MainVH(@NonNull View itemView, final OnClickAdd onClickAdd) {
            super(itemView);
            textEpta = itemView.findViewById(R.id.tv_rv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickAdd.onClick(textEpta.getText().toString());
                }
            });
        }

        void onBind(int position) {
            textEpta.setText(list.get(position));

        }
    }
}

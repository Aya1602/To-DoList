package com.example.to_dolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<MainVH> {
    private List<Model> list;

    public void addList(Model homeModel) {
        list.add(homeModel);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MainVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
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
}

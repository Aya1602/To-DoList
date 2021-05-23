package com.example.to_dolist;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MainVH extends RecyclerView.ViewHolder {
    private TextView textEpta;
    private EditText editTextEpta;
    private Button btn  , btnCancel;

    public MainVH(@NonNull View itemView) {
        super(itemView);
        textEpta = itemView.findViewById(R.id.tv_rv);
        editTextEpta = itemView.findViewById(R.id.et_rv);
        btn = itemView.findViewById(R.id.btn_add_the_text);
        btnCancel = itemView.findViewById(R.id.btn_cancel);

    }

    public void onBind(int position) {
            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et = editTextEpta.getText().toString();
                textEpta.setText(et);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}

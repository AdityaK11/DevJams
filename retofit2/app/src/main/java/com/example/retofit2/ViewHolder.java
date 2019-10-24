package com.example.retofit2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView name,id,age,salary;

    public ViewHolder(View v){
        super(v);

        id = v.findViewById(R.id.id);
         name = v.findViewById(R.id.name);
         age = v.findViewById(R.id.age);
         salary = v.findViewById(R.id.salary);

    }

}

package com.example.thread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<NumberSpin> savedNumbers;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, ArrayList<NumberSpin> savedNumbers) {
        this.savedNumbers = savedNumbers;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_number, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.txtNum1.setText(Integer.toString(savedNumbers.get(position).getNumberOne()));
        holder.txtNum2.setText(Integer.toString(savedNumbers.get(position).getNumberTwo()));
        holder.txtNum3.setText(Integer.toString(savedNumbers.get(position).getNumberThree()));
    }

    @Override
    public int getItemCount() {
        return savedNumbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNum1, txtNum2, txtNum3;

        public ViewHolder(View itemView) {
            super(itemView);

            this.txtNum1 = (TextView)itemView.findViewById(R.id.numberSpin1);
            this.txtNum2 = (TextView)itemView.findViewById(R.id.numberSpin2);
            this.txtNum3 = (TextView)itemView.findViewById(R.id.numberSpin3);
        }
    }
}

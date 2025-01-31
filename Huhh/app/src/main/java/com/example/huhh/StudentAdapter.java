package com.example.huhh;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    ArrayList<StudentItem> studentItems;
    //ArrayList<StudentItem> studentItems;
    Context context;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public StudentAdapter(Context context, ArrayList<StudentItem> studentItems) {
        this.studentItems = studentItems;
        this.context = context;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView sectionName;
        TextView status;
        CardView cardView;
        public StudentViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentTextV);
            sectionName = itemView.findViewById(R.id.sectionTextV);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));

        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_item,parent,false);
        return new StudentViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final StudentItem studentItem = studentItems.get(position);
        holder.studentName.setText(studentItem.getName());
        holder.sectionName.setText(studentItem.getSection());
        holder.status.setText(studentItem.getStatus());
        holder.cardView.setCardBackgroundColor(getColor(position));
    }
    private int getColor(int position) {
        final StudentItem studentItem = studentItems.get(position);
        String status = studentItem.getStatus();
        if(status.equals("P"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.present)));
        else if (status.equals("A"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.absent)));
        else if(status.equals("E"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.excuse)));
        else if(status.equals(""))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.white)));
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.normal)));
    }

    @Override
    public int getItemCount() {

        return studentItems.size();
    }
}

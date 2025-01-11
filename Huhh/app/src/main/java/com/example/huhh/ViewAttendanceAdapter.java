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

public class ViewAttendanceAdapter extends RecyclerView.Adapter<ViewAttendanceAdapter.ViewAttendanceHolder> {
    ArrayList<ViewItem> viewItems;
    //ArrayList<StudentItem> studentItems;
    Context context;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public ViewAttendanceAdapter(Context context, ArrayList<ViewItem> viewItems) {
        this.viewItems = viewItems;
        this.context = context;
    }

    public static class ViewAttendanceHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView sectionName;
        TextView status;
        TextView date;
        CardView cardView;
        public ViewAttendanceHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentTextV);
            sectionName = itemView.findViewById(R.id.sectionTextV);
            status = itemView.findViewById(R.id.status);
            date = itemView.findViewById(R.id.dateTextV);
            cardView = itemView.findViewById(R.id.cardview);

            //itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));

        }
    }

    @NonNull
    @Override
    public ViewAttendanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_attendance,parent,false);
        return new ViewAttendanceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAttendanceHolder holder, int position) {
        final ViewItem viewItem = viewItems.get(position);
        holder.studentName.setText(viewItem.getName());
        holder.sectionName.setText(viewItem.getSection());
        holder.status.setText(viewItem.getAttendance());
        holder.date.setText(viewItem.getDate());
        holder.cardView.setCardBackgroundColor(getColor(position));
    }
    private int getColor(int position) {
        final ViewItem viewItem = viewItems.get(position);
        String status = viewItem.getAttendance();
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

        return viewItems.size();
    }
}

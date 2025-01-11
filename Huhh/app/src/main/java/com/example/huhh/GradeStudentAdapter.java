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

public class GradeStudentAdapter extends RecyclerView.Adapter<GradeStudentAdapter.StudentViewHolder> {
    ArrayList<GradeStudentItem> gradeStudentItems;
    //ArrayList<StudentItem> studentItems;
    Context context;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public GradeStudentAdapter(Context context, ArrayList<GradeStudentItem> gradeStudentItems) {
        this.gradeStudentItems = gradeStudentItems;
        this.context = context;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView sectionName;
        TextView grade;
        CardView cardView;
        public StudentViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentTextV);
            sectionName = itemView.findViewById(R.id.sectionTextV);
            grade = itemView.findViewById(R.id.status);
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
        final GradeStudentItem gradeStudentItem = gradeStudentItems.get(position);
        holder.studentName.setText(gradeStudentItem.getName());
        holder.sectionName.setText(gradeStudentItem.getSection());
        holder.grade.setText(gradeStudentItem.getGrade());
        holder.cardView.setCardBackgroundColor(getColor(position));
    }
    private int getColor(int position) {
        final GradeStudentItem studentItem = gradeStudentItems.get(position);
        String status = studentItem.getGrade();
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

        return gradeStudentItems.size();
    }
}

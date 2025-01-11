package com.example.huhh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GradeItemAdapter1 extends RecyclerView.Adapter<GradeItemAdapter1.ClassViewHolder> {
    ArrayList<GradeItem> gradeItems;
    Context context;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public GradeItemAdapter1(Context context, ArrayList<GradeItem> gradeItems) {
        this.gradeItems = gradeItems;
        this.context = context;
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder{
        TextView subjectName;
        TextView sectionName;
        public ClassViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectTextV);
            sectionName = itemView.findViewById(R.id.sectionTextV);

            itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));

        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_subject_item,parent,false);

        return new ClassViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {

        holder.subjectName.setText(gradeItems.get(position).getSubject());
        holder.sectionName.setText(gradeItems.get(position).getSection());
    }

    @Override
    public int getItemCount() {

        return gradeItems.size();
    }
}

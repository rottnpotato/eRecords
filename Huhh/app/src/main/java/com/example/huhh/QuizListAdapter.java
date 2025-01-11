package com.example.huhh;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {
    ArrayList<QuizViewingItem> quizViewingItems;
    //ArrayList<StudentItem> studentItems;
    Context context;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setItemButtonClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public QuizListAdapter(Context context, ArrayList<QuizViewingItem> quizViewingItems) {
        this.quizViewingItems = quizViewingItems;
        this.context = context;
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView sectionName;
        TextView date;
        TextView score;
        CardView cardView;
        ImageButton imageButtons;

        public QuizViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentTextV);
            sectionName = itemView.findViewById(R.id.sectionTextV);
            score = itemView.findViewById(R.id.score);
            date = itemView.findViewById(R.id.dateTextV);
            cardView = itemView.findViewById(R.id.cardview);
            /*imageButtons = itemView.findViewById(R.id.addEntry);
            imageButton=imageButtons;
            //cardView.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
            imageButton.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));*/
        }

    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_quiz,parent,false);
        return new QuizViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        final QuizViewingItem quizViewingItem = quizViewingItems.get(position);
        holder.studentName.setText(quizViewingItem.getName());
        holder.sectionName.setText(quizViewingItem.getSection());
        holder.score.setText(quizViewingItem.getScoreObtained()+"/"+quizViewingItem.getHps());
        holder.date.setText(quizViewingItem.getDate());
        //holder.imageButtons.findViewById(R.id.addEntry);
        holder.cardView.setCardBackgroundColor(getColor(position));
    }
    private int getColor(int position) {
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.present)));
    }

    @Override
    public int getItemCount() {

        return quizViewingItems.size();
    }
}

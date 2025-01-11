package com.example.huhh;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizzAdapter extends RecyclerView.Adapter<QuizzAdapter.QuizViewHolder> {
    ArrayList<QuizzItem> quizzItems;
    //ArrayList<StudentItem> studentItems;
    Context context;
    static ImageButton imageButton;
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
    public QuizzAdapter(Context context, ArrayList<QuizzItem> quizzItems) {
        this.quizzItems = quizzItems;
        this.context = context;
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView sectionName;
        TextView status;
        CardView cardView;
        ImageButton imageButtons;

        public QuizViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentTextV);
            sectionName = itemView.findViewById(R.id.sectionTextV);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.cardview);
            imageButtons = itemView.findViewById(R.id.addEntry);
            imageButton=imageButtons;
            //cardView.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
            imageButton.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));
        }

    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item,parent,false);
        return new QuizViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        final QuizzItem quizzItem = quizzItems.get(position);
        holder.studentName.setText(quizzItem.getName());
        holder.sectionName.setText(quizzItem.getSection());
       // holder.status.setText(studentItem.getStatus());
        holder.imageButtons.findViewById(R.id.addEntry);
        holder.cardView.setCardBackgroundColor(getColor(position));
    }
    private int getColor(int position) {
        final QuizzItem quizzItem = quizzItems.get(position);
        String status = quizzItem.getStatus();
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

        return quizzItems.size();
    }
}

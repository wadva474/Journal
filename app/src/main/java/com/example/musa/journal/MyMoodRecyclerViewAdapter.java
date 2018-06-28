package com.example.musa.journal;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.example.musa.journal.AppDataBase.MoodEntity;


import java.util.List;


public class MyMoodRecyclerViewAdapter extends RecyclerView.Adapter<MyMoodRecyclerViewAdapter.ViewHolder> {

   public static List<MoodEntity>mitems;
    private OnclickLIstener lIstener;
    private Context mcontext;


    public MyMoodRecyclerViewAdapter(Context mcontext, OnclickLIstener lIstener) {
        this.lIstener = lIstener;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout_for_mood, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mMood.setText(mitems.get(position).getmMood());
        holder.mDescription.setText(mitems.get(position).mDescription);
        holder.mDate.setText(mitems.get(position).getmDate().toString());

    }

    public static List<MoodEntity> getMitems() {
        return mitems;
    }

    public  void setMitems(List<MoodEntity> mitems) {
        MyMoodRecyclerViewAdapter.mitems = mitems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mitems!=null){
            return mitems.size();
        }
        else
        return 0;
    }
    public interface OnclickLIstener{
        void OnItemClick(int ID);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private  TextView mMood;
        private  TextView mDescription;
        private TextView mDate;

        private ViewHolder(View itemView) {
            super(itemView);
            mMood= itemView.findViewById(R.id.moodDisplay);
            mDescription= itemView.findViewById(R.id.descriptionDisplay);
            mDate = itemView.findViewById(R.id.dateDisplay);
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
         int position=getAdapterPosition();
         lIstener.OnItemClick(position);

        }
    }
}
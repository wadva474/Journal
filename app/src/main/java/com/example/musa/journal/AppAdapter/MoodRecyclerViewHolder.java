package com.example.musa.journal.AppAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musa.journal.R;

public class MoodRecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mMood;
    public TextView mDescription;
    public ImageView mImage;


    public MoodRecyclerViewHolder(View itemView) {
        super(itemView);
        mMood = itemView.findViewById(R.id.moodView);
        mDescription = itemView.findViewById(R.id.descriptionView);
        mImage = itemView.findViewById(R.id.showimageView);
    }
}

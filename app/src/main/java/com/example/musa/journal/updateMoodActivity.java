package com.example.musa.journal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.musa.journal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class updateMoodActivity extends AppCompatActivity {
    @BindView(R.id.descriptionShowEditText) EditText descriptionshow;
    @BindView(R.id.moodShowEditText) EditText moodshow;
    @BindView(R.id.button) Button UpdateButton;
    String Description;
    String Mood;
    String Id;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mood);
        ButterKnife.bind(this);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            firebaseDatabase = database;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getReference("moods").child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
        Intent intent=getIntent();
        Id =intent.getStringExtra("position");
        if (intent.getExtras()!=null)
        UpdateUi();
    }
    public void UpdateUi(){
        databaseReference.child(Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                descriptionshow.setText(String.valueOf(dataSnapshot.child("description").getValue()));
                moodshow.setText(String.valueOf(dataSnapshot.child("mood").getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @OnClick(R.id.button)
    public void updateMood(View v){
        Map updateMode=new HashMap();
        updateMode.put("description",descriptionshow.getText().toString().trim());
        updateMode.put("mood",moodshow.getText().toString().trim());
     databaseReference.child(Id).updateChildren(updateMode);
     finish();

    }
}

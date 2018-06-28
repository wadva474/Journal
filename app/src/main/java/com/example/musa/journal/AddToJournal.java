package com.example.musa.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.musa.journal.AppDataBase.AppDatabase;
import com.example.musa.journal.AppDataBase.MoodEntity;
import com.example.musa.journal.AppExecutor.AppExecutor;

import java.util.Date;

public class AddToJournal extends AppCompatActivity {
    Button AddTask;
    android.support.design.widget.TextInputEditText moodEditText,DescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_journal);
        AddTask = findViewById(R.id.button);
        moodEditText = findViewById(R.id.mood);
        DescriptionEditText = findViewById(R.id.description);
        if (getIntent()!=null && getIntent().getExtras() == null) {
            AddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppExecutor.getsInstance().getDataIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getsInstance(getApplicationContext()).moodDao().insertmood(new MoodEntity(
                                    moodEditText.getText().toString(), DescriptionEditText.getText().toString(), new Date()

                            ));
                                finish();
                        }

                    });

                }
            });

        }
    }

}

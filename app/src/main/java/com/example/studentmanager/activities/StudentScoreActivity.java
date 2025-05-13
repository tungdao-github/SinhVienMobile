package com.example.studentmanager.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.Helper.DatabaseHelper;
import com.example.studentmanager.R;
import com.example.studentmanager.adapters.ScoreAdapter;
import com.example.studentmanager.models.Score;

import java.util.List;

//
public class StudentScoreActivity extends AppCompatActivity {

    ListView lvScores;
    ScoreAdapter adapter;
    DatabaseHelper db;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score);

        lvScores = findViewById(R.id.lvScores);
        db = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");
        List<Score> scores = db.getScoresByUsername(username);
        adapter = new ScoreAdapter(this, scores);
        lvScores.setAdapter(adapter);
    }
}


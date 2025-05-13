package com.example.studentmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.Helper.DatabaseHelper;
import com.example.studentmanager.R;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    TextView tvInfo, tvScores, tvRanking;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        tvInfo = findViewById(R.id.infoTextView);
        tvScores = findViewById(R.id.scoresTextView);
        tvRanking = findViewById(R.id.rankingTextView);

        db = new DatabaseHelper(this);

        String username = getIntent().getStringExtra("username");

        // Thông tin sinh viên
        String info = db.getStudentByUsername(username);
        tvInfo.setText("🔹 Thông tin:\n" + info);

        // Điểm
//        List<String> scores = db.getScoresByUsername(username);
//        StringBuilder scoreText = new StringBuilder("🔹 Điểm:\n");
//        for (String s : scores) {
//            scoreText.append(s).append("\n");
//        }
//        tvScores.setText(scoreText.toString());

        // Bảng xếp hạng
        List<String> ranking = db.getStudentRanking();
        StringBuilder rankingText = new StringBuilder("🔹 Bảng xếp hạng:\n");
        for (String s : ranking) {
            rankingText.append(s).append("\n");
        }
        tvRanking.setText(rankingText.toString());
        Button btnViewScores = findViewById(R.id.btnViewScores);
        Button btnViewSchedule = findViewById(R.id.btnViewSchedule);

        btnViewScores.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentScoreActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        btnViewSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentScheduleActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

    }
}

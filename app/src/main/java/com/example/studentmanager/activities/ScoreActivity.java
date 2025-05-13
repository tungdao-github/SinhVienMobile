package com.example.studentmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.R;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    ListView lvScore;
    ArrayList<String> scoreList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lvScore = findViewById(R.id.lvScore);

        // Dữ liệu mẫu
        scoreList = new ArrayList<>();
        scoreList.add("Lập trình Java - Điểm: 9.5");
        scoreList.add("Cơ sở dữ liệu - Điểm: 8.0");
        scoreList.add("Toán rời rạc - Điểm: 7.5");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
        lvScore.setAdapter(adapter);
    }
}

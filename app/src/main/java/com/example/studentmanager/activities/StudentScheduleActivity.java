package com.example.studentmanager.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.Helper.DatabaseHelper;
import com.example.studentmanager.R;
import com.example.studentmanager.adapters.ScheduleAdapter;
import com.example.studentmanager.adapters.ScoreAdapter;
import com.example.studentmanager.models.Schedule;
import com.example.studentmanager.models.Score;

import java.util.List;

public class StudentScheduleActivity extends AppCompatActivity {

    ListView lvSchedules;
    ScheduleAdapter adapter;
    DatabaseHelper db;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        lvSchedules = findViewById(R.id.lvSchedule);
        db = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");
        List<Schedule> schedules = db.getSchedulesByUsername(username);
        adapter = new ScheduleAdapter(this, schedules);
        lvSchedules.setAdapter(adapter);
    }
}

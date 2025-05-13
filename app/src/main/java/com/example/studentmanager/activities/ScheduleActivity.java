package com.example.studentmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.R;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    ListView lvSchedule;
    ArrayList<String> scheduleList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        lvSchedule = findViewById(R.id.lvSchedule);

        // Dữ liệu mẫu
        scheduleList = new ArrayList<>();
        scheduleList.add("Lập trình Java\nThứ 2 - Tiết 1-3 - Phòng B202");
        scheduleList.add("Cơ sở dữ liệu\nThứ 3 - Tiết 4-6 - Phòng C101");
        scheduleList.add("Mạng máy tính\nThứ 5 - Tiết 7-9 - Phòng A305");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scheduleList);
        lvSchedule.setAdapter(adapter);
    }
}

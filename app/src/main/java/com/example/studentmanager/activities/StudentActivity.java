package com.example.studentmanager.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.Helper.DatabaseHelper;
import com.example.studentmanager.R;

public class StudentActivity extends AppCompatActivity {

    TextView tvInfo;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.studentmanager.R.layout.activity_student);

        tvInfo = findViewById(R.id.infoTextView);
        db = new DatabaseHelper(this);

        String username = getIntent().getStringExtra("username");
        String info = db.getStudentByUsername(username);
        tvInfo.setText(info);
    }
}

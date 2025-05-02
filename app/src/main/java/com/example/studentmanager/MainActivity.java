package com.example.studentmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentmanager.adapters.StudentAdapter;
import com.example.studentmanager.models.Student;
import com.example.studentmanager.activities.AddStudentActivity;
import com.example.studentmanager.activities.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD = 1;
    private ArrayList<Student> students;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Kiểm tra đã đăng nhập chưa
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // ❌ Chưa đăng nhập, chuyển sang LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // ✅ Nếu đã đăng nhập rồi thì hiển thị danh sách sinh viên
        setContentView(R.layout.activity_main);

        students = new ArrayList<>();
        adapter = new StudentAdapter(this, students);

        ListView listView = findViewById(R.id.studentListView);
        listView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAddStudent);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK) {
            String name = data.getStringExtra("name");
            int age = data.getIntExtra("age", 0);
            String id = data.getStringExtra("id");
            students.add(new Student(name, age, id));
            adapter.notifyDataSetChanged();
        }
    }


}

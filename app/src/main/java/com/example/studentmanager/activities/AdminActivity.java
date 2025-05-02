package com.example.studentmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanager.Helper.DatabaseHelper;
import com.example.studentmanager.R;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    EditText name, age, username;
    Button add;
    ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.studentmanager.R.layout.activity_admin);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        username = findViewById(R.id.username);
        add = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        refreshList();

        add.setOnClickListener(v -> {
            db.addStudent(name.getText().toString(),
                    Integer.parseInt(age.getText().toString()),
                    username.getText().toString());
            Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
            refreshList();
        });
    }

    private void refreshList() {
        List<String> list = db.getAllStudents();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}

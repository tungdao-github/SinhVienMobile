package com.example.studentmanager.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentmanager.Helper.DatabaseHelper;
import com.example.studentmanager.R;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    EditText name, age, username, subject, score;
    Button btnAddStudent, btnAddScore;
    ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        username = findViewById(R.id.username);
        subject = findViewById(R.id.subject);
        score = findViewById(R.id.score);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddScore = findViewById(R.id.btnAddScore);
        listView = findViewById(R.id.listView);

        db = new DatabaseHelper(this);

        refreshList();

        btnAddStudent.setOnClickListener(v -> {
            db.addStudent(name.getText().toString(),
                    Integer.parseInt(age.getText().toString()),
                    username.getText().toString());
            Toast.makeText(this, "✅ Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
            refreshList();
        });

        btnAddScore.setOnClickListener(v -> {
            db.addScore(username.getText().toString(),
                    subject.getText().toString(),
                    Float.parseFloat(score.getText().toString()));
            Toast.makeText(this, "✅ Đã thêm điểm", Toast.LENGTH_SHORT).show();
            refreshList();
        });
        Button btnAddScore = findViewById(R.id.btnAddScore);
        Button btnAddSchedule = findViewById(R.id.btnAddSchedule);

        btnAddScore.setOnClickListener(v -> {
            showAddScoreDialog();  // gọi dialog nhập điểm
        });

        btnAddSchedule.setOnClickListener(v -> {
            showAddScheduleDialog();  // gọi dialog nhập TKB
        });
    }

    private void refreshList() {
        List<String> list = db.getStudentRanking(); // show bảng xếp hạng
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

//    private void showAddScoreDialog() {
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_score, null);
//        EditText edtEmail = view.findViewById(R.id.edtEmail);
//        EditText edtSubject = view.findViewById(R.id.edtSubject);
//        EditText edtScore = view.findViewById(R.id.edtScore);
//
//        new AlertDialog.Builder(this)
//                .setTitle("Thêm điểm")
//                .setView(view)
//                .setPositiveButton("Lưu", (dialog, which) -> {
//                    String email = edtEmail.getText().toString();
//                    String subject = edtSubject.getText().toString();
//                    double score = Double.parseDouble(edtScore.getText().toString());
//                    db.insertScore(email, subject, score);
//                    Toast.makeText(this, "Đã thêm điểm", Toast.LENGTH_SHORT).show();
//                })
//                .setNegativeButton("Hủy", null)
//                .show();
//    }
private void showAddScoreDialog() {
    Dialog dialog = new Dialog(this);
    dialog.setContentView(R.layout.dialog_add_score);

    EditText edtUsername = dialog.findViewById(R.id.etUsername);
    EditText edtSubject = dialog.findViewById(R.id.edtSubject);
    EditText edtScore = dialog.findViewById(R.id.edtScore);
    Button btnSave = dialog.findViewById(R.id.btnSave);

    btnSave.setOnClickListener(v -> {
        String username = edtUsername.getText().toString();
        String subject = edtSubject.getText().toString();
        double score = Double.parseDouble(edtScore.getText().toString());

        DatabaseHelper db = new DatabaseHelper(this);
        db.insertScore(username, subject, score);
        Toast.makeText(this, "Thêm điểm thành công", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    });

    dialog.show();
}

    private void showAddScheduleDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_schedule);

        EditText edtUsername = dialog.findViewById(R.id.etUsername);
        EditText edtSubject = dialog.findViewById(R.id.edtSubject);
        EditText edtTime = dialog.findViewById(R.id.edtTime);
        EditText edtRoom = dialog.findViewById(R.id.edtRoom);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String subject = edtSubject.getText().toString();
            String time = edtTime.getText().toString();
            String room = edtRoom.getText().toString();

            DatabaseHelper db = new DatabaseHelper(this);
            db.insertSchedule(username, subject, time, room);
            Toast.makeText(this, "Thêm TKB thành công", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

}

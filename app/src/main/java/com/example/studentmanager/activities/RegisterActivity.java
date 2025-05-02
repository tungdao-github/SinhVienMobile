package com.example.studentmanager.activities;

//package com.example.studentapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studentmanager.R;
import com.example.studentmanager.Helper.DBHelper;
import com.example.studentmanager.Helper.DBHelper;
import com.example.studentmanager.R;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Spinner spRole;
    Button btnRegister;
    com.example.studentmanager.Helper.DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.studentmanager.R.layout.activity_register);

        db = new com.example.studentmanager.Helper.DBHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        spRole     = findViewById(R.id.spRole);
        btnRegister = findViewById(R.id.btnRegister);

        spRole.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"student", "admin"}));

        btnRegister.setOnClickListener(v -> {
            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();
            String role = spRole.getSelectedItem().toString();

            boolean ok = db.register(user, pass, role);
            if (ok) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

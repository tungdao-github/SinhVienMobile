package com.example.studentmanager.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "StudentApp.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT, role TEXT)");
        db.execSQL("CREATE TABLE students(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, username TEXT)");
        db.execSQL("CREATE TABLE subjects(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, credit INTEGER)");
        db.execSQL("CREATE TABLE scores(id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, subject_id INTEGER, score REAL)");


        db.execSQL("INSERT INTO users VALUES('admin', 'admin123', 'admin')");
        db.execSQL("INSERT INTO users VALUES('sv01', '123456', 'user')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    public boolean register(String username, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("role", role);
        long result = db.insert("users", null, cv);
        return result != -1;
    }

    public String login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT role FROM users WHERE username=? AND password=?", new String[]{username, password});
        if (c.moveToFirst()) {
            return c.getString(0); // role
        }
        return null;
    }

    // Các hàm quản lý sinh viên: thêm, sửa, xóa, lấy danh sách...
}

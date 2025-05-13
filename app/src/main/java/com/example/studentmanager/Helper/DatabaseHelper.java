package com.example.studentmanager.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studentmanager.models.Schedule;
import com.example.studentmanager.models.Score;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "StudentApp.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT, role TEXT)");
        db.execSQL("CREATE TABLE students(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, username TEXT)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS score(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, subject TEXT, score REAL)");
//        db.execSQL("CREATE TABLE IF NOT EXISTS schedule(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, subject TEXT, day TEXT, time TEXT, room TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Score (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "subject TEXT, " +
                "score REAL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Schedule (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "subject TEXT, " +
                "time TEXT, " +
                "room TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Score (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "subject TEXT, " +
                "score REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
    public void insertScore(String username, String subject, double score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("subject", subject);
        values.put("score", score);
        db.insert("Score", null, values);
        db.close();
    }

    public void insertSchedule(String username, String subject, String time, String room) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("subject", subject);
        values.put("time", time);
        values.put("room", room);
        db.insert("Schedule", null, values);
        db.close();
    }
    // Lấy điểm theo email

    public void insertUser(String user, String pass, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user);
        cv.put("password", pass);
        cv.put("role", role);
        db.insert("users", null, cv);
    }

    public String checkLogin(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT role FROM users WHERE username=? AND password=?", new String[]{user, pass});
        if (c.moveToFirst()) {
            return c.getString(0); // role
        }
        return "";
    }

    public void addStudent(String name, int age, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        cv.put("username", username);
        db.insert("students", null, cv);
    }


    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name, age FROM students", null);
        while (c.moveToNext()) {
            list.add(c.getString(0) + " - Tuổi: " + c.getInt(1));
        }
        return list;
    }

    public List<Score> getScoresByUsername(String username) {
        List<Score> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT subject, score FROM Score WHERE username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            do {
                String subject = cursor.getString(0);
                double score = cursor.getDouble(1);
                list.add(new Score(subject, score));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public List<Schedule> getSchedulesByUsername(String username) {
        List<Schedule> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT subject, time, room FROM Schedule WHERE username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            do {
                String subject = cursor.getString(0);
                String time = cursor.getString(1);
                String room = cursor.getString(2);
                list.add(new Schedule(subject, time, room));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Thêm môn học
    public void addSubject(String name, int credit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("credit", credit);
        db.insert("subjects", null, cv);
    }

    // Lấy danh sách môn học
    public List<String> getAllSubjects() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, credit FROM subjects", null);
        while (c.moveToNext()) {
            list.add("Mã: " + c.getInt(0) + " - " + c.getString(1) + " (" + c.getInt(2) + " tín chỉ)");
        }
        return list;
    }

    // Sửa môn học
    public boolean updateSubject(int id, String name, int credit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("credit", credit);
        int rows = db.update("subjects", cv, "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    // Xóa môn học
    public boolean deleteSubject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("subjects", "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    // Thêm điểm
    public void addScore(int studentId, int subjectId, float score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("student_id", studentId);
        cv.put("subject_id", subjectId);
        cv.put("score", score);
        db.insert("scores", null, cv);
    }

    // Lấy điểm sinh viên theo studentId
    public List<String> getScoresByStudent(int studentId) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT s.name, sc.score FROM subjects s JOIN scores sc ON s.id = sc.subject_id WHERE sc.student_id=?",
                new String[]{String.valueOf(studentId)});
        while (c.moveToNext()) {
            list.add("Môn: " + c.getString(0) + " - Điểm: " + c.getFloat(1));
        }
        return list;
    }

    // Tính điểm trung bình sinh viên
    public float getAverageScore(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT AVG(score) FROM scores WHERE student_id=?", new String[]{String.valueOf(studentId)});
        if (c.moveToFirst()) {
            return c.getFloat(0);
        }
        return 0f;
    }
    // Lấy thông tin sinh viên theo username
    public String getStudentByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name, age FROM students WHERE username=?", new String[]{username});
        if (c.moveToFirst()) {
            return c.getString(0) + " - Tuổi: " + c.getInt(1);
        }
        return "Không có thông tin.";
    }

    // Lấy danh sách điểm theo username
//    public List<String> getScoresByUsername(String username) {
//        List<String> list = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor studentCursor = db.rawQuery("SELECT id FROM students WHERE username=?", new String[]{username});
//        if (studentCursor.moveToFirst()) {
//            int studentId = studentCursor.getInt(0);
//            Cursor c = db.rawQuery("SELECT s.name, sc.score FROM subjects s JOIN scores sc ON s.id = sc.subject_id WHERE sc.student_id=?",
//                    new String[]{String.valueOf(studentId)});
//            while (c.moveToNext()) {
//                list.add("Môn: " + c.getString(0) + " - Điểm: " + c.getFloat(1));
//            }
//        }
//        return list;
//    }

    // Lấy bảng xếp hạng sinh viên theo điểm trung bình
    public List<String> getStudentRanking() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT st.name, AVG(sc.score) as avg_score " +
                        "FROM students st JOIN scores sc ON st.id = sc.student_id " +
                        "GROUP BY st.id ORDER BY avg_score DESC", null);

        int rank = 1;
        while (c.moveToNext()) {
            list.add("Hạng " + (rank++) + ": " + c.getString(0) + " - TB: " + c.getFloat(1));
        }

        return list;
    }

    // Thêm điểm theo username và tên môn học
    public void addScore(String username, String subject, float score) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Lấy student_id từ username
        Cursor studentCursor = db.rawQuery("SELECT id FROM students WHERE username=?", new String[]{username});
        if (!studentCursor.moveToFirst()) return;
        int studentId = studentCursor.getInt(0);

        // Lấy subject_id từ tên môn học
        Cursor subjectCursor = db.rawQuery("SELECT id FROM subjects WHERE name=?", new String[]{subject});
        if (!subjectCursor.moveToFirst()) return;
        int subjectId = subjectCursor.getInt(0);

        // Thêm điểm
        ContentValues cv = new ContentValues();
        cv.put("student_id", studentId);
        cv.put("subject_id", subjectId);
        cv.put("score", score);
        db.insert("scores", null, cv);
    }

    public List<String> getTopStudents() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT st.name, AVG(sc.score) as avg_score " +
                        "FROM students st JOIN scores sc ON st.id = sc.student_id " +
                        "GROUP BY st.id ORDER BY avg_score DESC LIMIT 5", null);

        while (c.moveToNext()) {
            list.add(c.getString(0) + " - TB: " + c.getFloat(1));
        }

        return list;
    }
}

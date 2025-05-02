package com.example.studentmanager.models;

public class Student {
    private String name;
    private int age;
    private String studentId;
    private String username;
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getStudentId() { return studentId; }
    public String getUsername() {return username;}
}

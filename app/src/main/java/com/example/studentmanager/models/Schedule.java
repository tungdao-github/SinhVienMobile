package com.example.studentmanager.models;

public class Schedule {
    private String subject;
    private String time;
    private String room;

    public Schedule(String subject, String time, String room) {
        this.subject = subject;
        this.time = time;
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }
}

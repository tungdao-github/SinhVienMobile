package com.example.studentmanager.models;
public class Score {
    private String subject;
    private double score;

    public Score(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }
}


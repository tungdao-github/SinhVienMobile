//package com.example.studentmanager.Utils;
//
//import com.example.studentmanager.models.Score;
//
//import java.util.List;
//
//public class GPAUtils {
//    public static double calculateGPA(List<Score> scores) {
//        int totalCredits = 0;
//        double totalWeighted = 0.0;
//
//        for (Score score : scores) {
//            totalCredits += score.getCredit();
//            totalWeighted += score.getScore() * score.getCredit();
//        }
//
//        return totalCredits == 0 ? 0.0 : totalWeighted / totalCredits;
//    }
//}

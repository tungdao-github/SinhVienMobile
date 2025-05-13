//package com.example.studentmanager.Fragment;
//
//import android.app.AlertDialog;
//import android.os.Bundle;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.*;
//
//import com.example.studentmanager.R;
//import com.example.studentmanager.Utils.GPAUtils;
////import com.example.studentmanager.adapters.ScoreAdapter;
//import com.example.studentmanager.models.Score;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ScoreFragment extends Fragment {
//
//    private List<Score> scoreList;
//    private ScoreAdapter adapter;
//    private TextView txtGPA;
//
//    public ScoreFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_score, container, false);
//
//        scoreList = new ArrayList<>();
//        adapter = new ScoreAdapter(scoreList);
//
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewScores);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//
//        Button btnAddScore = view.findViewById(R.id.btnAddScore);
//        txtGPA = view.findViewById(R.id.txtGPA);
//
//        btnAddScore.setOnClickListener(v -> showAddScoreDialog());
//
//        updateGPA();
//        return view;
//    }
//
//    private void showAddScoreDialog() {
//        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_score, null);
//        EditText editSubject = dialogView.findViewById(R.id.editSubject);
//        EditText editCredit = dialogView.findViewById(R.id.editCredit);
//        EditText editScore = dialogView.findViewById(R.id.editScore);
//
//        new AlertDialog.Builder(getContext())
//                .setTitle("Thêm điểm")
//                .setView(dialogView)
//                .setPositiveButton("Thêm", (dialog, which) -> {
//                    String subject = editSubject.getText().toString();
//                    int credit = Integer.parseInt(editCredit.getText().toString());
//                    double scoreValue = Double.parseDouble(editScore.getText().toString());
//
//                    scoreList.add(new Score(subject, credit, scoreValue));
//                    adapter.notifyDataSetChanged();
//                    updateGPA();
//                })
//                .setNegativeButton("Hủy", null)
//                .show();
//    }
//
//    private void updateGPA() {
//        double gpa = GPAUtils.calculateGPA(scoreList);
//        txtGPA.setText(String.format("GPA hiện tại: %.2f", gpa));
//    }
//}
//

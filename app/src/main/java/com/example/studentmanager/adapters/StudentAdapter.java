package com.example.studentmanager.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.example.studentmanager.models.Student;
import com.example.studentmanager.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_student, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.tvName)).setText(student.getName());
        ((TextView) convertView.findViewById(R.id.tvAge)).setText("Tuổi: " + student.getAge());
        ((TextView) convertView.findViewById(R.id.tvId)).setText("MSSV: " + student.getStudentId());
        return convertView;
    }
}

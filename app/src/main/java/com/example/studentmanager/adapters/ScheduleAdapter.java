package com.example.studentmanager.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.studentmanager.R;
import com.example.studentmanager.models.Schedule;

import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {
    public ScheduleAdapter(Context context, List<Schedule> schedules) {
        super(context, 0, schedules);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Schedule schedule = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_schedule, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.tvSubject)).setText(schedule.getSubject());
        ((TextView) convertView.findViewById(R.id.tvTime)).setText(schedule.getTime());
        ((TextView) convertView.findViewById(R.id.tvRoom)).setText(schedule.getRoom());
        return convertView;
    }
}

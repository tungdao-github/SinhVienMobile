package com.example.studentmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanager.R;
import com.example.studentmanager.models.Score;

import java.util.List;
//
public class ScoreAdapter extends ArrayAdapter<Score> {
    public ScoreAdapter(Context context, List<Score> scores) {
        super(context, 0, scores);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Score score = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_score, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.tvSubject)).setText(score.getSubject());
        ((TextView) convertView.findViewById(R.id.tvScore)).setText(String.valueOf(score.getScore()));
        return convertView;
    }
}

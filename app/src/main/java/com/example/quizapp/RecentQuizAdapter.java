package com.example.quizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecentQuizAdapter extends ArrayAdapter<RecentQuiz> {
    private Context mContext;
    public RecentQuizAdapter(Activity context, ArrayList<RecentQuiz> recentQuizzes) {
        super(context, 0, recentQuizzes);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final RecentQuiz currentRecentQuiz = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.quiz_title);
        assert currentRecentQuiz != null;
        title.setText(currentRecentQuiz.getmQuizTitle());

        TextView description = (TextView) listItemView.findViewById(R.id.description);
        description.setText(currentRecentQuiz.getmQuizDescription());

        final Button recentStartButton = (Button) listItemView.findViewById(R.id.recent_start);

        final Activity host = (Activity) recentStartButton.getContext();


        recentStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentRecentQuiz.getmQuizTitle().equals("Astronomy Quiz")) {
                    AstronomyQuizFirstActivity.questionSubject = "Astronomy";
                    QuizIntroActivity.title = host.getString(R.string.astronomy_quiz);
                    QuizIntroActivity.description = host.getString(R.string.astronomy_quiz_intro_description);
                    Intent i = new Intent(host, QuizIntroActivity.class);
                    host.startActivity(i);
                    StaticVariables.subject = "Astronomy Quiz";
                } else if(currentRecentQuiz.getmQuizTitle().equals("Geography Quiz")) {
                    AstronomyQuizFirstActivity.questionSubject = "Geography";
                    QuizIntroActivity.title = host.getString(R.string.geography_quiz);
                    QuizIntroActivity.description = host.getString(R.string.geography_quiz_intro_description);
                    Intent i = new Intent(host, QuizIntroActivity.class);
                    host.startActivity(i);
                    StaticVariables.subject = "Geography Quiz";
                } else if(currentRecentQuiz.getmQuizTitle().equals("Zoology Quiz")) {
                    AstronomyQuizFirstActivity.questionSubject = "Zoology";
                    QuizIntroActivity.title = host.getString(R.string.zoology_quiz);
                    QuizIntroActivity.description = host.getString(R.string.zoology_quiz_intro_description);
                    Intent i = new Intent(host, QuizIntroActivity.class);
                    host.startActivity(i);
                    StaticVariables.subject = "Zoology Quiz";
                }

            }
        });

        return listItemView;
    }
}

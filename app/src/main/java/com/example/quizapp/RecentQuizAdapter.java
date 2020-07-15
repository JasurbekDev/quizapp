package com.example.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecentQuizAdapter extends ArrayAdapter<RecentQuiz> {
    RecentQuizAdapter(Activity context, ArrayList<RecentQuiz> recentQuizzes) {
        super(context, 0, recentQuizzes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final RecentQuiz currentRecentQuiz = getItem(position);
        TextView title = listItemView.findViewById(R.id.quiz_title);
        assert currentRecentQuiz != null;
        title.setText(currentRecentQuiz.getmQuizTitle());

        TextView description = listItemView.findViewById(R.id.description);
        description.setText(currentRecentQuiz.getmQuizDescription());

        final Button recentStartButton = listItemView.findViewById(R.id.recent_start);

        final Activity host = (Activity) recentStartButton.getContext();

        recentStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (currentRecentQuiz.getmQuizTitle()) {
                    case "Astronomy Quiz": {
                        QuestionsLayoutActivity.questionSubject = host.getString(R.string.astronomy);
                        QuizIntroActivity.title = host.getString(R.string.astronomy_quiz);
                        QuizIntroActivity.description = host.getString(R.string.astronomy_quiz_intro_description);
                        Intent i = new Intent(host, QuizIntroActivity.class);
                        host.startActivity(i);
                        StaticVariables.subject = host.getString(R.string.astronomy_quiz);
                        break;
                    }
                    case "Geography Quiz": {
                        QuestionsLayoutActivity.questionSubject = host.getString(R.string.geography);
                        QuizIntroActivity.title = host.getString(R.string.geography_quiz);
                        QuizIntroActivity.description = host.getString(R.string.geography_quiz_intro_description);
                        Intent i = new Intent(host, QuizIntroActivity.class);
                        host.startActivity(i);
                        StaticVariables.subject = host.getString(R.string.geography_quiz);
                        break;
                    }
                    case "Zoology Quiz": {
                        QuestionsLayoutActivity.questionSubject = host.getString(R.string.zoology);
                        QuizIntroActivity.title = host.getString(R.string.zoology_quiz);
                        QuizIntroActivity.description = host.getString(R.string.zoology_quiz_intro_description);
                        Intent i = new Intent(host, QuizIntroActivity.class);
                        host.startActivity(i);
                        StaticVariables.subject = host.getString(R.string.zoology_quiz);
                        break;
                    }
                }
            }
        });

        return listItemView;
    }
}

package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView recentImage;
    ListView listView;
//    Button astronomyButton;
    TextView astronomyCard;
    TextView geographyCard;
    TextView zoologyCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        astronomyCard = findViewById(R.id.astronomy_quiz);
        geographyCard = findViewById(R.id.geography_quiz);
        zoologyCard = findViewById(R.id.zoology_quiz);
        recentImage = findViewById(R.id.recent_image);
        listView = findViewById(R.id.list);
        listView.setVisibility(View.GONE);
//        astronomyButton = findViewById(R.id.astronomy_button);

        if(StaticVariables.isFirst) {
            StaticVariables.allQuizzes.add(StaticVariables.astronomy);
            StaticVariables.allQuizzes.add(StaticVariables.geography);
            StaticVariables.allQuizzes.add(StaticVariables.zoology);
            StaticVariables.isFirst = false;
        }



        astronomyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAstronomyClick();
            }
        });

        geographyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGeographyClick();
            }
        });

        zoologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onZoologyClick();
            }
        });

        if(StaticVariables.isList) {
            recentImage.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            if(StaticVariables.recentQuizzes.size() == 0) {
                for (int i = 0; i < StaticVariables.allQuizzes.size(); i++) {
                    if(StaticVariables.subject.toLowerCase().equals(StaticVariables.allQuizzes.get(i).getmQuizTitle().toLowerCase())) {
                        StaticVariables.recentQuizzes.add(StaticVariables.allQuizzes.get(i));
                        break;
                    }
                }
            } else {
                ArrayList<RecentQuiz> newList = new ArrayList<>();
                boolean isExist = false;
                int index = 0;
                for (int i = 0; i < StaticVariables.recentQuizzes.size(); i++) {
                    if(StaticVariables.subject.toLowerCase().equals(StaticVariables.recentQuizzes.get(i).getmQuizTitle().toLowerCase())) {
                        isExist = true;
                        index = i;
                        break;
                    }
                }
                if(isExist) {
                    newList.add(StaticVariables.recentQuizzes.get(index));
                    StaticVariables.recentQuizzes.remove(index);
                    for (int i = 0; i < StaticVariables.recentQuizzes.size(); i++) {
                        newList.add(StaticVariables.recentQuizzes.get(i));
                    }
                    StaticVariables.recentQuizzes = newList;
                } else {
                    for (int i = 0; i < StaticVariables.allQuizzes.size(); i++) {
                        if(StaticVariables.subject.toLowerCase().equals(StaticVariables.allQuizzes.get(i).getmQuizTitle().toLowerCase())) {
                            newList.add(StaticVariables.allQuizzes.get(i));
                            break;
                        }
                    }
                    for (RecentQuiz recentQuizz : StaticVariables.recentQuizzes) {
                        newList.add(recentQuizz);
                    }
                    StaticVariables.recentQuizzes = newList;
                }
            }

            RecentQuizAdapter recentQuizAdapter = new RecentQuizAdapter(this, StaticVariables.recentQuizzes);
            listView.setAdapter(recentQuizAdapter);

        }
    }

    public void onAstronomyClick() {
        AstronomyQuizFirstActivity.questionSubject = "Astronomy";
        QuizIntroActivity.title = getString(R.string.astronomy_quiz);
        QuizIntroActivity.description = getString(R.string.astronomy_quiz_intro_description);
        Intent i = new Intent(MainActivity.this, QuizIntroActivity.class);
        startActivity(i);
        StaticVariables.subject = "Astronomy Quiz";
    }

    public void onGeographyClick() {
        AstronomyQuizFirstActivity.questionSubject = "Geography";
        QuizIntroActivity.title = getString(R.string.geography_quiz);
        QuizIntroActivity.description = getString(R.string.geography_quiz_intro_description);
        Intent i = new Intent(MainActivity.this, QuizIntroActivity.class);
        startActivity(i);
        StaticVariables.subject = "Geography Quiz";
    }

    private void onZoologyClick() {
        AstronomyQuizFirstActivity.questionSubject = "Zoology";
        QuizIntroActivity.title = getString(R.string.zoology_quiz);
        QuizIntroActivity.description = getString(R.string.zoology_quiz_intro_description);
        Intent i = new Intent(MainActivity.this, QuizIntroActivity.class);
        startActivity(i);
        StaticVariables.subject = "Zoology Quiz";
    }
}

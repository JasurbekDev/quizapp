package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView recentImage;
    ListView listView;
    TextView astronomyCard;
    TextView geographyCard;
    TextView zoologyCard;
    EditText editText;
    LinearLayout editNameLayout;
    TextView welcomeText;
    Button acceptNameButton;
    static String userName;
    static boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittext);
        editNameLayout = findViewById(R.id.edit_name_layout);
        acceptNameButton = findViewById(R.id.accept_name_button);
        astronomyCard = findViewById(R.id.astronomy_quiz);
        welcomeText = findViewById(R.id.welcome_text);
        geographyCard = findViewById(R.id.geography_quiz);
        zoologyCard = findViewById(R.id.zoology_quiz);
        recentImage = findViewById(R.id.recent_image);
        listView = findViewById(R.id.list);
        listView.setVisibility(View.GONE);

        if (isFirst) {
            welcomeText.setVisibility(View.INVISIBLE);
            isFirst = false;
        } else {
            welcomeText.setText(getString(R.string.welcome_text, userName));
            welcomeText.setVisibility(View.VISIBLE);
            editNameLayout.setVisibility(View.INVISIBLE);
        }

        acceptNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = editText.getText().toString();
                welcomeText.setText(getString(R.string.welcome_text, userName));
                editNameLayout.setVisibility(View.INVISIBLE);
                welcomeText.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

        welcomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                editNameLayout.setVisibility(View.VISIBLE);
                welcomeText.setVisibility(View.INVISIBLE);
            }
        });

        if (StaticVariables.isFirst) {
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

        if (StaticVariables.isList) {
            recentImage.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            if (StaticVariables.recentQuizzes.size() == 0) {
                for (int i = 0; i < StaticVariables.allQuizzes.size(); i++) {
                    if (StaticVariables.subject.toLowerCase().equals(StaticVariables.allQuizzes.get(i).getmQuizTitle().toLowerCase())) {
                        StaticVariables.recentQuizzes.add(StaticVariables.allQuizzes.get(i));
                        break;
                    }
                }
            } else {
                ArrayList<RecentQuiz> newList = new ArrayList<>();
                boolean isExist = false;
                int index = 0;
                for (int i = 0; i < StaticVariables.recentQuizzes.size(); i++) {
                    if (StaticVariables.subject.toLowerCase().equals(StaticVariables.recentQuizzes.get(i).getmQuizTitle().toLowerCase())) {
                        isExist = true;
                        index = i;
                        break;
                    }
                }
                if (isExist) {
                    newList.add(StaticVariables.recentQuizzes.get(index));
                    StaticVariables.recentQuizzes.remove(index);
                    newList.addAll(StaticVariables.recentQuizzes);
                    StaticVariables.recentQuizzes = newList;
                } else {
                    for (int i = 0; i < StaticVariables.allQuizzes.size(); i++) {
                        if (StaticVariables.subject.toLowerCase().equals(StaticVariables.allQuizzes.get(i).getmQuizTitle().toLowerCase())) {
                            newList.add(StaticVariables.allQuizzes.get(i));
                            break;
                        }
                    }
                    newList.addAll(StaticVariables.recentQuizzes);
                    StaticVariables.recentQuizzes = newList;
                }
            }

            RecentQuizAdapter recentQuizAdapter = new RecentQuizAdapter(this, StaticVariables.recentQuizzes);
            listView.setAdapter(recentQuizAdapter);

        }
    }

    public void onAstronomyClick() {
        QuestionsLayoutActivity.questionSubject = "Astronomy";
        QuizIntroActivity.title = getString(R.string.astronomy_quiz);
        QuizIntroActivity.description = getString(R.string.astronomy_quiz_intro_description);
        Intent i = new Intent(MainActivity.this, QuizIntroActivity.class);
        startActivity(i);
        StaticVariables.subject = "Astronomy Quiz";
    }

    public void onGeographyClick() {
        QuestionsLayoutActivity.questionSubject = "Geography";
        QuizIntroActivity.title = getString(R.string.geography_quiz);
        QuizIntroActivity.description = getString(R.string.geography_quiz_intro_description);
        Intent i = new Intent(MainActivity.this, QuizIntroActivity.class);
        startActivity(i);
        StaticVariables.subject = "Geography Quiz";
    }

    private void onZoologyClick() {
        QuestionsLayoutActivity.questionSubject = "Zoology";
        QuizIntroActivity.title = getString(R.string.zoology_quiz);
        QuizIntroActivity.description = getString(R.string.zoology_quiz_intro_description);
        Intent i = new Intent(MainActivity.this, QuizIntroActivity.class);
        startActivity(i);
        StaticVariables.subject = "Zoology Quiz";
    }
}

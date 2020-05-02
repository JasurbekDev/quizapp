package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizIntroActivity extends AppCompatActivity {
    public static String title = "";
    public static String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_intro);

        Button startQuizButton = findViewById(R.id.start_quiz_button);

        setTitle();
        setDescription();

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizIntroActivity.this, AstronomyQuizFirstActivity.class);
                startActivity(i);
            }
        });


    }

    public void setTitle() {
        TextView quizTitle = findViewById(R.id.astronomy_quiz_intro_title);
        quizTitle.setText(title);
    }

    public void setDescription() {
        TextView quizTitle = findViewById(R.id.astronomy_quiz_intro_description);
        quizTitle.setText(description);
    }

}

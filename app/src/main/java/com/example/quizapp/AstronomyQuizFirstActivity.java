package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AstronomyQuizFirstActivity extends AppCompatActivity {
    MediaPlayer player;
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    static String questionSubject = "";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private static List<String> userAnswerList = new ArrayList<>();
    public static int questionNumberIndex;
    public static List<Question> questions = new ArrayList<>();
    private static boolean isFirst = true;
    private static String buttonName = "Next";
    Button nextButton;
    TextView question;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    LinearLayout result;
    Button goHomeButton;
    static int correctAnswers;
    TextView score;
    TextView summary;
    boolean canGoBack = true;
    ImageView backArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astronomy_quiz_first);

        if(isFirst) {
            for (Question allQuestion : AllQuestions.getAllQuestions()) {
                if(allQuestion.getSubject().toLowerCase().equals(questionSubject.toLowerCase())) {
                    questions.add(allQuestion);
                }
            }
        }

        isFirst = false;

        nextButton = findViewById(R.id.next_button);
        radioGroup = findViewById(R.id.radio_group);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        score = findViewById(R.id.score);
        summary = findViewById(R.id.summary);
        backArrow = findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canGoBack) {
                    buttonName = "Next";
                    if(questionNumberIndex == 0) {
                        isFirst = true;
                        questions = new ArrayList<>();
                    } else {
                        questionNumberIndex--;
                        userAnswerList.remove(userAnswerList.size() - 1);
                    }
                    finish();
                } else {
                    Toast.makeText(AstronomyQuizFirstActivity.this, "You cannot go back!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        result = findViewById(R.id.result);
        goHomeButton = findViewById(R.id.go_home);
        result.setVisibility(View.GONE);
        if(questionNumberIndex <= questions.size()) {
            setContent(questions.get(questionNumberIndex).getQuestion(), questions.get(questionNumberIndex).getOption1(), questions.get(questionNumberIndex).getOption2(), questions.get(questionNumberIndex).getOption3(), questions.get(questionNumberIndex).getOption4());
        }
        nextButton.setText(buttonName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() != -1) {
                    if(questionNumberIndex < questions.size() - 1) {
                        if(questionNumberIndex == questions.size() - 2) {
                            buttonName = "Submit";
                        }
                        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        userAnswerList.add(radioButton.getText().toString());
                        questionNumberIndex++;
                        Intent i = new Intent(AstronomyQuizFirstActivity.this, AstronomyQuizFirstActivity.class);
                        startActivity(i);
                    } else {
                        releaseMediaPlayer();
                        player = MediaPlayer.create(AstronomyQuizFirstActivity.this, R.raw.completion_sound);
                        player.start();
                        player.setOnCompletionListener(onCompletionListener);

                        canGoBack = false;
                        buttonName = "Next";
                        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        userAnswerList.add(radioButton.getText().toString());
                        for (int i = 0; i < questions.size(); i++) {
                            if(userAnswerList.get(i).toLowerCase().equals(questions.get(i).getAnswer().toLowerCase())) {
                                correctAnswers++;
                            }
                        }
                        int correctAnswerRatio = (int) (((double)correctAnswers / questions.size()) * 100);
                        score.setText(getString(R.string.score,Integer.toString(correctAnswerRatio), "%"));
                        summary.setText(getString(R.string.summary, Integer.toString(questions.size()), (questions.size() <= 1) ? "question" : "questions", Integer.toString(correctAnswers), (correctAnswers <= 1) ? "is" : "are"));


                        radioGroup.setVisibility(View.INVISIBLE);
                        question.setVisibility(View.INVISIBLE);
                        nextButton.setVisibility(View.INVISIBLE);
                        backArrow.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                    }
                } else {
                    Vibrator vibration = (Vibrator) AstronomyQuizFirstActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                    assert vibration != null;
                    vibration.vibrate(30);
                    Toast.makeText(AstronomyQuizFirstActivity.this, "Please choose your answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                questionNumberIndex = 0;
                correctAnswers = 0;
                userAnswerList = new ArrayList<>();
                isFirst = true;
                questions = new ArrayList<>();
                Intent i = new Intent(AstronomyQuizFirstActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                StaticVariables.isList = true;

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (canGoBack) {
            buttonName = "Next";
            if(questionNumberIndex == 0) {
                isFirst = true;
                questions = new ArrayList<>();
            } else {
                questionNumberIndex--;
                userAnswerList.remove(userAnswerList.size() - 1);
            }
            super.onBackPressed();
        } else {
            Vibrator vibration = (Vibrator) AstronomyQuizFirstActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
            assert vibration != null;
            vibration.vibrate(30);
            Toast.makeText(AstronomyQuizFirstActivity.this, "You cannot go back!", Toast.LENGTH_SHORT).show();
        }
    }

    public void setContent(String qTitle, String option1, String option2, String option3, String option4) {
        question.setText(qTitle);
        ((RadioButton)radioGroup.getChildAt(0)).setText(option1);
        ((RadioButton)radioGroup.getChildAt(1)).setText(option2);
        ((RadioButton)radioGroup.getChildAt(2)).setText(option3);
        ((RadioButton)radioGroup.getChildAt(3)).setText(option4);
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;
        }
    }

}

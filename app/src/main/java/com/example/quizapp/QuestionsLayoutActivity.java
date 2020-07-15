package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionsLayoutActivity extends AppCompatActivity {
    MediaPlayer player;
    static String questionSubject = "";
    RadioGroup radioGroup;
    RadioButton radioButton;
    LinearLayout checkboxes;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    FrameLayout writtenAnswerContainer;
    EditText writtenAnswerEditText;
    static List<String> singleChoiceAnswerList = new ArrayList<>();
    static List<List<String>> checkBoxAnswerList = new ArrayList<>();
    static List<String> writtenAnswerList = new ArrayList<>();
    static int questionNumberIndex;
    static List<Question> questions = new ArrayList<>();
    static boolean isFirst = true;
    static String buttonName;
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
    String questionType;
    String writtenAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_layout);

        if (isFirst) {
            for (Question allQuestion : AllQuestions.getAllQuestions()) {
                if (allQuestion.getSubject().toLowerCase().equals(questionSubject.toLowerCase())) {
                    questions.add(allQuestion);
                    buttonName = getString(R.string.next);
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

        checkboxes = findViewById(R.id.checkboxes);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);

        writtenAnswerContainer = findViewById(R.id.written_answer_container);
        writtenAnswerEditText = findViewById(R.id.written_answer);

        questionType = questions.get(questionNumberIndex).getQuestionType();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canGoBack) {
                    buttonName = getString(R.string.next);
                    if (questionNumberIndex == 0) {
                        isFirst = true;
                        questions = new ArrayList<>();
                    } else {
                        questionNumberIndex--;
                        String previousQuestionType = questions.get(questionNumberIndex).getQuestionType();
                        switch (previousQuestionType) {
                            case "Single Choice":
                                singleChoiceAnswerList.remove(singleChoiceAnswerList.size() - 1);
                                break;
                            case "Checkbox":
                                checkBoxAnswerList.remove(checkBoxAnswerList.size() - 1);
                                break;
                            case "Text Entry":
                                writtenAnswerList.remove(writtenAnswerList.size() - 1);
                                break;
                        }
                    }
                    finish();
                } else {
                    Toast.makeText(QuestionsLayoutActivity.this, R.string.cannot_go_back, Toast.LENGTH_SHORT).show();
                }
            }
        });
        result = findViewById(R.id.result);
        goHomeButton = findViewById(R.id.go_home);
        result.setVisibility(View.GONE);
        if (questionNumberIndex <= questions.size()) {
            setContent(questions.get(questionNumberIndex).getQuestion(),
                    questions.get(questionNumberIndex).getOption1(),
                    questions.get(questionNumberIndex).getOption2(),
                    questions.get(questionNumberIndex).getOption3(),
                    questions.get(questionNumberIndex).getOption4());
        }
        nextButton.setText(buttonName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasWrittenAnswer = false;
                if (questionType.equals(getString(R.string.text_entry)) && !TextUtils.isEmpty(writtenAnswerEditText.getText().toString().trim())) {
                    hasWrittenAnswer = true;
                }
                if ((questionType.equals(getString(R.string.single_choice)) && radioGroup.getCheckedRadioButtonId() != -1)
                        || (questionType.equals(getString(R.string.checkbox)) && !(!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()))
                        || (questionType.equals(getString(R.string.text_entry)) && hasWrittenAnswer)) {
                    if (questionNumberIndex < questions.size() - 1) {
                        if (questionNumberIndex == questions.size() - 2) {
                            buttonName = getString(R.string.submit);
                        }
                        switch (questionType) {
                            case "Single Choice":
                                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                                singleChoiceAnswerList.add(radioButton.getText().toString());
                                break;
                            case "Checkbox":
                                List<String> userChoices = new ArrayList<>();
                                if (checkBox1.isChecked()) {
                                    userChoices.add(checkBox1.getText().toString());
                                }
                                if (checkBox2.isChecked()) {
                                    userChoices.add(checkBox2.getText().toString());
                                }
                                if (checkBox3.isChecked()) {
                                    userChoices.add(checkBox3.getText().toString());
                                }
                                if (checkBox4.isChecked()) {
                                    userChoices.add(checkBox4.getText().toString());
                                }
                                checkBoxAnswerList.add(userChoices);
                                break;
                            case "Text Entry":
                                writtenAnswer = writtenAnswerEditText.getText().toString().trim();
                                writtenAnswerList.add(writtenAnswer);
                                InputMethodManager imm = (InputMethodManager) getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                                assert imm != null;
                                imm.hideSoftInputFromWindow(writtenAnswerEditText.getWindowToken(), 0);
                                break;
                        }

                        questionNumberIndex++;
                        Intent i = new Intent(QuestionsLayoutActivity.this, QuestionsLayoutActivity.class);
                        startActivity(i);
                    } else {
                        releaseMediaPlayer();
                        player = MediaPlayer.create(QuestionsLayoutActivity.this, R.raw.completion_sound);
                        player.start();
                        player.setOnCompletionListener(onCompletionListener);

                        canGoBack = false;
                        buttonName = getString(R.string.next);
                        switch (questionType) {
                            case "Single Choice":
                                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                                singleChoiceAnswerList.add(radioButton.getText().toString());
                                break;
                            case "Checkbox":
                                List<String> userChoices = new ArrayList<>();
                                if (checkBox1.isChecked()) {
                                    userChoices.add(checkBox1.getText().toString());
                                }
                                if (checkBox2.isChecked()) {
                                    userChoices.add(checkBox2.getText().toString());
                                }
                                if (checkBox3.isChecked()) {
                                    userChoices.add(checkBox3.getText().toString());
                                }
                                if (checkBox4.isChecked()) {
                                    userChoices.add(checkBox4.getText().toString());
                                }
                                checkBoxAnswerList.add(userChoices);
                                break;
                            case "Text Entry":
                                writtenAnswer = writtenAnswerEditText.getText().toString().trim();
                                writtenAnswerList.add(writtenAnswer);
                                InputMethodManager imm = (InputMethodManager) getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                                assert imm != null;
                                imm.hideSoftInputFromWindow(writtenAnswerEditText.getWindowToken(), 0);
                                break;
                        }
                        int radioAnswerIndex = 0;
                        int checkboxAnswerIndex = 0;
                        int writtenAnswerIndex = 0;
                        for (int i = 0; i < questions.size(); i++) {
                            switch (questions.get(i).getQuestionType()) {
                                case "Single Choice":
                                    if (singleChoiceAnswerList.get(radioAnswerIndex++).toLowerCase().equals(questions.get(i).getAnswer().toLowerCase())) {
                                        correctAnswers++;
                                    }
                                    break;
                                case "Checkbox":
                                    String[] checkBoxAnswer = new String[checkBoxAnswerList.get(checkboxAnswerIndex).size()];
                                    for (int i1 = 0; i1 < checkBoxAnswerList.get(checkboxAnswerIndex).size(); i1++) {
                                        checkBoxAnswer[i1] = checkBoxAnswerList.get(checkboxAnswerIndex).get(i1);
                                    }
                                    checkboxAnswerIndex++;
                                    String[] correctCheckBoxAnswer = questions.get(i).getCheckboxAnswers();
                                    boolean isCheckBoxAnswerCorrect = true;
                                    for (int i1 = 0; i1 < correctCheckBoxAnswer.length; i1++) {
                                        if (checkBoxAnswer.length != correctCheckBoxAnswer.length) {
                                            isCheckBoxAnswerCorrect = false;
                                            break;
                                        } else if (!checkBoxAnswer[i1].equals(correctCheckBoxAnswer[i1])) {
                                            isCheckBoxAnswerCorrect = false;
                                            break;
                                        }
                                    }
                                    if (isCheckBoxAnswerCorrect) {
                                        correctAnswers++;
                                    }
                                    break;
                                case "Text Entry":
                                    if (writtenAnswerList.get(writtenAnswerIndex++).toLowerCase().equals(questions.get(i).getAnswer().toLowerCase())) {
                                        correctAnswers++;
                                    }
                                    break;
                            }
                        }
                        int correctAnswerRatio = (int) (((double) correctAnswers / questions.size()) * 100);
                        score.setText(getString(R.string.score, Integer.toString(correctAnswerRatio), getString(R.string.percentage_sign)));
                        summary.setText(getString(R.string.summary, Integer.toString(questions.size()), (questions.size() <= 1) ? getString(R.string.question) : getString(R.string.questions), Integer.toString(correctAnswers), (correctAnswers <= 1) ? getString(R.string.is) : getString(R.string.are)));

                        radioGroup.setVisibility(View.INVISIBLE);
                        checkboxes.setVisibility(View.GONE);
                        writtenAnswerContainer.setVisibility(View.GONE);
                        question.setVisibility(View.INVISIBLE);
                        nextButton.setVisibility(View.INVISIBLE);
                        backArrow.setVisibility(View.INVISIBLE);
                        result.setVisibility(View.VISIBLE);
                    }
                } else {
                    Vibrator vibration = (Vibrator) QuestionsLayoutActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                    assert vibration != null;
                    vibration.vibrate(30);
                    Toast.makeText(QuestionsLayoutActivity.this, R.string.not_aswered_text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                questionNumberIndex = 0;
                correctAnswers = 0;
                singleChoiceAnswerList = new ArrayList<>();
                checkBoxAnswerList = new ArrayList<>();
                writtenAnswerList = new ArrayList<>();
                isFirst = true;
                questions = new ArrayList<>();
                Intent i = new Intent(QuestionsLayoutActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                StaticVariables.isList = true;

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (canGoBack) {
            buttonName = getString(R.string.next);
            if (questionNumberIndex == 0) {
                isFirst = true;
                questions = new ArrayList<>();
            } else {
                questionNumberIndex--;
                String previousQuestionType = questions.get(questionNumberIndex).getQuestionType();
                if (previousQuestionType.equals(getString(R.string.single_choice))) {
                    singleChoiceAnswerList.remove(singleChoiceAnswerList.size() - 1);
                } else if (previousQuestionType.equals(getString(R.string.checkbox))) {
                    checkBoxAnswerList.remove(checkBoxAnswerList.size() - 1);
                } else if (previousQuestionType.equals(getString(R.string.text_entry))) {
                    writtenAnswerList.remove(writtenAnswerList.size() - 1);
                }
            }
            super.onBackPressed();
        } else {
            Vibrator vibration = (Vibrator) QuestionsLayoutActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
            assert vibration != null;
            vibration.vibrate(30);
            Toast.makeText(QuestionsLayoutActivity.this, getString(R.string.cannot_go_back), Toast.LENGTH_SHORT).show();
        }
    }

    public void setContent(String qTitle, String option1, String option2, String option3, String option4) {
        question.setText(qTitle);
        switch (questionType) {
            case "Single Choice":
                radioGroup.setVisibility(View.VISIBLE);
                checkboxes.setVisibility(View.GONE);
                writtenAnswerContainer.setVisibility(View.GONE);
                ((RadioButton) radioGroup.getChildAt(0)).setText(option1);
                ((RadioButton) radioGroup.getChildAt(1)).setText(option2);
                ((RadioButton) radioGroup.getChildAt(2)).setText(option3);
                ((RadioButton) radioGroup.getChildAt(3)).setText(option4);
                break;
            case "Checkbox":
                radioGroup.setVisibility(View.GONE);
                checkboxes.setVisibility(View.VISIBLE);
                writtenAnswerContainer.setVisibility(View.GONE);
                ((CheckBox) checkboxes.getChildAt(0)).setText(option1);
                ((CheckBox) checkboxes.getChildAt(1)).setText(option2);
                ((CheckBox) checkboxes.getChildAt(2)).setText(option3);
                ((CheckBox) checkboxes.getChildAt(3)).setText(option4);
                break;
            case "Text Entry":
                radioGroup.setVisibility(View.GONE);
                checkboxes.setVisibility(View.GONE);
                writtenAnswerContainer.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void releaseMediaPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
}
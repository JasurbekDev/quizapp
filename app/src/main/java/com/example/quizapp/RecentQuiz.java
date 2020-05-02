package com.example.quizapp;

import android.widget.Button;

public class RecentQuiz {
    private String mQuizTitle;
    private String mQuizDescription;
    private String buttonId;

    public RecentQuiz(String mQuizTitle, String mQuizDescription) {
        this.mQuizTitle = mQuizTitle;
        this.mQuizDescription = mQuizDescription;
    }

    public String getmQuizTitle() {
        return mQuizTitle;
    }

    public void setmQuizTitle(String mQuizTitle) {
        this.mQuizTitle = mQuizTitle;
    }

    public String getmQuizDescription() {
        return mQuizDescription;
    }

    public String getButton() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public void setmQuizDescription(String mQuizDescription) {
        this.mQuizDescription = mQuizDescription;
    }
}

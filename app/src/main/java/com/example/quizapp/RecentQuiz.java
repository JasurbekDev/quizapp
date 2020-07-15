package com.example.quizapp;

class RecentQuiz {
    private String mQuizTitle;
    private String mQuizDescription;

    RecentQuiz(String mQuizTitle, String mQuizDescription) {
        this.mQuizTitle = mQuizTitle;
        this.mQuizDescription = mQuizDescription;
    }

    String getmQuizTitle() {
        return mQuizTitle;
    }

    String getmQuizDescription() {
        return mQuizDescription;
    }
}

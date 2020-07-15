package com.example.quizapp;

class Question {
    private String subject;
    private String questionType;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String[] checkboxAnswers;

    Question(String subject, String questionType, String question, String option1, String option2, String option3, String option4, String answer) {
        this.subject = subject;
        this.questionType = questionType;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    Question(String subject, String questionType, String question, String option1, String option2, String option3, String option4, String[] checkboxAnswers) {
        this.subject = subject;
        this.questionType = questionType;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.checkboxAnswers = checkboxAnswers;
    }

    Question(String subject, String questionType, String question, String answer) {
        this.subject = subject;
        this.questionType = questionType;
        this.question = question;
        this.answer = answer;
    }

    String getSubject() {
        return subject;
    }

    String getQuestionType() {
        return questionType;
    }

    String getQuestion() {
        return question;
    }

    String getOption1() {
        return option1;
    }

    String getOption2() {
        return option2;
    }

    String getOption3() {
        return option3;
    }

    String getOption4() {
        return option4;
    }

    String getAnswer() {
        return answer;
    }

    String[] getCheckboxAnswers() {
        return checkboxAnswers;
    }
}

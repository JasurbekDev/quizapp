package com.example.quizapp;

import java.util.ArrayList;

class StaticVariables {
    static ArrayList<RecentQuiz> recentQuizzes = new ArrayList<>();
    static ArrayList<RecentQuiz> allQuizzes = new ArrayList<>();
    static String subject = "";
    static boolean isList = false;
    static boolean isFirst = true;
    static RecentQuiz astronomy = new RecentQuiz("Astronomy Quiz", "Improve your astronomy skills...");
    static RecentQuiz geography = new RecentQuiz("Geography Quiz", "Improve your geography skills...");
    static RecentQuiz zoology = new RecentQuiz("Zoology Quiz", "Improve your zoology skills...");
}

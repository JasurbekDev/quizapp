package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

class AllQuestions {

    static List<Question> getAllQuestions() {
        List<Question> allQuestions = new ArrayList<>();

        allQuestions.add(new Question("Astronomy", "Single Choice", "What is the closest planet to the Sun?", "Venus", "Mercury", "Earth", "Jupiter", "Mercury"));
        allQuestions.add(new Question("Astronomy", "Checkbox", "Choose planets that are smaller than Earth", "Mercury", "Mars", "Saturn", "Venus", new String[]{"Mercury", "Mars", "Venus"}));
        allQuestions.add(new Question("Astronomy", "Text Entry", "\"The Red Planet\" is the poetic name for ...?", "Mars"));

        allQuestions.add(new Question("Geography", "Text Entry", "What is the Earth's largest continent?", "Asia"));
        allQuestions.add(new Question("Geography", "Single Choice", "What country is home to Kangaroo Island?", "Australia", "France", "Japan", "Great Britain", "Australia"));
        allQuestions.add(new Question("Geography", "Checkbox", "Choose countries in Europe", "Germany", "Uzbekistan", "Denmark", "Australia", new String[]{"Germany", "Denmark"}));

        allQuestions.add(new Question("Zoology", "Checkbox", "Choose animals that can change their color", "Cats", "Octopuses", "Hummingbirds", "Turtles", new String[]{"Octopuses", "Hummingbirds"}));
        allQuestions.add(new Question("Zoology", "Text Entry", "A(n) _____ is known to use echolocation", "Bat"));
        allQuestions.add(new Question("Zoology", "Single Choice", "Which animal is able to rotate its head 270 degrees?", "Hamster", "Horse", "Owl", "Rat", "Owl"));

        return allQuestions;
    }
}

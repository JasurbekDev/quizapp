package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class AllQuestions {
    public static List<Question> allQuestions = new ArrayList<>();

    public static List<Question> getAllQuestions() {
        allQuestions = new ArrayList<>();

        allQuestions.add(new Question("Astronomy", "What is the closest planet to the Sun?", "Venus", "Mercury", "Earth", "Jupiter", "Mercury"));
        allQuestions.add(new Question("Astronomy", "What planet is famous for its big red spot on it?", "Mars", "Saturn", "Jupiter", "Venus", "Jupiter"));
        allQuestions.add(new Question("Astronomy", "Who was the first person to walk on the moon?", "Leonardo da Vinci", "Neil Armstrong", "Yuri Alekseyevich Gagarin", "Chris Hadfield", "Neil Armstrong"));
        allQuestions.add(new Question("Astronomy", "How many planets are in our solar system?", "5", "9", "7", "8", "8"));
        allQuestions.add(new Question("Astronomy", "Olympus Mons is a large volcanic mountain on which planet?", "Earth", "Saturn", "Mars", "There is no correct answer", "Mars"));

        allQuestions.add(new Question("Geography", "What is Earth's largest continent?", "Africa", "Europe", "Antarctica", "Asia", "Asia"));
        allQuestions.add(new Question("Geography", "What is the only sea without any coasts?", "Adriatic Sea", "Celebes Sea", "Mediterranean Sea", "Sargasso Sea", "Sargasso Sea"));
        allQuestions.add(new Question("Geography", "What country is home to Kangaroo Island?", "Australia", "France", "Japan", "Great Britain", "Australia"));
        allQuestions.add(new Question("Geography", "What continent is located at Latitude 90° S Longitude 0.00° E?", "Australia", "South America", "Antarctica", "Asia", "Antarctica"));
        allQuestions.add(new Question("Geography", "What is the tallest mountain in the world?", "Mount Kilamanjaro", "Aconcagua", "Qogir", "Mount Everest", "Mount Everest"));

        allQuestions.add(new Question("Zoology", "The name of which African animal means \"river horse\"?", "Hippopotamus", "Crocodile", "Water Buffalo", "Camel", "Hippopotamus"));
        allQuestions.add(new Question("Zoology", "What bird is the national emblem of the United States?", "Great Horned Owl", "Turkey", "Cardinal", "Bald Eagle", "Bald Eagle"));
        allQuestions.add(new Question("Zoology", "Which animal is known to use echolocation?", "Elephant", "Bat", "Rattlesnake", "Mackerel", "Bat"));
        allQuestions.add(new Question("Zoology", "Which animal is able to rotate its head 270 degrees?", "Hamster", "Horse", "Owl", "Rat", "Owl"));
        allQuestions.add(new Question("Zoology", "Which species of bird is the only one that can fly backwards?", "Toucan", "Hummingbird", "Bald Eagle", "African Grey", "Hummingbird"));

        return allQuestions;
    }
}

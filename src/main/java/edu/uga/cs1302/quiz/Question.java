package edu.uga.cs1302.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private Country country;
    private List<String> answerChoices;  // size 3, includes 1 correct + 2 incorrect
    private String correctAnswer;

    private static final String[] CONTINENTS = {
        "Africa", "Asia", "Europe", "North America", "Oceania", "South America", "Antarctica"
    };

    public Question(Country country) {
        this.country = country;
        this.correctAnswer = country.getContinent();
        this.answerChoices = new ArrayList<>();

        // Add the correct answer first
        answerChoices.add(correctAnswer);

        // Add 2 incorrect continents
        List<String> available = new ArrayList<>();
        for (String cont : CONTINENTS) {
            if (!cont.equals(correctAnswer)) {
                available.add(cont);
            }
        }

        // Randomly pick 2 incorrect ones
        Collections.shuffle(available);
        answerChoices.add(available.get(0));
        answerChoices.add(available.get(1));

        // Shuffle the answer choices so correct one is in a random position
        Collections.shuffle(answerChoices);
    }

    public Country getCountry() {
        return country;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "On which continent is " + country.getName() + " located?\nChoices: " + answerChoices;
    }
}

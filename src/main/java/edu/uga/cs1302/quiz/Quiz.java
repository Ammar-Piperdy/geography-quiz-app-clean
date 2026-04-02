package edu.uga.cs1302.quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Quiz {

    private List<Question> questions;
    private int currentScore;

    public static final int TOTAL_QUESTIONS = 6;

    public Quiz(CountryCollection countryCollection) {
        questions = new ArrayList<>();
        currentScore = 0;
        Set<String> usedCountryNames = new HashSet<>();

        int countryListSize = countryCollection.size();

        while (questions.size() < TOTAL_QUESTIONS) {
            int randomIndex = ThreadLocalRandom.current().nextInt(countryListSize);
            Country selectedCountry = countryCollection.getCountry(randomIndex);

            if (!usedCountryNames.contains(selectedCountry.getName())) {
                usedCountryNames.add(selectedCountry.getName());
                questions.add(new Question(selectedCountry));
            }
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void incrementScore() {
        currentScore++;
    }

    public int getScore() {
        return currentScore;
    }

    public int getTotalQuestions() {
        return TOTAL_QUESTIONS;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Quiz Questions:\n");
        for (int i = 0; i < questions.size(); i++) {
            sb.append((i + 1)).append(". ")
              .append("On which continent is ")
              .append(questions.get(i).getCountry().getName())
              .append(" located?\n");
        }
        return sb.toString();
    }
}

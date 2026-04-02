package edu.uga.cs1302.quiz;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class QuizResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<QuizScore> results;

    public QuizResult() {
        results = new LinkedList<>();
    }

    public void addResult(QuizScore score) {
        results.add(0, score); // Add to front to keep newest first
    }

    public List<QuizScore> getResults() {
        return results;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving quiz results: " + e.getMessage());
        }
    }

    public static QuizResult loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return new QuizResult(); // Return empty results if file doesn't exist
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (QuizResult) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading quiz results: " + e.getMessage());
            return new QuizResult();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Past Quiz Results:\n");
        for (QuizScore score : results) {
            sb.append(score.toString()).append("\n");
        }
        return sb.toString();
    }
}

package edu.uga.cs1302.quiz;

import java.io.Serializable;
import java.util.Date;

public class QuizScore implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date date;
    private int score;

    public QuizScore(Date date, int score) {
        this.date = date;
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("Score: %d out of 6 on %s", score, date.toString());
    }
}

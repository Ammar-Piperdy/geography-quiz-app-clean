package edu.uga.cs1302.quiz;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import java.util.Date;
import java.util.List;

public class QuizWindow {

    private Stage stage;
    private Quiz quiz;
    private int questionIndex = 0;
    private Label feedbackLabel;
    private Label questionLabel;
    private ToggleGroup choicesGroup;
    private VBox mainLayout;
    private Button submitButton;
    private List<Question> questions;

    public QuizWindow(CountryCollection countryCollection, QuizResult quizResult, String resultFile) {
        quiz = new Quiz(countryCollection);
        questions = quiz.getQuestions();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Geography Quiz");
        stage.setMinWidth(450);

        feedbackLabel = new Label(""); // e.g. "Correct!" or "Incorrect!"
        questionLabel = new Label();
        choicesGroup = new ToggleGroup();
        mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        submitButton = new Button("Submit");
        submitButton.setOnAction(e -> handleSubmit(quizResult, resultFile));

        // Build the first question screen
        showQuestion();

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showQuestion() {
        mainLayout.getChildren().clear();

        Question currentQuestion = questions.get(questionIndex);

        // Label at top: feedback or blank on first question
        mainLayout.getChildren().add(feedbackLabel);

        // Set new question
        questionLabel.setText("Q" + (questionIndex + 1) + ": On which continent is " +
                currentQuestion.getCountry().getName() + " located?");
        mainLayout.getChildren().add(questionLabel);

        // Show radio buttons
        choicesGroup = new ToggleGroup();
        for (String choice : currentQuestion.getAnswerChoices()) {
            RadioButton rb = new RadioButton(choice);
            rb.setToggleGroup(choicesGroup);
            mainLayout.getChildren().add(rb);
        }

        mainLayout.getChildren().add(submitButton);
    }

    private void handleSubmit(QuizResult quizResult, String resultFile) {
        RadioButton selected = (RadioButton) choicesGroup.getSelectedToggle();
        if (selected == null) {
            feedbackLabel.setText("Please select an answer.");
            return;
        }

        String userAnswer = selected.getText();
        Question current = questions.get(questionIndex);
        boolean correct = userAnswer.equals(current.getCorrectAnswer());

        if (correct) {
            quiz.incrementScore();
            feedbackLabel.setText("Correct!");
        } else {
            feedbackLabel.setText("Incorrect! Correct answer is " + current.getCorrectAnswer());
        }

        questionIndex++;

        // Last question? Show result
        if (questionIndex == Quiz.TOTAL_QUESTIONS) {
            showFinalResult(quizResult, resultFile);
        } else {
            showQuestion();
        }
    }

    private void showFinalResult(QuizResult quizResult, String resultFile) {
        mainLayout.getChildren().clear();

        Label finalFeedback = new Label(feedbackLabel.getText());
        Label scoreLabel = new Label("Your score is: " + quiz.getScore() + " out of " + Quiz.TOTAL_QUESTIONS);
        Button closeButton = new Button("Close");

        closeButton.setOnAction(e -> stage.close());

        VBox.setMargin(finalFeedback, new Insets(0, 0, 10, 0));
        mainLayout.getChildren().addAll(finalFeedback, scoreLabel, closeButton);

        // Save result
        QuizScore score = new QuizScore(new Date(), quiz.getScore());
        quizResult.addResult(score);
        quizResult.saveToFile(resultFile);
    }
}

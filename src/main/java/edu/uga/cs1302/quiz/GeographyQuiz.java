package edu.uga.cs1302.quiz;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

import java.io.*;

public class GeographyQuiz extends Application {

    private CountryCollection countryCollection;
    private QuizResult quizResult;
    private final String RESULT_FILE = "quizzes.dat";

    @Override
    public void start(Stage primaryStage) {
        // Load countries
        countryCollection = new CountryCollection();

        // Load past results
        File file = new File(RESULT_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                quizResult = (QuizResult) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                quizResult = new QuizResult();
            }
        } else {
            quizResult = new QuizResult();
        }

        // UI Setup
        primaryStage.setTitle("Geography Quiz");

        Label welcomeLabel = new Label(
	    "Welcome to the Geography Quiz!\n\n" +
	    "You will be asked 6 questions about countries and their continents.\n" +
	    "Each question has 3 answer choices. Try to get as many correct as you can!"
        );
	welcomeLabel.setWrapText(true);
	welcomeLabel.setTextAlignment(TextAlignment.CENTER);

        Button startQuizButton = new Button("Start New Quiz");
        Button viewResultsButton = new Button("View Past Results");
        Button helpButton = new Button("Help");
        Button exitButton = new Button("Exit");

        startQuizButton.setOnAction(e -> new QuizWindow(countryCollection, quizResult, RESULT_FILE));
        viewResultsButton.setOnAction(e -> new ResultsWindow(quizResult));
        helpButton.setOnAction(e -> new HelpWindow());
        exitButton.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(10, welcomeLabel, startQuizButton, viewResultsButton, helpButton, exitButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinWidth(400);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

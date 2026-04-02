package edu.uga.cs1302.quiz;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import java.util.List;

public class ResultsWindow {

    public ResultsWindow(QuizResult quizResult) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Past Quiz Results");
        stage.setMinWidth(400);
        stage.setMinHeight(300);

        VBox resultBox = new VBox(10);
        resultBox.setPadding(new Insets(15));
        resultBox.setAlignment(Pos.TOP_LEFT);

        List<QuizScore> results = quizResult.getResults();
        if (results.isEmpty()) {
            resultBox.getChildren().add(new Label("No past quiz results available."));
        } else {
            for (QuizScore score : results) {
                Label label = new Label(score.toString());
                resultBox.getChildren().add(label);
            }
        }

        ScrollPane scrollPane = new ScrollPane(resultBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, scrollPane, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}

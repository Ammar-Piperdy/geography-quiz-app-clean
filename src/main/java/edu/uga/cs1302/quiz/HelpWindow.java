package edu.uga.cs1302.quiz;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

public class HelpWindow {

    public HelpWindow() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Help - Geography Quiz");
        stage.setMinWidth(450);
        stage.setMinHeight(300);

        // Instructions text
        String instructions = """
            Welcome to the Geography Quiz!

            - Click 'Start New Quiz' to begin a 6-question quiz.
            - Each question will ask which continent a country is located on.
            - Choose the correct answer from the 3 options provided.
            - You will receive feedback after each question.
            - At the end of the quiz, your score will be displayed.
            - Your quiz results are saved and can be reviewed anytime.

            Use the 'View Past Results' button on the main menu to see your history.

            Good luck and have fun!
            """;

        Label helpLabel = new Label(instructions);
        helpLabel.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(helpLabel);
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

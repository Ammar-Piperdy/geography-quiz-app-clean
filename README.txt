Author: Ammar Piperdy  
Class: CSCI 1302  
Date: 07/29/2025  

Description:  
-------------
This project implements a multiple-choice geography quiz application using JavaFX.  
It displays a series of randomly generated questions asking users to match countries to their continents.  
The quiz tracks user responses, calculates a final score, and stores past quiz results persistently.  
A help window explains the rules, and a results window summarizes performance after the quiz ends.

Directory Structure:  
---------------------
- src/main/java/edu/uga/cs1302/quiz/
  - GeographyQuiz.java: Main class that launches the JavaFX application.
  - Country.java: Represents a country with its name and continent.
  - CountryCollection.java: Loads countries from a CSV and provides access to them.
  - Question.java: Represents a multiple-choice quiz question.
  - Quiz.java: Stores a collection of `Question` objects for one quiz session.
  - QuizWindow.java: JavaFX GUI for displaying questions and collecting answers.
  - QuizResult.java: Stores the score and summary from a completed quiz.
  - QuizScore.java: Utility class for formatting score output.
  - HelpWindow.java: JavaFX window showing quiz instructions.
  - ResultsWindow.java: Displays quiz summary and past scores.

- src/main/resources/
  - country_continent.csv: CSV file with country-continent pairs.

- target/classes/:  
  - Contains compiled `.class` files and the copied resource CSV.

- quizzes.dat:
  - A serialized file used to store past quiz results across sessions.

Build and Run:  
--------------
To compile and build the project, run:  
    mvn clean compile  

To run the application:  
    mvn javafx:run  

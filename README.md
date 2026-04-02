# Geography Quiz Application

A Java-based geography quiz application built using JavaFX. The application provides an interactive graphical interface where users answer multiple-choice questions matching countries to their continents.

## Features
- Randomly generated geography questions
- Multiple-choice answer selection
- Score tracking and final results display
- Persistent storage of past quiz results
- Help window explaining quiz rules
- Results window summarizing performance

## Technologies
- Java
- JavaFX
- Maven

## Project Structure
- Object-oriented design with multiple classes (Quiz, Question, Country, QuizResult, etc.)
- Uses ArrayList for managing quiz data
- Data loaded from a CSV file (`country_continent.csv`)
- GUI implemented using JavaFX components

## How to Run

From the project root:

```bash
mvn clean compile
mvn javafx:run

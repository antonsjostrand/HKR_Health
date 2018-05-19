package Project.Controller;

import Project.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkoutHistoryController implements Initializable {

    private ArrayList<String> retrievedStrings;
    private ArrayList<String> retrievedWorkouts = new ArrayList<>();
    private ArrayList<String> retrievedIDs = new ArrayList<>();
    private ArrayList<String> retrievedWorkoutContent = new ArrayList<>();
    private ArrayList<String> retrievedDates = new ArrayList<>();

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ListView<String> workoutLV;

    @FXML
    private TextArea exerciseTA;

    @FXML
    private TextArea setsTA;

    @FXML
    private TextArea repsTA;

    @FXML
    private TextArea weightTA;

    @FXML
    private Label dateLabel;

    @FXML
    private Button exercisesButton;

    @FXML
    private Button stretchButton;

    @FXML
    private Button timerButton;

    @FXML
    private Button diaryButton;

    @FXML
    private Button nutritionButton;

    @FXML
    private Button feedbackButton;

    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    void homeButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exerciseButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/exerciseScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void stretchButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/stretchScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void timerButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/timerScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {

        }
    }

    @FXML
    void diaryButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void nutritionButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/nutritionScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void feedbackButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/feedbackScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            retrievedStrings = DatabaseConnection.getInstance().retrieveAllWorkouts();

            for (int counter = 0; counter < retrievedStrings.size(); counter = counter + 2){
                retrievedIDs.add(retrievedStrings.get(counter));
                retrievedDates.add(retrievedStrings.get(counter+1));
            }

            for (int i = 0; i < retrievedStrings.size(); i = i + 2){
                String workoutString = "ID: " + retrievedStrings.get(i) + " DATE: " + retrievedStrings.get(i+1);
                retrievedWorkouts.add(workoutString);
            }


            workoutLV.getItems().addAll(retrievedWorkouts);
            workoutLV.setOnMouseClicked(e -> {
                exerciseTA.clear();
                repsTA.clear();
                setsTA.clear();
                weightTA.clear();

                int index = workoutLV.getSelectionModel().getSelectedIndex();
                String workoutID = retrievedIDs.get(index);

                retrievedWorkoutContent = DatabaseConnection.getInstance().retrieveSpecificWorkoutExercises(workoutID);

                dateLabel.setText(retrievedDates.get(index));

                for (int i = 0; i < retrievedWorkoutContent.size(); i = i + 4){
                    exerciseTA.appendText(retrievedWorkoutContent.get(i) + "\n");
                    setsTA.appendText(retrievedWorkoutContent.get(i+1) + "\n");
                    repsTA.appendText(retrievedWorkoutContent.get(i+2) + "\n");
                    weightTA.appendText(retrievedWorkoutContent.get(i+3) + "\n");
                }


            });



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Exercise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuestStretchController implements Initializable {

    @FXML private javafx.scene.image.ImageView hkrlogo;

    @FXML
    private TextArea instructionTA;

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ImageView imageIV;

    @FXML
    private Label instructionLabel;

    @FXML
    private Label muscleGroupLabel;

    @FXML
    private MenuItem fullBodyMenu, lowerBodyMenu, upperBodyMenu;

    @FXML
    private ListView<Exercise> exerciseLV;

    private Exercise retrievedExercise;


    @FXML
    void bodyPartMenuPressed(ActionEvent event){

    }

    @FXML
    void guestPressedBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    void guestButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void lowerBodyMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Lower body");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this bodypart in the database.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void upperBodyMenuPressed(ActionEvent event){
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Upper body");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this bodypart in the database.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fullBodyMenuPressed(ActionEvent event){
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Full body");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this bodypart in the database.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void guestPressedExercisesButton(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestExercises.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void guestPressedStretchButton(ActionEvent event) {

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestStretch.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void guestPressedTimerButton(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestTimer.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void muscleGroupMenuPressed(ActionEvent event) {

    }


    //Metod som skriver ut informationen om övningen på scenen.
    public void viewExercises(Exercise exercise) {
        exerciseNameLabel.setText(exercise.getName());
        instructionTA.setText("Instruction: " + exercise.getInstruction());
        imageIV.setImage(new Image(exercise.getImagePath()));

        String[] muscleGroups = exercise.getMuscleGroup();
        String muscleGroupSentence = "";

        for (int i = 0; i < muscleGroups.length; i++) {
            if (muscleGroups[i] == null) {
                break;
            } else {
                muscleGroupSentence = muscleGroupSentence + muscleGroups[i] + ", ";
            }
        }

        muscleGroupLabel.setText("Musclegroups: " + muscleGroupSentence);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

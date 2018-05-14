package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Exercise;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ExerciseController {

    private Exercise retrievedExercise;

    @FXML
    private MenuButton muscleGroupMenu;

    @FXML
    private MenuItem chestMenu;

    @FXML
    private MenuItem shouldersMenu;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem bicepsMenu;

    @FXML
    private MenuItem tricepsMenu;

    @FXML
    private MenuItem gluteusMenu;

    @FXML
    private MenuItem quadMenu;

    @FXML
    private MenuItem hamstringMenu;

    @FXML
    private MenuItem calvesMenu;

    @FXML
    private Button cancelButton;

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ImageView imageIV;

    @FXML
    private Label instructionLabel;

    @FXML
    private Label muscleGroupLabel;

    @FXML
    private ListView<Exercise> exerciseLV;

    @FXML
    void backMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Back");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void bicepsMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Biceps");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void calvesMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Calves");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void chestMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Chest");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gluteusMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Gluteus");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void hamstringMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Hamstrings");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void muscleGroupMenuPressed(ActionEvent event) {

    }

    @FXML
    void quadMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Quadriceps");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void shouldersMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Shoulders");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void tricepsMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Triceps");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e ->{
                Exercise testExercise = exerciseLV.getSelectionModel().getSelectedItem();
                retrievedExercise = DatabaseConnection.getInstance().retrieveSpecificExercise(testExercise.getName());

                viewExercises(retrievedExercise);

            });
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonPressed(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void viewExercises(Exercise exercise) {
        exerciseNameLabel.setText(exercise.getName());
        instructionLabel.setText("Instruction: " + exercise.getInstruction());

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
}

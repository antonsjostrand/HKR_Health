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


public class StretchController implements Initializable {
    private Exercise retrievedExercise;

    @FXML
    private MenuButton bodyPartMenu;

    @FXML
    private MenuItem lowerBodyMenu;

    @FXML
    private MenuItem upperBodyMenu;

    @FXML
    private MenuItem fullBodyMenu;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView imageIV;

    @FXML
    private Label instructionLabel, muscleGroupLabel, exerciseNameLabel;


    @FXML
    private ListView<Exercise> exerciseLV;

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
    void bodyPartMenuPressed(ActionEvent event) {

    }


    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metod som skriver ut informationen om övningen på scenen.
    public void viewExercises(Exercise exercise) {
        exerciseNameLabel.setText(exercise.getName());
        instructionLabel.setText("Instruction: " + exercise.getInstruction());
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

    @FXML void homeButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
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
            //Fixa error handling
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
            //Fixa error handling
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
            //Fixa error handling
        }
    }


    @FXML
    void myAccountButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/accountInfoScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
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
            //Fixa error handling
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
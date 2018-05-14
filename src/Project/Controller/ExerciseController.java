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
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExerciseController implements Initializable {

    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private Exercise retrievedExercise;
    private String exerciseName;

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
    private TextArea exerciseTA;

    @FXML
    private TextField chosenExerciseTF;

    @FXML
    void backMenuPressed(ActionEvent event) {

    }

    @FXML
    void bicepsMenuPressed(ActionEvent event) {

    }

    @FXML
    void calvesMenuPressed(ActionEvent event) {

    }

    @FXML
    void chestMenuPressed(ActionEvent event) {
        try{
            viewExercises("Chest");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gluteusMenuPressed(ActionEvent event) {

    }

    @FXML
    void hamstringMenuPressed(ActionEvent event) {

    }

    @FXML
    void muscleGroupMenuPressed(ActionEvent event) {

    }

    @FXML
    void quadMenuPressed(ActionEvent event) {

    }

    @FXML
    void shouldersMenuPressed(ActionEvent event) {

    }

    @FXML
    void tricepsMenuPressed(ActionEvent event) {

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

    @FXML
    void viewButtonPressed(ActionEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exerciseTA.setText("|          Exercise          |");
        exerciseTA.appendText("\n------------------------------");
    }

    public void viewExercises(String musclegroup) throws Exception{
        exerciseList = DatabaseConnection.getInstance().retrieveExercise(musclegroup);

        for (int i = 0; i < exerciseList.size(); i++){
            retrievedExercise = exerciseList.get(i);
            exerciseName = retrievedExercise.getName();

            exerciseTA.appendText("\n" + exerciseName);
        }
    }
}

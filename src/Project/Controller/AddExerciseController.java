package Project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddExerciseController {

    @FXML
    private Button cancelButton;

    @FXML private Button createButton;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField typeTF;

    @FXML
    private TextField muscleOneTF;

    @FXML
    private TextField muscleTwoTF;

    @FXML
    private TextField muscleThreeTF;

    @FXML
    private TextField muscleFourTF;

    @FXML
    private TextArea instructionTA;

    @FXML
    private TextField pathTF;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/adminScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void createButtonPressed(ActionEvent event){

    }



}

package Project.Controller;

import Project.DatabaseConnection;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    private Button exerciseButton;

    @FXML
    private Button nutritionButton;

    @FXML
    private Button feedbackButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try{
            changeScene("loginScene", event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void exerciseButtonPressed(ActionEvent event) {
        try{
            changeScene("addExerciseScene", event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void feedbackButtonPressed(ActionEvent event) {

    }

    @FXML
    void newAdminButtonPressed(ActionEvent event){
        try{
            changeScene("addAdminScene", event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void nutritionButtonPressed(ActionEvent event) {
        try{
            changeScene("addNutritionScene", event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeScene(String resource, ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/" + resource + ".fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}

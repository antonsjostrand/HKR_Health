package Project.Controller;

import Project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class AddNutritionController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField kcalTF;

    @FXML
    private TextField proteinTF;

    @FXML
    private TextField carbohydratesTF;

    @FXML
    private TextField fatTF;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
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

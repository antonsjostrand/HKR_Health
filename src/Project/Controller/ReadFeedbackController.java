package Project.Controller;

import Project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadFeedbackController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button readFeedbackButton;

    @FXML
    private TextArea allFeedbacksTA;

    @FXML
    private TextArea chosenFeedbackTA;

    @FXML
    private TextField feedbackIDTF;

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
    void readFeedbackButtonPressed(ActionEvent event) {
        String chosenFeedback;

        try{
            chosenFeedback = DatabaseConnection.getInstance().retrieveChosenFeedback(feedbackIDTF.getText());

            chosenFeedbackTA.setText(chosenFeedback);


        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> feedbackList;
        try{
            allFeedbacksTA.setText("|      ID      |                   Header                   |                            Written by user                            |");
            allFeedbacksTA.appendText("\n-------------------------------------------------------------------------------------------------------");

            feedbackList = DatabaseConnection.getInstance().retrieveFeedbacksAndWriters();

            for (int i = 0; i < feedbackList.size(); i = i + 6){
                allFeedbacksTA.appendText(" \n  " + feedbackList.get(i) + "              " + feedbackList.get(i+2) + "                            "
                        + feedbackList.get(i+3) + "   -   " + feedbackList.get(i+4) + " " + feedbackList.get(i+5));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
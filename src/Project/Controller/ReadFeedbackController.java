package Project.Controller;

import Project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    @FXML private Button deleteButton;
    @FXML private Button refreshButton;

    @FXML
    private TextArea idFeedbackTA;

    @FXML
    private TextArea headerFeedbackTA;

    @FXML
    private TextArea writerTA;

    @FXML
    private TextArea chosenFeedbackTA;

    @FXML
    private TextField feedbackIDTF;

    @FXML
    private TextField deleteIDTF;

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

    @FXML
    void deleteButtonPressed(ActionEvent event){
        try{
            DatabaseConnection.getInstance().deleteFeedbackFromDB(deleteIDTF.getText());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Feedback deleted.");
            alert.setContentText("The feedback with ID: " + deleteIDTF.getText() + " is now deleted.");
            alert.showAndWait();

            deleteIDTF.clear();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void refreshButtonPressed(ActionEvent event){
        ArrayList<String> feedbackList;
        try {
            idFeedbackTA.setText("  ID  ");
            idFeedbackTA.appendText("\n---------");
            headerFeedbackTA.setText("     HEADER     ");
            headerFeedbackTA.appendText("\n--------------------------------------");
            writerTA.setText("            USER            ");
            writerTA.appendText("\n-------------------------------------------------");

            feedbackList = DatabaseConnection.getInstance().retrieveFeedbacksAndWriters();

            for (int i = 0; i < feedbackList.size(); i = i + 6) {

                idFeedbackTA.appendText("\n" + feedbackList.get(i));
                headerFeedbackTA.appendText("\n" + feedbackList.get(i + 2));
                writerTA.appendText("\n"+ feedbackList.get(i + 3) + "   -   " + feedbackList.get(i + 4) + " " + feedbackList.get(i + 5));

            }

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No feedback");
            alert.setContentText("Currently there is no feedbacks to read.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> feedbackList;
        try {
            idFeedbackTA.setText("  ID  ");
            idFeedbackTA.appendText("\n---------");
            headerFeedbackTA.setText("     HEADER     ");
            headerFeedbackTA.appendText("\n--------------------------------------");
            writerTA.setText("            USER            ");
            writerTA.appendText("\n-------------------------------------------------");

            feedbackList = DatabaseConnection.getInstance().retrieveFeedbacksAndWriters();

            for (int i = 0; i < feedbackList.size(); i = i + 6) {

                idFeedbackTA.appendText("\n" + feedbackList.get(i));
                headerFeedbackTA.appendText("\n" + feedbackList.get(i + 2));
                writerTA.appendText("\n"+ feedbackList.get(i + 3) + "   -   " + feedbackList.get(i + 4) + " " + feedbackList.get(i + 5));

            }

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No feedback");
            alert.setContentText("Currently there is no feedbacks to read.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
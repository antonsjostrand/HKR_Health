package Project.Controller;

import Project.DatabaseConnection;
import Project.UserInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;

public class FeedbackController {

    private char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @FXML private TextField headerTF;
    @FXML private TextArea contentTA;
    @FXML private Button postButton;
    @FXML private Button cancelButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML
    void postButtonPressed(ActionEvent event) {
        try {
            checkIfInputEmpty();
            checkHeaderFormat(headerTF.getText());
            checkContentFormat(contentTA.getText());

            createFeedback();

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input failure");
            alert.setContentText("Make sure there is no empty textfields.");
            alert.showAndWait();

        }catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input failure");
            alert.setContentText("Make sure there is no digits in the header and the content has to be between 1-255 characters.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //metod som kollar om formattet på headern är korrekt.
    public void checkHeaderFormat(String header){
        if (header.length() > 25){
            headerTF.clear();
            headerTF.setText("Enter a proper header.");
            headerTF.requestFocus();
            throw new InputMismatchException();
        }

        for (int i = 0; i < numbers.length; i++){
            if (header.contains(String.valueOf(i))){
                headerTF.clear();
                headerTF.setText("Enter a proper header.");
                headerTF.requestFocus();
                throw new InputMismatchException();
            }
        }


    }

    //Metod som kollar om formattet på content är korrekt.
    public void checkContentFormat(String content){
        if (content.length() < 1 || content.length() > 254 ){
            contentTA.clear();
            contentTA.setText("Enter a proper feedback.");
            contentTA.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Metod som kollar ifall textfieldsen är tomma.
    public void checkIfInputEmpty(){
        if (headerTF.getText().isEmpty() || contentTA.getText().isEmpty()){
            throw new NullPointerException();
        }
    }

    //Metod som skapar och lagrar en feedback i databasen.
    public void createFeedback(){
        String username = UserInformation.getInstance().getUsername();
        String ssn = UserInformation.getInstance().getSSN();

        DatabaseConnection.getInstance().addFeedbackToDB(ssn, username, headerTF.getText(), contentTA.getText());

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Feedback saved.");
        alert.setContentText("Your feedback has now been saved.");
        alert.showAndWait();

        headerTF.clear();
        contentTA.clear();
    }
}
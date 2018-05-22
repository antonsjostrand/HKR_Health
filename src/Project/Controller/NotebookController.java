package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Diary;
import Project.Model.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class NotebookController{

    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};

    @FXML
    private TextField todaysDate, title;
    @FXML
    private TextArea notes;
    @FXML
    private ImageView notebookLogo;

    @FXML
    void goBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveDiaryButton(ActionEvent event) {
        try {
            checkIfInputIsEmpty(title.getText(), notes.getText());
            checkDateFormat(todaysDate.getText());

            saveDiary();

        } catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The values entered is not following the rules.");
            alert.showAndWait();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The textfields cannot be empty.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metod som kollar så att datumet är i korrekt format
    public void checkDateFormat(String date) {
        if (date.length() != 8) {
            todaysDate.clear();
            todaysDate.setText("DD/MM-YY");
            todaysDate.requestFocus();
            throw new InputMismatchException();
        }

        if ((date.charAt(1) == '0' && date.charAt(0) == '0')|| (date.charAt(3) == '0' &&  date.charAt(4) == '0')) {
            todaysDate.clear();
            todaysDate.setText("DD/MM-YY");
            todaysDate.requestFocus();
            throw new InputMismatchException();
        }

        for (int counter = 0; counter < letters.length; counter++) {
            if (date.contains(String.valueOf(letters[counter]))) {
                todaysDate.clear();
                todaysDate.setText("DD/MM-YY");
                todaysDate.requestFocus();
                throw new InputMismatchException();
            }
        }

        if (date.charAt(2) == '/' || date.charAt(5) == '-') {
            return;
        }
        else {
            todaysDate.clear();
            todaysDate.setText("DD/MM-YY");
            todaysDate.requestFocus();
            throw new InputMismatchException();
        }
    }
    //Kollar så att ingen input är tom.
    public void checkIfInputIsEmpty(String titleText, String notesText) {
        if (title.getText().isEmpty() || todaysDate.getText().isEmpty() || notes.getText().isEmpty()) {
            throw new NullPointerException();
        }
        if (titleText.trim().length() == 0) {
            title.clear();
            title.setText("Enter a title.");
            title.requestFocus();
            throw new InputMismatchException();
        }
        if (notesText.trim().length() == 0) {
            notes.clear();
            notes.setText("Enter some text.");
            notes.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Metod som skapar ett saveDiary objekt som sedan lagras i databasen.
    public void saveDiary() {
        try {
            Note newNote = new Note(title.getText(), notes.getText(), todaysDate.getText());

            DatabaseConnection.getInstance().addNoteToDB(newNote);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Diary saved");
            alert.setContentText("Diary saved successful. Press cancel to go back to the diary scene.");
            alert.showAndWait();

            notes.clear();
            title.clear();
            todaysDate.clear();


        }catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Date incorrect.");
            alert.setContentText("Enter a valid date.");
            alert.showAndWait();

            todaysDate.clear();
            todaysDate.setText("DD/MM-YY");
            todaysDate.requestFocus();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    @FXML void exerciseButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/exerciseScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML void stretchButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/stretchScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML void timerButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/timerScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){

        }
    }

    @FXML void diaryButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML void nutritionButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/nutritionScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    @FXML void cancelButtonPressed(ActionEvent event){
        try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
    }
}

    @FXML void feedbackButtonPressed(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/feedbackScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
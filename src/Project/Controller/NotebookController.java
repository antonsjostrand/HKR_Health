package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Diary;
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
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class NotebookController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/Resources/hkrlogo.png"));
        notebookLogo.setImage(image);
    }

    @FXML
    void goBack(ActionEvent event) {
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

    @FXML
    void saveDiaryButton(ActionEvent event) {
        try {
            checkIfInputIsEmpty();
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
        } else {
            todaysDate.clear();
            todaysDate.setText("DD/MM-YY");
            todaysDate.requestFocus();
            throw new InputMismatchException();
        }
    }
    //Kollar så att ingen input är tom.
    public void checkIfInputIsEmpty(){
        if (title.getText().isEmpty() || todaysDate.getText().isEmpty() || notes.getText().isEmpty()){
            throw new NullPointerException();
        }
    }

    //Metod som skapar ett saveDiary objekt som sedan lagras i databasen.
    public void saveDiary() {
        try {

            title.getText();
            todaysDate.getText();
            notes.getText();

            title.clear();
            todaysDate.clear();
            notes.clear();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Diary saved");
            alert.setContentText("Diary saved successful. Press cancel to go back to the diary scene.");
            alert.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
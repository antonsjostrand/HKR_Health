package Project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DiaryController implements Initializable {

    @FXML private ImageView diaryLogo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/Resources/hkrlogo.png"));
        diaryLogo.setImage(image);

    }
    @FXML void measurementUpdate(ActionEvent event) {
        try {
            changeScene("measurementScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML void measurementHistory(ActionEvent event) {
        try {
            changeScene("measurementHistoryScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }


    @FXML void goBack(ActionEvent event) {
        try {
            changeScene("userScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }


    @FXML void myAccountButton(ActionEvent event) {
        try {
            changeScene("accountInfoScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }



    @FXML void dailyUpdate(ActionEvent event) {
        try {
            changeScene("notebookScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }



    @FXML void notebookHistory (ActionEvent event) {
        try {
            changeScene("notebookHistoryScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }


    public void changeScene(String resource, ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/" + resource + ".fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}




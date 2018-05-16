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

    }
    @FXML void measurementUpdate(ActionEvent event) {
        try {
            changeScene("measurementScene", event);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML void measurementHistory(ActionEvent event) {
        try {
            changeScene("measurementHistoryScene", event);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @FXML void goBack(ActionEvent event) {
        try {
            changeScene("loginScene", event);

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
            e.printStackTrace();
        }
    }



    @FXML void notebookHistory (ActionEvent event) {
        try {
            changeScene("notebookHistoryScene", event);

        }catch (IOException e){
            //Fixa error handling
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
            e.printStackTrace();
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            //Fixa error handling
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




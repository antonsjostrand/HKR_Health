package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.AccountInfo;
import Project.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private AccountInfo accInfo, retrievedAccountInfo;
    private ArrayList<AccountInfo> accountInfoList = new ArrayList<>();
    private int[] accList = new int[3];

    @FXML private ImageView buttonPressed;
    @FXML private Button homeButton;
    @FXML private Button exercisesButton;
    @FXML private Button stretchButton;
    @FXML private Button diaryButton;
    @FXML private Button timerButton;
    @FXML private Button nutritionButton;
    @FXML private Button feedbackButton;
    @FXML private Button backButton;
    @FXML private Button saveButton;
    @FXML private Label nameLabel, ssnLabel;
    @FXML private TextField updateHeight, updateWeight, updateAge, updateDate, firstHeight, firstWeight, firstAge;;
    @FXML private TextArea chosenInformationTA;
    @FXML private ListView<AccountInfo> loadedInformationLV;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            nameLabel.setText(UserInformation.getInstance().getCompleteName());
            ssnLabel.setText(UserInformation.getInstance().getSSN());

            accList = DatabaseConnection.getInstance().retrieveAccountInfo(UserInformation.getInstance().getSSN());
            firstHeight.setText(String.valueOf(accList[1]));
            firstWeight.setText(String.valueOf(accList[2]));
            firstAge.setText(String.valueOf(accList[0]));

            chosenInformationTA.setText("|    HEIGHT    |    WEIGHT    |    AGE    |");
            chosenInformationTA.appendText("\n----------------------------------------");

            refreshAndSetInformation();

        }catch (Exception e){
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
            e.printStackTrace();
        }
    }
    @FXML
    void goBack (ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML void myAccountButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/accountInfoScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML void sleepButton(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/sleepScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML
    void saveButtonPressed(ActionEvent event) {
        try {
            DatabaseConnection.getInstance().updateCurrentStatistics(UserInformation.getInstance().getSSN(),
                    UserInformation.getInstance().getUsername(),
                    updateDate.getText(),
                    Integer.parseInt(updateHeight.getText()),
                    Integer.parseInt(updateWeight.getText()),
                    Integer.parseInt(updateAge.getText()));


        } catch (Exception e) {
            e.printStackTrace();
            //Fixa error handling
        }
    }

    @FXML
    void refreshButtonPressed(ActionEvent event) {
        try{
            refreshAndSetInformation();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metod som hämtar, uppdaterar och visar din account information i de olika fönsterna.
    public void refreshAndSetInformation() throws Exception{
        loadedInformationLV.getItems().clear();

        accountInfoList = DatabaseConnection.getInstance().retrieveAllCurrentStatistics(UserInformation.getInstance().getSSN());
        loadedInformationLV.getItems().addAll(accountInfoList);

        loadedInformationLV.setOnMouseClicked(e -> {
            accInfo = loadedInformationLV.getSelectionModel().getSelectedItem();
            retrievedAccountInfo = DatabaseConnection.getInstance().retrieveSpecificStatistics(accInfo.getDate());

            chosenInformationTA.setText("|    HEIGHT    |    WEIGHT    |    AGE    |");
            chosenInformationTA.appendText("\n------------------------------------------");

            String allInformation = "      " + String.valueOf(retrievedAccountInfo.getHeight()) + "             " + String.valueOf(retrievedAccountInfo.getWeight() +
                    "                " + String.valueOf(retrievedAccountInfo.getAge()));

            chosenInformationTA.appendText("\n" + allInformation);
        });
    }
}
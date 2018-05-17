package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Measurement;
import Project.Model.MeasurementTable;
import Project.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MeasurementHistoryController implements Initializable{

    private ObservableList<MeasurementTable> measurementData = FXCollections.observableArrayList();
    private ArrayList<Measurement> measurementList;
    private ArrayList<String> measurementTextList;
    private Measurement retrievedMeasurement;

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ListView<Measurement> loadedMesurementLV;

    @FXML
    private TableView<MeasurementTable> chosenMeasurementTV;

    @FXML
    private TableColumn<MeasurementTable, String> dateTC;

    @FXML
    private TableColumn<MeasurementTable, String> leftUpperArmTC;

    @FXML
    private TableColumn<MeasurementTable, String> rightUpperArmTC;

    @FXML
    private TableColumn<MeasurementTable, String> leftForearmTC;

    @FXML
    private TableColumn<MeasurementTable, String> rightForearmTC;

    @FXML
    private TableColumn<MeasurementTable, String> leftThighTC;

    @FXML
    private TableColumn<MeasurementTable, String> rightThighTC;

    @FXML
    private TableColumn<MeasurementTable, String> leftCalfTC;

    @FXML
    private TableColumn<MeasurementTable, String> rightCalfTC;

    @FXML
    private TableColumn<MeasurementTable, String> waistTC;

    @FXML
    private TableColumn<MeasurementTable, String> shouldersTC;

    @FXML
    private TableColumn<MeasurementTable, String> chestTC;

    @FXML
    private Button homeButton;

    @FXML
    private Button exercisesButton;

    @FXML
    private Button stretchButton;

    @FXML
    private Button timerButton;

    @FXML
    private Button diaryButton;

    @FXML
    private Button nutritionButton;

    @FXML
    private Button feedbackButton;

    @FXML
    private Button backButton;

    @FXML private Button clearButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            measurementList = DatabaseConnection.getInstance().retrieveAllMeasurements(UserInformation.getInstance().getSSN());
            loadedMesurementLV.getItems().addAll(measurementList);

            loadedMesurementLV.setOnMouseClicked(e -> {
                Measurement newMeasurement = loadedMesurementLV.getSelectionModel().getSelectedItem();
                measurementTextList = DatabaseConnection.getInstance().retrieveSpecificMeasurement(UserInformation.getInstance().getSSN(), newMeasurement.getDate());

            if (measurementList != null){

                for (int i = 0; i < measurementList.size(); i = i + 12){
                    MeasurementTable newTable = new MeasurementTable(String.valueOf(measurementTextList.get(i)), String.valueOf(measurementTextList.get(i+1)),
                            String.valueOf(measurementTextList.get(i+2)), String.valueOf(measurementTextList.get(i+3)), String.valueOf(measurementTextList.get(i+4)),
                            String.valueOf(measurementTextList.get(i+5)), String.valueOf(measurementTextList.get(i+6)), String.valueOf(measurementTextList.get(i+7)),
                            String.valueOf(measurementTextList.get(i+8)), String.valueOf(measurementTextList.get(i+9)), String.valueOf(measurementTextList.get(i+10)),
                            String.valueOf(measurementTextList.get(i+11)));

                    measurementData.add(newTable);
                }



                dateTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("dateOfCreation"));
                leftUpperArmTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("upperArmL"));
                rightUpperArmTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("upperArmR"));
                leftForearmTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("forearmL"));
                rightForearmTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("forearmR"));
                leftThighTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("thighL"));
                rightThighTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("thighR"));
                leftCalfTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("calfL"));
                rightCalfTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("calfR"));
                waistTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("waist"));
                shouldersTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("shoulderWidth"));
                chestTC.setCellValueFactory(new PropertyValueFactory<MeasurementTable, String>("chestWidth"));

                chosenMeasurementTV.setItems(measurementData);
            }

            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        try{
            chosenMeasurementTV.getItems().clear();
            measurementTextList.clear();
            measurementData.clear();
           

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }



}

package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.DailyIntake;
import Project.Model.DailyIntakeTable;
import Project.Model.NutritionTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NutritionHistoryController implements Initializable {

    private ObservableList<DailyIntakeTable> dailyIntakeData = FXCollections.observableArrayList();
    private ObservableList<DailyIntakeTable> chosenDailyIntakeData = FXCollections.observableArrayList();
    private ArrayList<DailyIntake> retrievedDailyIntakes;
    private ArrayList<String> chosenDailyIntakes;
    private DailyIntake retrievedDailyIntake;
    private DailyIntakeTable chosenDailyIntake;

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ListView<DailyIntake> loadedDailyIntakeLV;

    @FXML
    private TextArea nameTA;

    @FXML
    private Button clearButton;

    @FXML
    private TextArea gramTA;

    @FXML
    private TableView<DailyIntakeTable> chosenDailyIntakeTV;

    @FXML
    private TableColumn<DailyIntakeTable, String> dateTC;

    @FXML
    private TableColumn<DailyIntakeTable, String> kcalTC;

    @FXML
    private TableColumn<DailyIntakeTable, String> proteinTC;

    @FXML
    private TableColumn<DailyIntakeTable, String> carbsTC;

    @FXML
    private TableColumn<DailyIntakeTable, String> fatTC;

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

    @FXML
    private Button homeButton;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        try{
            chosenDailyIntakeTV.getItems().clear();
            nameTA.clear();
            gramTA.clear();

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            retrievedDailyIntakes = DatabaseConnection.getInstance().retrieveAllDailyIntakes();
            loadedDailyIntakeLV.getItems().addAll(retrievedDailyIntakes);

            loadedDailyIntakeLV.setOnMouseClicked(e -> {
                retrievedDailyIntake = loadedDailyIntakeLV.getSelectionModel().getSelectedItem();

                if (retrievedDailyIntakes != null){
                    DailyIntakeTable newData = new DailyIntakeTable(retrievedDailyIntake.getDate(), String.valueOf(retrievedDailyIntake.getProtein()),
                            String.valueOf(retrievedDailyIntake.getCarbs()), String.valueOf(retrievedDailyIntake.getFat()), String.valueOf(retrievedDailyIntake.getKcal()));

                    dailyIntakeData.addAll(newData);
                }

                dateTC.setCellValueFactory(new PropertyValueFactory<DailyIntakeTable, String>("date"));
                proteinTC.setCellValueFactory(new PropertyValueFactory<DailyIntakeTable, String>("protein"));
                carbsTC.setCellValueFactory(new PropertyValueFactory<DailyIntakeTable, String>("carbs"));
                fatTC.setCellValueFactory(new PropertyValueFactory<DailyIntakeTable, String>("fat"));
                kcalTC.setCellValueFactory(new PropertyValueFactory<DailyIntakeTable, String>("kcal"));

                chosenDailyIntakeTV.setItems(dailyIntakeData);

                chosenDailyIntakeTV.setOnMouseClicked(event -> {
                    nameTA.setText("  PRODUCT  ");
                    gramTA.setText("   GRAM    ");
                    System.out.println(chosenDailyIntakeTV.getSelectionModel().getSelectedIndex());

                    chosenDailyIntakeData = chosenDailyIntakeTV.getItems();

                    chosenDailyIntake = chosenDailyIntakeData.get(chosenDailyIntakeTV.getSelectionModel().getSelectedIndex());
                    chosenDailyIntakes = DatabaseConnection.getInstance().retrieveSpecifcDailyIntakeContents(chosenDailyIntake.getDate());

                    for (int i = 0; i < chosenDailyIntakes.size(); i = i + 2) {
                        nameTA.appendText("\n" + chosenDailyIntakes.get(i));
                        gramTA.appendText("\n" + chosenDailyIntakes.get(i+1));

                    }

                });
            });

        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Your nutrition history are empty");
            alert.setContentText("There is no nothing to read.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
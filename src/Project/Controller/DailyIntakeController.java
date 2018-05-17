package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.DailyIntake;
import Project.Model.Nutrition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DailyIntakeController implements Initializable{

    private ArrayList<String> dailyIntakeNutrition = new ArrayList<>();
    private ArrayList<Nutrition> nutritionList;
    private Nutrition retrievedNutrition;
    private double protein, fat, carbs, kcal, proteinTotal, fatTotal, carbsTotal, kcalTotal, gramTotal, multiplier;
    private String productName;

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ListView<Nutrition> loadedNutritionLV;

    @FXML
    private Button addButton;

    @FXML
    private TextField nutritionNameTF;

    @FXML
    private TextField amountTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextArea intakeTotalTA;

    @FXML
    private TextArea intakeTA;

    @FXML
    private TextArea fatTA;

    @FXML
    private TextArea proteinTA;

    @FXML
    private TextArea carbsTA;

    @FXML
    private TextArea nameTA;

    @FXML
    private TextArea kcalTA;

    @FXML
    private TextArea gramTA;

    @FXML
    private Button saveButton;

    @FXML
    private Button homeButton;

    @FXML private Button searchButton;

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
    void addButtonPressed(ActionEvent event) {
        try{
            loadedNutritionLV.setOnMouseClicked(e -> {
                retrievedNutrition = loadedNutritionLV.getSelectionModel().getSelectedItem();

                protein = retrievedNutrition.getProteinAmount();
                carbs = retrievedNutrition.getCarbohydratesAmount();
                fat = retrievedNutrition.getFatAmount();
                kcal = retrievedNutrition.getKcalPer100();
                productName = retrievedNutrition.getName();

                multiplier = Double.parseDouble(amountTF.getText()) / 100;

                protein = protein * multiplier;
                carbs = carbs * multiplier;
                fat = fat * multiplier;
                kcal = kcal * multiplier;
            });

            if (productName != null) {
                nameTA.appendText("\n" + productName);
                proteinTA.appendText("\n" + String.valueOf(protein));
                carbsTA.appendText("\n" + String.valueOf(carbs));
                fatTA.appendText("\n" + String.valueOf(fat));
                kcalTA.appendText("\n" + String.valueOf(kcal));
                gramTA.appendText("\n" + amountTF.getText());

                proteinTotal = proteinTotal + protein;
                kcalTotal = kcalTotal + kcal;
                fatTotal = fatTotal +fat;
                carbsTotal = carbsTotal + carbs;

                dailyIntakeNutrition.add(productName);
                dailyIntakeNutrition.add(String.valueOf(amountTF.getText()));

                intakeTotalTA.setText("|     PROTEIN     |      CARBS      |      FAT      |      KCAL     |");
                intakeTotalTA.appendText("\n---------------------------------------------------------------");
                intakeTotalTA.appendText("\n          " + proteinTotal + "               " + carbsTotal + "               " + fatTotal + "            " + kcalTotal);

                productName = null;

            }else{
                throw new NullPointerException();
                //Skriv att man måste vara noga med att markera produkten man valt efter man skrivit in hur många gram man ätit
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void saveButtonPressed(ActionEvent event){
        try{
            createDailyIntake(dateTF.getText(), proteinTotal, carbsTotal, fatTotal, kcalTotal, dailyIntakeNutrition);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML void searchButtonPressed(ActionEvent event){
        try{
            nutritionList = DatabaseConnection.getInstance().searchForNutrition(nutritionNameTF.getText());

            loadedNutritionLV.getItems().clear();
            loadedNutritionLV.getItems().addAll(nutritionList);

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
            e.printStackTrace();
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
            intakeTotalTA.setText("   KCAL   |   PROTEIN   |   CARBS   |   FAT   |");
            intakeTotalTA.appendText("\n---------------------------------------------");


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metod som skapar ett dailyintake object som sedan sparas i databasen.
    public void createDailyIntake(String date, double protein, double carbs, double fat, double kcal, ArrayList<String> dailyIntakeNutritionList) {
        try {
            DailyIntake newDailyIntake = new DailyIntake(protein, fat, carbs, kcal, date);
            DatabaseConnection.getInstance().addDailyIntakeToDB(newDailyIntake, dailyIntakeNutritionList);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Daily intake saved.");
            alert.setContentText("Daily intake has been saved to the database.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
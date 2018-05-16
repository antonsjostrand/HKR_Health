package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.NutritionTable;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import java.util.*;

public class NutritionController implements Initializable{

    private ArrayList<String> nutritionList = new ArrayList<>();
    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};

    @FXML
    private Button cancelButton;

    @FXML
    private TextField macroTF;

    @FXML
    private TextField valueFromTF;

    @FXML
    private TextField valueToTF;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<NutritionTable> tableTW;

    @FXML
    private TableView<NutritionTable> searchedTableTW;

    @FXML
    private TableColumn<NutritionTable, String> nameTC;

    @FXML
    private TableColumn<NutritionTable, String> proteinTC;

    @FXML
    private TableColumn<NutritionTable, String> carbTC;

    @FXML
    private TableColumn<NutritionTable, String> fatTC;

    @FXML
    private TableColumn<NutritionTable, String> kcalTC;
    @FXML
    private TableColumn<NutritionTable, String> searchedNameTC;

    @FXML
    private TableColumn<NutritionTable, String> searchedProteinTC;

    @FXML
    private TableColumn<NutritionTable, String> searchedCarbsTC;

    @FXML
    private TableColumn<NutritionTable, String> searchedFatTC;

    @FXML
    private TableColumn<NutritionTable, String> searchedKcalTC;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
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

    @FXML
    void searchButtonPressed(ActionEvent event) {
        try {
            checkIfInputIsEmpty();
            checkMacroFormat(macroTF.getText());
            checkFromToFormat(valueFromTF.getText(), valueToTF.getText());

            nutritionList = DatabaseConnection.getInstance().retrieveSpecificNutrions(macroTF.getText(), valueFromTF.getText(), valueToTF.getText());

            if (nutritionList != null) {

                ObservableList<NutritionTable> nutritionData = FXCollections.observableArrayList();

                for (int i = 0; i < nutritionList.size(); i = i + 5) {

                    NutritionTable newData = new NutritionTable(nutritionList.get(i), nutritionList.get(i + 1), nutritionList.get(i + 2), nutritionList.get(i + 3), nutritionList.get(i + 4));
                    nutritionData.add(newData);
                }

                searchedNameTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("name"));
                searchedProteinTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("protein"));
                searchedCarbsTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("carbs"));
                searchedFatTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("fat"));
                searchedKcalTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("kcal"));

                searchedTableTW.setItems(nutritionData);
            }else{
                throw new ArrayIndexOutOfBoundsException();
            }

        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input empty.");
            alert.setContentText("The input cannot be empty, enter values to be able to search for items.");
            alert.showAndWait();


        }catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input not correct.");
            alert.setContentText("The input doesn't match the requirements.");
            alert.showAndWait();


        }catch (ArrayIndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No items.");
            alert.setContentText("There is no items that match the requirements.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            nutritionList = DatabaseConnection.getInstance().retrieveAllNutritions();

            if (nutritionList != null) {

                ObservableList<NutritionTable> nutritionData = FXCollections.observableArrayList();

                for (int i = 0; i < nutritionList.size(); i = i + 5) {

                    NutritionTable newData = new NutritionTable(nutritionList.get(i), nutritionList.get(i + 1), nutritionList.get(i + 2), nutritionList.get(i + 3), nutritionList.get(i + 4));
                    nutritionData.add(newData);
                }

                nameTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("name"));
                proteinTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("protein"));
                carbTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("carbs"));
                fatTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("fat"));
                kcalTC.setCellValueFactory(new PropertyValueFactory<NutritionTable, String>("kcal"));

                tableTW.setItems(nutritionData);


            } else {
                throw new ArrayIndexOutOfBoundsException();
            }

        }catch  (ArrayIndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No items.");
            alert.setContentText("There is no items in the database.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Kollar så att inputen inte är tom.
    public void checkIfInputIsEmpty(){
        if (macroTF.getText().isEmpty() || valueFromTF.getText().isEmpty() || valueToTF.getText().isEmpty()){
            macroTF.clear();
            valueFromTF.clear();
            valueToTF.clear();
            throw new NullPointerException();
        }
    }

    //Kollar så att formattet på macro är rätt.
    public void checkMacroFormat(String macro){
        if (macro.equals("Carbs") || macro.equals("Protein") || macro.equals("Fat") || macro.equals("Kcal")){
            return;
        }else{
            macroTF.clear();
            macroTF.setText("Protein/Carbs/Fat/Kcal");
            macroTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Kollar så att formattet på from och to är rätt.
    public void checkFromToFormat(String from, String to) {
        for (int i = 0; i < letters.length; i++) {
            if (from.contains(String.valueOf(letters[i]))) {
                valueFromTF.clear();
                valueFromTF.setText("Enter a digit.");
                valueFromTF.requestFocus();
                throw new InputMismatchException();
            }
            if (to.contains(String.valueOf(letters[i]))) {
                valueToTF.clear();
                valueToTF.setText("Enter a digit.");
                valueToTF.requestFocus();
                throw new InputMismatchException();
            }
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
}
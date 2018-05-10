package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Nutrition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javax.xml.crypto.Data;
import java.util.InputMismatchException;

public class AddNutritionController {

    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};
    private char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField kcalTF;

    @FXML
    private TextField proteinTF;

    @FXML
    private TextField carbohydratesTF;

    @FXML
    private TextField fatTF;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/adminScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void createButtonPressed(ActionEvent event){
        try{
            Boolean checkName;

            checkIfInputEmpty();
            checkNameFormat(nameTF.getText());
            checkKcalFormat(kcalTF.getText());
            checkProteinFormat(proteinTF.getText());
            checkCarbsFormat(carbohydratesTF.getText());
            checkFatFormat(fatTF.getText());

            checkName = DatabaseConnection.getInstance().checkNutritionName(nameTF.getText());

            createNutrition(checkName);

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Empty input.");
            alert.setContentText("The textfields cannot be empty, make sure you enter values in all of them.");
            alert.showAndWait();

        }catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Input format incorrect.");
            alert.setContentText("The values entered doesn't correspond with the rules.");
            alert.showAndWait();

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Input format incorrect.");
            alert.setContentText("Make sure you have only entered digits where it's needed.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Kollar så att inget av textfieldsen är tomma.
    public void checkIfInputEmpty(){
        if (nameTF.getText().isEmpty() || kcalTF.getText().isEmpty() || proteinTF.getText().isEmpty()
            || carbohydratesTF.getText().isEmpty() || fatTF.getText().isEmpty()){
            throw new NullPointerException();
        }
    }

    //Kollar formattet på name så det är korrekt.
    //Kollar också så att man inte använder sig av felmeddelandet som namn.
    public void checkNameFormat(String name){
        for (int i = 0; i < numbers.length; i++) {
            if (name.contains(String.valueOf(numbers[i]))) {
                nameTF.clear();
                nameTF.setText("Enter a proper nutrition name.");
                nameTF.requestFocus();
                throw new InputMismatchException();
            }
        }

            if (name.trim().length() == 0){
                nameTF.clear();
                nameTF.setText("Enter a proper nutrition name.");
                nameTF.requestFocus();
                throw new InputMismatchException();
            }

            if (name.equals("Enter a proper nutrition name.")){
                nameTF.clear();
                nameTF.setText("Enter a proper nutrition name.");
                nameTF.requestFocus();
                throw new InputMismatchException();
            }
    }

    //Kollar formattet på kcal så det är korrekt.
    public void checkKcalFormat(String kcal){
        for (int i = 0; i < letters.length; i++){
            if (kcal.contains(String.valueOf(letters))){
                kcalTF.clear();
                kcalTF.setText("Enter a proper kcal value");
                kcalTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Kollar formattet på protein så det är korrekt.
    public void checkProteinFormat(String protein){
        for (int i = 0; i < letters.length; i++){
            if (protein.contains(String.valueOf(letters))){
                proteinTF.clear();
                proteinTF.setText("Enter a proper protein value");
                proteinTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Kollar så att formattet på fat värdet är korrekt.
    public void checkFatFormat(String fat){
        for (int i = 0; i < letters.length; i++){
            if (fat.contains(String.valueOf(letters))){
                fatTF.clear();
                fatTF.setText("Enter a proper fat value");
                fatTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Kollar så att formattet på carbs är korrekt.
    public void checkCarbsFormat(String carbs){
        for (int i = 0; i < letters.length; i++){
            if (carbs.contains(String.valueOf(letters))){
                carbohydratesTF.clear();
                carbohydratesTF.setText("Enter a proper carbohydrate value");
                carbohydratesTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Skapar ett nutrition objekt som sedan lagras i databasen
    public void createNutrition(Boolean checkStatus) throws Exception {
        if (checkStatus == false) {
            Nutrition newNutrition = new Nutrition(nameTF.getText(), Integer.parseInt(kcalTF.getText()), Integer.parseInt(proteinTF.getText()),
                    Integer.parseInt(fatTF.getText()), Integer.parseInt(carbohydratesTF.getText()));

            DatabaseConnection.getInstance().addNutritionToDB(newNutrition);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nutrition");
            alert.setHeaderText("Nutrition creation.");
            alert.setContentText("Nutrition created and stored in the database.");
            alert.showAndWait();

            nameTF.clear();
            kcalTF.clear();
            proteinTF.clear();
            fatTF.clear();
            carbohydratesTF.clear();
        }else{
            nameTF.clear();
            nameTF.setText("Nutrition already exists.");
            nameTF.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Nutrition");
            alert.setContentText("Nutrition already exists.");
            alert.showAndWait();
        }
    }

}

package Project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import Project.DatabaseConnection;
import Project.UserInformation;
import javafx.stage.Stage;

import java.util.InputMismatchException;

public class MeasurementController {

    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};
    private char[] dividers = {'/', '-'};

    @FXML
    private TextField forearmRTF;
    @FXML
    private TextField thighRTF;
    @FXML
    private TextField calfRTF;
    @FXML
    private TextField waistTF;
    @FXML
    private TextField shoulderWidthTF;
    @FXML
    private TextField chestWidthTF;
    @FXML
    private TextField upperArmRTF;
    @FXML
    private TextField upperArmLTF;
    @FXML
    private TextField forearmLTF;
    @FXML
    private TextField thighLTF;
    @FXML
    private TextField calfLTF;
    @FXML
    private Button saveMeasurementButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField dateTF;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
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
    void saveMeasurementButtonPressed(ActionEvent event) {
        try {
            checkIfInputIsEmpty();
            checkForearmFormat(forearmRTF.getText(), forearmLTF.getText());
            checkArmFormat(upperArmRTF.getText(), upperArmLTF.getText());
            checkThighFormat(thighRTF.getText(), thighLTF.getText());
            checkCalvesFormat(calfRTF.getText(), calfLTF.getText());
            checkChestFormat(chestWidthTF.getText());
            checkWaistFormat(waistTF.getText());
            checkShoulderFormat(shoulderWidthTF.getText());
            checkDateFormat(dateTF.getText());

            createMeasurement();

        }catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The values entered is not following the rules.");
            alert.showAndWait();

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The textfields cannot be empty.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metod som kollar formattet på underarmsmåttet.
    public void checkForearmFormat(String forearmR, String forarmL) {
        for (int i = 0; i < letters.length; i++) {
            if (forearmR.contains(String.valueOf(letters[i]))) {
                forearmRTF.clear();
                forearmRTF.setText("Enter a valid digit.");
                forearmRTF.requestFocus();
                throw new InputMismatchException();
            }
        }
        for (int i = 0; i < letters.length; i++) {
            if (forarmL.contains(String.valueOf(letters[i]))) {
                forearmLTF.clear();
                forearmLTF.setText("Enter a valid digit.");
                forearmLTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar formattet på armmåttet.
    public void checkArmFormat(String rightArm, String leftArm) {
        for (int i = 0; i < letters.length; i++) {
            if (rightArm.contains(String.valueOf(letters[i]))) {
                upperArmRTF.clear();
                upperArmRTF.setText("Enter a valid digit.");
                upperArmRTF.requestFocus();
                throw new InputMismatchException();
            }
        }
        for (int i = 0; i < letters.length; i++) {
            if (leftArm.contains(String.valueOf(letters[i]))) {
                upperArmLTF.clear();
                upperArmLTF.setText("Enter a valid digit.");
                upperArmLTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar formattet på lårmåttet.
    public void checkThighFormat(String rightThigh, String leftThigh) {
        for (int i = 0; i < letters.length; i++) {
            if (rightThigh.contains(String.valueOf(letters[i]))) {
                thighRTF.clear();
                thighRTF.setText("Enter a valid digit.");
                thighRTF.requestFocus();
                throw new InputMismatchException();
            }
        }
        for (int i = 0; i < letters.length; i++) {
            if (leftThigh.contains(String.valueOf(letters[i]))) {
                thighLTF.clear();
                thighLTF.setText("Enter a valid digit.");
                thighLTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar formattet på vadmåttet.
    public void checkCalvesFormat(String rightCalf, String leftCalf) {
        for (int i = 0; i < letters.length; i++) {
            if (rightCalf.contains(String.valueOf(letters[i]))) {
                calfRTF.clear();
                calfRTF.setText("Enter a valid digit.");
                calfRTF.requestFocus();
                throw new InputMismatchException();
            }
        }
        for (int i = 0; i < letters.length; i++) {
            if (leftCalf.contains(String.valueOf(letters[i]))) {
                calfLTF.clear();
                calfLTF.setText("Enter a valid digit.");
                calfLTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar formattet på bröstmåttet.
    public void checkChestFormat(String chest) {
        for (int i = 0; i < letters.length; i++) {
            if (chest.contains(String.valueOf(letters[i]))) {
                chestWidthTF.clear();
                chestWidthTF.setText("Enter a valid digit.");
                chestWidthTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar formattet på midjemåttet.
    public void checkWaistFormat(String waist) {
        for (int i = 0; i < letters.length; i++) {
            if (waist.contains(String.valueOf(letters[i]))) {
                waistTF.clear();
                waistTF.setText("Enter a valid digit.");
                waistTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar formattet på axelmåttet.
    public void checkShoulderFormat(String shoulder) {
        for (int i = 0; i < letters.length; i++) {
            if (shoulder.contains(String.valueOf(letters[i]))) {
                shoulderWidthTF.clear();
                shoulderWidthTF.setText("Enter a valid digit.");
                shoulderWidthTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Metod som kollar så att datumet är i korrekt format
    public void checkDateFormat(String date){
        if (date.length() != 8) {
            dateTF.clear();
            dateTF.setText("DD/MM-YY");
            dateTF.requestFocus();
            throw new InputMismatchException();
        }

        for (int counter = 0; counter < letters.length; counter ++){
            if (date.contains(String.valueOf(letters[counter]))){
                dateTF.clear();
                dateTF.setText("DD/MM-YY");
                dateTF.requestFocus();
                throw new InputMismatchException();
            }
        }

        if (date.charAt(2) == '/' || date.charAt(5) == '-'){
                return;
        }else{
                dateTF.clear();
                dateTF.setText("DD/MM-YY");
                dateTF.requestFocus();
                throw new InputMismatchException();
            }
    }

    //Kollar så att ingen input är tom.
    public void checkIfInputIsEmpty(){
        if (upperArmRTF.getText().isEmpty() || upperArmLTF.getText().isEmpty() || forearmRTF.getText().isEmpty() || forearmLTF.getText().isEmpty() ||
            thighLTF.getText().isEmpty() || thighRTF.getText().isEmpty() || calfLTF.getText().isEmpty() || calfRTF.getText().isEmpty() ||
            chestWidthTF.getText().isEmpty() || waistTF.getText().isEmpty() || shoulderWidthTF.getText().isEmpty() || dateTF.getText().isEmpty()){
            throw new NullPointerException();
        }
    }

    //Metod som skapar ett measurement objekt som sedan lagras i databasen.
    public void createMeasurement() {
        try {
            DatabaseConnection.getInstance().updateMeasurementStatistics(
                    Integer.parseInt(upperArmLTF.getText()),
                    Integer.parseInt(upperArmRTF.getText()),
                    Integer.parseInt(forearmLTF.getText()),
                    Integer.parseInt(forearmRTF.getText()),
                    Integer.parseInt(thighLTF.getText()),
                    Integer.parseInt(thighRTF.getText()),
                    Integer.parseInt(calfLTF.getText()),
                    Integer.parseInt(calfRTF.getText()),
                    Integer.parseInt(waistTF.getText()),
                    Integer.parseInt(shoulderWidthTF.getText()),
                    Integer.parseInt(chestWidthTF.getText()),
                    dateTF.getText(),
                    UserInformation.getInstance().getSSN(),
                    UserInformation.getInstance().getUsername(),
                    UserInformation.getInstance().getDiaryID());

            upperArmLTF.clear();
            upperArmRTF.clear();
            forearmLTF.clear();
            forearmRTF.clear();
            thighRTF.clear();
            thighLTF.clear();
            calfLTF.clear();
            calfRTF.clear();
            chestWidthTF.clear();
            shoulderWidthTF.clear();
            waistTF.clear();
            dateTF.clear();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Measurement saved");
            alert.setContentText("Measurement saved successful. Press cancel to go back to the diary scene.");
            alert.showAndWait();

        } catch (Exception e) {

        }
    }
}
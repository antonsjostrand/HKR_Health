package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.AccountInfo;
import Project.UserInformation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};

    private AccountInfo accInfo, retrievedAccountInfo;
    private ArrayList<AccountInfo> accountInfoList = new ArrayList<>();
    private int[] accList = new int[3];

    @FXML
    private ImageView buttonPressed;
    @FXML
    private Button homeButton;
    @FXML
    private Button exercisesButton;
    @FXML
    private Button stretchButton;
    @FXML
    private Button diaryButton;
    @FXML
    private Button timerButton;
    @FXML
    private Button nutritionButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label nameLabel, ssnLabel;
    @FXML
    private TextField updateHeight, updateWeight, updateAge, updateDate, firstHeight, firstWeight, firstAge;
    ;
    @FXML
    private TextArea chosenInformationTA;
    @FXML
    private ListView<AccountInfo> loadedInformationLV;


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

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Update your information");
            alert.setContentText("There is no updated information, please do an update.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void homeButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exerciseButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/exerciseScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void stretchButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/stretchScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void timerButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/timerScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {

        }
    }

    @FXML
    void diaryButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/diaryScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void nutritionButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/nutritionScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void feedbackButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/feedbackScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void myAccountButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/accountInfoScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void sleepButton(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/sleepScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void saveButtonPressed(ActionEvent event) {
        try {
            checkIfInputIsEmpty();
            checkDateFormat(updateDate.getText());
            checkHeightFormat(updateHeight.getText());
            checkWeightFormat(updateWeight.getText());
            checkAgeFormat(updateAge.getText());

            DatabaseConnection.getInstance().updateCurrentStatistics(UserInformation.getInstance().getSSN(),
                    UserInformation.getInstance().getUsername(),
                    updateDate.getText(),
                    Integer.parseInt(updateHeight.getText()),
                    Integer.parseInt(updateWeight.getText()),
                    Integer.parseInt(updateAge.getText()));

            updateDate.clear();
            updateHeight.clear();
            updateWeight.clear();
            updateAge.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Update saved.");
            alert.setContentText("Your new update has been saved to the database.");
            alert.showAndWait();


        }catch (InputMismatchException e) {
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

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Date already saved.");
            alert.setContentText("You can only update once per date.");
            alert.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refreshButtonPressed(ActionEvent event) {
        try {
            refreshAndSetInformation();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty");
            alert.setContentText("There is no updated info yet.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metod som hämtar, uppdaterar och visar din account information i de olika fönsterna.
    public void refreshAndSetInformation() throws Exception {
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

    //Kollar så att ingen input är tom.
    public void checkIfInputIsEmpty() {
        if (updateHeight.getText().isEmpty() || updateWeight.getText().isEmpty() ||
                updateDate.getText().isEmpty() || updateAge.getText().isEmpty()) {
            throw new NullPointerException();
        }
    }

    //Metod som kollar så att datumet är i korrekt format
    public void checkDateFormat(String date) {
        if (date.length() != 8) {
            updateDate.clear();
            updateDate.setText("DD/MM-YY");
            updateDate.requestFocus();
            throw new InputMismatchException();
        }

        if ((date.charAt(1) == '0' && date.charAt(0) == '0')|| (date.charAt(3) == '0' &&  date.charAt(4) == '0')) {
            updateDate.clear();
            updateDate.setText("DD/MM-YY");
            updateDate.requestFocus();
            throw new InputMismatchException();
        }

        for (int counter = 0; counter < letters.length; counter++) {
            if (date.contains(String.valueOf(letters[counter]))) {
                updateDate.clear();
                updateDate.setText("DD/MM-YY");
                updateDate.requestFocus();
                throw new InputMismatchException();
            }
        }

        if (date.charAt(2) == '/' || date.charAt(5) == '-') {
            return;
        } else {
            updateDate.clear();
            updateDate.setText("DD/MM-YY");
            updateDate.requestFocus();
            throw new InputMismatchException();
        }
    }

         //Kollar så height är i rätt format.
    public void checkHeightFormat(String height) {
        for (int i = 0; i < letters.length; i++) {
            if (height.contains(String.valueOf(letters[i]))) {
                updateHeight.clear();
                updateHeight.setText("Enter a valid digit.");
                updateHeight.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

          //Kollar så weight är i rätt format.
    public void checkWeightFormat(String weight) {
        for (int i = 0; i < letters.length; i++) {
            if (weight.contains(String.valueOf(letters[i]))) {
                updateWeight.clear();
                updateWeight.setText("Enter a valid digit.");
                updateWeight.requestFocus();
                throw new InputMismatchException();

            }
        }
    }

           //Kollar så att age är i rätt format.
    public void checkAgeFormat(String age) {
        for (int i = 0; i < letters.length; i++) {
            if (age.contains(String.valueOf(letters[i]))) {
                updateAge.clear();
                updateAge.setText("Enter a valid digit.");
                updateAge.requestFocus();
                throw new InputMismatchException();
            }
        }
    }
}
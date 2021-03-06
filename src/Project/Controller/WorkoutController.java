package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Exercise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class WorkoutController {

    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};

    private Exercise chosenExercise;
    private ArrayList<String> chosenExerciseInformation = new ArrayList<>();

    @FXML
    private Label exerciseNameLabel;

    @FXML
    private ListView<Exercise> exerciseLV;

    @FXML
    private Button saveButton;

    @FXML
    private MenuButton muscleGroupMenu;

    @FXML
    private MenuItem chestMenu;

    @FXML
    private MenuItem shouldersMenu;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem bicepsMenu;

    @FXML
    private MenuItem tricepsMenu;

    @FXML
    private MenuItem gluteusMenu;

    @FXML
    private MenuItem quadMenu;

    @FXML
    private MenuItem hamstringMenu;

    @FXML
    private MenuItem calvesMenu;

    @FXML
    private MenuItem absMenu;

    @FXML
    private TextField setsTF;

    @FXML
    private TextField repsTF;

    @FXML
    private TextField weightTF;

    @FXML
    private TextField dateTF;

    @FXML
    private Button addButton;

    @FXML
    private TextArea exerciseTA;

    @FXML
    private TextArea setsTA;

    @FXML
    private TextArea repsTA;

    @FXML
    private TextArea weightTA;

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
    void saveButtonPressed(ActionEvent event) {
        try {
            //Kolla så att ingen input är empty inklusive arraylistan. detta för att man inte ska kunna spara tomma workouts.
            checkIfInputIsEmptyBeforeSave();
            checkDateFormat(dateTF.getText());

            DatabaseConnection.getInstance().addWorkoutToDB(chosenExerciseInformation, dateTF.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Workout saved.");
            alert.setContentText("Your workout has been saved to the database.");
            alert.showAndWait();

            dateTF.clear();
            exerciseTA.clear();
            setsTA.clear();
            repsTA.clear();
            weightTA.clear();

        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Date incorrect.");
            alert.setContentText("Enter a valid date.");
            alert.showAndWait();

            dateTF.clear();
            dateTF.setText("DD/MM-YY");
            dateTF.requestFocus();

        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The textfields cannot be empty.");
            alert.showAndWait();

        }catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("Make sure the date format is correct.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addButtonPressed(ActionEvent event) {
        try {
            checkIfInputIsEmpty(setsTF.getText(), repsTF.getText(), weightTF.getText());
            checkSetRepWeightFormat(setsTF.getText(), repsTF.getText(), weightTF.getText());

            exerciseTA.appendText(chosenExercise.getName() + "\n");
            setsTA.appendText(setsTF.getText() + "\n");
            repsTA.appendText(repsTF.getText() + "\n");
            weightTA.appendText(weightTF.getText() + "\n");

            chosenExerciseInformation.add(chosenExercise.getName());
            chosenExerciseInformation.add(repsTF.getText());
            chosenExerciseInformation.add(setsTF.getText());
            chosenExerciseInformation.add(weightTF.getText());

            repsTF.clear();
            setsTF.clear();
            weightTF.clear();

        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The textfields cannot be empty.");
            alert.showAndWait();

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("Only numbers is allowed.");
            alert.showAndWait();

        }catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input incorrect");
            alert.setContentText("The input is not correct.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void backMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Back");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();

            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

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
    void goBack(ActionEvent event) {
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
    void bicepsMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Biceps");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void calvesMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Calves");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void chestMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Chest");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gluteusMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Gluteus");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void hamstringMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Hamstrings");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void muscleGroupMenuPressed(ActionEvent event) {

    }

    @FXML
    void quadMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Quadriceps");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void shouldersMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Shoulders");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tricepsMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Triceps");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void absMenuPressed(ActionEvent event) {
        try {
            ArrayList<Exercise> exerciseList = DatabaseConnection.getInstance().retrieveExercise("Abs");
            exerciseLV.getItems().clear();
            exerciseLV.getItems().addAll(exerciseList);

            exerciseLV.setOnMouseClicked(e -> {
                chosenExercise = exerciseLV.getSelectionModel().getSelectedItem();


            });
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty exercise register.");
            alert.setContentText("There is no exercises for this muscle group in the database.");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (Exception e) {
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
            e.printStackTrace();
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
            e.printStackTrace();
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

    //Kollar så att ingen input är tom innan man lägger till.
    public void checkIfInputIsEmpty(String sets, String reps, String weight) {
        if (setsTF.getText().isEmpty() || repsTF.getText().isEmpty() || weightTF.getText().isEmpty() || exerciseLV.getItems().isEmpty()) {
            throw new NullPointerException();
        }
        if (sets.trim().length() == 0) {
            setsTF.clear();
            setsTF.setText("Enter a digit.");
            setsTF.requestFocus();
            throw new InputMismatchException();
        }
        if (reps.trim().length() == 0) {
            repsTF.clear();
            repsTF.setText("Enter a digit.");
            repsTF.requestFocus();
            throw new InputMismatchException();
        }
        if (weight.trim().length() == 0) {
            weightTF.clear();
            weightTF.setText("Enter a digit.");
            weightTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Kollar så att ingen input är tom innan man sparar.
    public void checkIfInputIsEmptyBeforeSave() {
        if (exerciseTA.getText().isEmpty() || setsTA.getText().isEmpty() || repsTA.getText().isEmpty() ||
                weightTA.getText().isEmpty() || dateTF.getText().isEmpty() ||
                exerciseLV.getItems().isEmpty()) {
            throw new NullPointerException();
        }
    }

    //Metod som kollar så att datumet är i korrekt format
    public void checkDateFormat(String date) {
        if (date.length() != 8) {
            dateTF.clear();
            dateTF.setText("DD/MM-YY");
            dateTF.requestFocus();
            throw new InputMismatchException();
        }

        if ((date.charAt(1) == '0' && date.charAt(0) == '0')|| (date.charAt(3) == '0' &&  date.charAt(4) == '0')) {
            dateTF.clear();
            dateTF.setText("DD/MM-YY");
            dateTF.requestFocus();
            throw new InputMismatchException();
        }

        for (int counter = 0; counter < letters.length; counter++) {
            if (date.contains(String.valueOf(letters[counter]))) {
                dateTF.clear();
                dateTF.setText("DD/MM-YY");
                dateTF.requestFocus();
                throw new InputMismatchException();
            }
        }

        if (date.charAt(2) == '/' || date.charAt(5) == '-') {
            return;
        }
        else {
            dateTF.clear();
            dateTF.setText("DD/MM-YY");
            dateTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Metod som kollar så att formattet på sets/reps/Weight är korrekt.
    public void checkSetRepWeightFormat(String set, String rep, String weight){
        for (int i = 0; i < letters.length; i++){
            if (set.contains(String.valueOf(letters[i]))){
                setsTF.clear();
                setsTF.setText("Enter an digit.");
                setsTF.requestFocus();
                throw new NumberFormatException();
            }
        }
        for (int i = 0; i < letters.length; i++){
            if (rep.contains(String.valueOf(letters[i]))){
                repsTF.clear();
                repsTF.setText("Enter an digit.");
                repsTF.requestFocus();
                throw new NumberFormatException();
            }
        }
        for (int i = 0; i < letters.length; i++){
            if (weight.contains(String.valueOf(letters[i]))){
                weightTF.clear();
                weightTF.setText("Enter an digit.");
                weightTF.requestFocus();
                throw new NumberFormatException();
            }
        }
    }
}

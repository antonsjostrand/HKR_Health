package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Exercise;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.InputMismatchException;

public class AddExerciseController {

    private char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @FXML private Button cancelButton;
    @FXML private Button createButton;
    @FXML private TextField nameTF;
    @FXML private TextField typeTF;
    @FXML private TextField muscleOneTF;
    @FXML private TextField muscleTwoTF;
    @FXML private TextField muscleThreeTF;
    @FXML private TextField muscleFourTF;
    @FXML private TextArea instructionTA;
    @FXML private TextField pathTF;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try{
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
        try {
            checkIfInputEmtpy();
            checkNameFormat(nameTF.getText());
            checkTypeFormat(typeTF.getText());
            checkMuscleGroupFormat(muscleOneTF.getText(), muscleTwoTF.getText(), muscleThreeTF.getText(), muscleFourTF.getText());
            checkInstructionFormat(instructionTA.getText());

            createExercise();

        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Empty input.");
            alert.setContentText("The first four fields and instruction field cannot be empty.");
            alert.showAndWait();

        }catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Input format incorrect.");
            alert.setContentText("The values entered doesn't correspond with the rules.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metod som kollar så att formatet på övningsnamnet är korrekt.
    //Kollar också så att man inte använder sig av felmeddelandet som namn.
    public void checkNameFormat(String name) {
        for (int i = 0; i < numbers.length; i++) {
            if (name.contains(String.valueOf(numbers[i]))) {
                nameTF.clear();
                nameTF.setText("Enter a proper exercise name.");
                nameTF.requestFocus();
                throw new InputMismatchException();
            }
        }

            if (name.trim().length() == 0){
                nameTF.clear();
                nameTF.setText("Enter a proper exercise name.");
                nameTF.requestFocus();
                throw new InputMismatchException();
            }

            if (name.equals("Enter a proper exercise name.")){
                nameTF.clear();
                nameTF.setText("Enter a proper exercise name.");
                nameTF.requestFocus();
                throw new InputMismatchException();
            }
        }


    //Metod som kollar så att formatet på övningen är korret.
    public void checkTypeFormat(String type){
        if (type.equals("Stretch") || type.equals("Strength")) {
            return;
        }else{
            typeTF.clear();
            typeTF.setText("Enter the correct type.");
            typeTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Metod som kollar så att muskelgrupperna man angett har ett korrekt format.
    //Kollar också så att man inte använder sig av felmeddelandet som namn.
    public void checkMuscleGroupFormat(String muscleOne, String muscleTwo, String muscleThree, String muscleFour){
        for (int i = 0; i < numbers.length; i++) {
            if (muscleOne.contains(String.valueOf(numbers[i]))) {
                muscleOneTF.clear();
                muscleOneTF.setText("Enter a proper musclegroup.");
                muscleOneTF.requestFocus();
                throw new InputMismatchException();
            }else if (muscleTwo.contains(String.valueOf(numbers[i]))){
                muscleTwoTF.clear();
                muscleTwoTF.setText("Enter a proper musclegroup.");
                muscleTwoTF.requestFocus();
                throw new InputMismatchException();
            }else if (muscleThree.contains(String.valueOf(numbers[i]))){
                muscleThreeTF.clear();
                muscleThreeTF.setText("Enter a proper musclegroup.");
                muscleThreeTF.requestFocus();
                throw new InputMismatchException();
            }else if (muscleFour.contains(String.valueOf(numbers[i]))){
                muscleFourTF.clear();
                muscleFourTF.setText("Enter a proper musclegroup.");
                muscleFourTF.requestFocus();
                throw new InputMismatchException();
            }
        }

        if (muscleOne.trim().length() == 0){
            muscleOneTF.clear();
            muscleOneTF.setText("Enter a proper muscle group.");
            muscleOneTF.requestFocus();
            throw new InputMismatchException();
        }
        if (muscleTwo.trim().length() == 0){
            muscleTwoTF.clear();

        }
        if (muscleThree.trim().length() == 0){
            muscleThreeTF.clear();

        }
        if (muscleFour.trim().length() == 0){
            muscleFourTF.clear();

        }
        if (muscleTwo.equals("Enter a proper muscle group.")){
            muscleTwoTF.clear();
            muscleTwoTF.setText("Enter a proper muscle group.");
            muscleTwoTF.requestFocus();
            throw new InputMismatchException();
        }
        if (muscleThree.equals("Enter a proper muscle group.")){
            muscleThreeTF.clear();
            muscleThreeTF.setText("Enter a proper muscle group.");
            muscleThreeTF.requestFocus();
            throw new InputMismatchException();
        }
        if (muscleFour.equals("Enter a proper muscle group.")){
            muscleFourTF.clear();
            muscleFourTF.setText("Enter a proper muscle group.");
            muscleFourTF.requestFocus();
            throw new InputMismatchException();
        }
        if (muscleOne.equals("Enter a proper muscle group.")){
            muscleOneTF.clear();
            muscleOneTF.setText("Enter a proper muscle group.");
            muscleOneTF.requestFocus();
            throw new InputMismatchException();
        }
        }


    //Metod som kollar så att storleken på instruktionen inte är för stor för att sparas i DB.
    public void checkInstructionFormat(String instruction){
        if (instruction.length() < 1 || instruction.length() > 255){
            instructionTA.requestFocus();
            throw new InputMismatchException();
        }
        if (instruction.trim().length() == 0){
            instructionTA.requestFocus();
            throw new NullPointerException();
        }
    }

    //Metod som kollar så att inputen inte är tom.
    //De enda som får vara tomma är muskelgrupp 2, 3 och 4 då det inte alltid behövs.
    public void checkIfInputEmtpy(){
        if (nameTF.getText().isEmpty() || typeTF.getText().isEmpty() || muscleOneTF.getText().isEmpty() || instructionTA.getText().isEmpty() || pathTF.getText().isEmpty()){
            throw new NullPointerException();
        }
    }

    //Metod som skapar ett exercise objekt.
    public void createExercise() throws Exception{
        if (muscleTwoTF.getText().isEmpty() && muscleThreeTF.getText().isEmpty() && muscleFourTF.getText().isEmpty()){
            Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                    muscleOneTF.getText());

            DatabaseConnection.getInstance().addExerciseToDB(newExercise);
        }else if (muscleThreeTF.getText().isEmpty() && muscleFourTF.getText().isEmpty()){
            Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                    muscleOneTF.getText(), muscleTwoTF.getText());

            DatabaseConnection.getInstance().addExerciseToDB(newExercise);
        }else if (muscleFourTF.getText().isEmpty()){
            Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                    muscleOneTF.getText(), muscleTwoTF.getText(), muscleThreeTF.getText());

            DatabaseConnection.getInstance().addExerciseToDB(newExercise);
        }else{
            Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                    muscleOneTF.getText(), muscleTwoTF.getText(), muscleThreeTF.getText(), muscleFourTF.getText());

            DatabaseConnection.getInstance().addExerciseToDB(newExercise);
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exercise.");
        alert.setHeaderText("Exercise creation.");
        alert.setContentText("Exercise created and stored in the database.");
        alert.showAndWait();

        nameTF.clear();
        typeTF.clear();
        instructionTA.clear();
        pathTF.clear();
        muscleOneTF.clear();
        muscleTwoTF.clear();
        muscleThreeTF.clear();
        muscleFourTF.clear();
    }


}

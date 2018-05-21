package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Exercise;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

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
    private MenuButton muscleGroupMenu;

    @FXML
    private MenuItem chestMenuOne;

    @FXML
    private MenuItem shouldersMenuOne;

    @FXML
    private MenuItem backMenuOne;

    @FXML
    private MenuItem bicepsMenuOne;

    @FXML
    private MenuItem tricepsMenuOne;

    @FXML
    private MenuItem gluteusMenuOne;

    @FXML
    private MenuItem quadricepsMenuOne;

    @FXML
    private MenuItem hamstringsMenuOne;

    @FXML
    private MenuItem calvesMenuOne;

    @FXML
    private MenuItem absMenuOne;

    @FXML
    private MenuItem fullBodyMenu;

    @FXML private MenuItem upperBodyMenu;

    @FXML private MenuItem lowerBodyMenu;

    @FXML
    private Label muscleGroupOneLabel;

    @FXML
    private Label muscleGroupTwoLabel;

    @FXML
    private Label muscleGroupThreeLabel;

    @FXML
    private Label muscleGroupFourLabel;

    @FXML
    void absMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Abs");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void backMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Back");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void bicepsMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Biceps");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void calvesMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Calves");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void chestMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Chest");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void gluteusMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Gluteus");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void hamstringsMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Hamstrings");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void quadricepsMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Quadriceps");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void shouldersMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Shoulders");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void tricepsMenuOnePressed(ActionEvent event) {
        try{
            fillMusclegroupLabels("Triceps");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void fullBodyMenuPressed(ActionEvent event){
        try{
            fillMusclegroupLabels("Full body");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void lowerBodyMenuPressed(ActionEvent event){
        try{
            fillMusclegroupLabels("Lower body");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void upperBodyMenuPressed(ActionEvent event){
        try{
            fillMusclegroupLabels("Upper body");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
            Boolean checkName;

            checkIfInputEmtpy();
            checkNameFormat(nameTF.getText());
            checkTypeFormat(typeTF.getText());
            checkInstructionFormat(instructionTA.getText());

            checkName = DatabaseConnection.getInstance().checkExerciseName(nameTF.getText());


            createExercise(checkName);

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
        if (nameTF.getText().isEmpty() || typeTF.getText().isEmpty() || muscleGroupOneLabel.getText().isEmpty() || instructionTA.getText().isEmpty() || pathTF.getText().isEmpty()){
            throw new NullPointerException();
        }
    }

    //Metod som skapar ett exercise objekt.
    public void createExercise(Boolean nameStatus) throws Exception{
        if (nameStatus == false) {
            if (muscleGroupTwoLabel.getText().isEmpty() && muscleGroupThreeLabel.getText().isEmpty() && muscleGroupFourLabel.getText().isEmpty()) {

                Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                        muscleGroupOneLabel.getText());
                DatabaseConnection.getInstance().addExerciseToDB(newExercise);

            } else if (muscleGroupThreeLabel.getText().isEmpty() && muscleGroupFourLabel.getText().isEmpty()) {

                Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                        muscleGroupOneLabel.getText(), muscleGroupTwoLabel.getText());
                DatabaseConnection.getInstance().addExerciseToDB(newExercise);

            } else if (muscleGroupFourLabel.getText().isEmpty()) {
                Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                        muscleGroupOneLabel.getText(), muscleGroupTwoLabel.getText(), muscleGroupThreeLabel.getText());

                DatabaseConnection.getInstance().addExerciseToDB(newExercise);

            } else {
                Exercise newExercise = new Exercise(nameTF.getText(), typeTF.getText(), instructionTA.getText(), pathTF.getText(),
                        muscleGroupOneLabel.getText(), muscleGroupTwoLabel.getText(), muscleGroupThreeLabel.getText(), muscleGroupFourLabel.getText());

                DatabaseConnection.getInstance().addExerciseToDB(newExercise);
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Exercise creation.");
            alert.setContentText("Exercise created and stored in the database.");
            alert.showAndWait();

            nameTF.clear();
            typeTF.clear();
            instructionTA.clear();
            pathTF.clear();
            muscleGroupOneLabel.setText("");
            muscleGroupTwoLabel.setText("");
            muscleGroupThreeLabel.setText("");
            muscleGroupFourLabel.setText("");
        }else{
            nameTF.clear();
            nameTF.setText("Exercise already exists.");
            nameTF.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Exercise");
            alert.setContentText("Exercise already exists.");
            alert.showAndWait();
        }
    }

    //Metod som fyller ut musclegroup labels
    public void fillMusclegroupLabels(String muscle){
        if (muscleGroupOneLabel.getText().isEmpty()){
            muscleGroupOneLabel.setText(muscle);
        } else if (muscleGroupTwoLabel.getText().isEmpty()){
            muscleGroupTwoLabel.setText(muscle);
        } else if (muscleGroupThreeLabel.getText().isEmpty()){
            muscleGroupThreeLabel.setText(muscle);
        } else if (muscleGroupFourLabel.getText().isEmpty()){
            muscleGroupFourLabel.setText(muscle);
        }
    }

}

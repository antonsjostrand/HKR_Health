package Project.Model;

import Project.DatabaseConnection;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Exercise {

    private String name, typeOfExercise, instruction, imagePath;
    private String[] muscleGroup;

    //Skapa en mapp som kommer innehålla alla bilder! Sen sparar vi en övnings bilds path (imagePath)
    //till det övningsobjektet den tillhör så det kan lagras i DB

    public Exercise(String name, String typeOfExercise,String instruction, String imagePath,String... muscleGroup) {
        try {
            if (typeOfExercise.equals("Stretch") || typeOfExercise.equals("Strength")) {
                this.name = name;
                this.typeOfExercise = typeOfExercise;
                this.instruction = instruction;
                this.imagePath = imagePath;
                this.muscleGroup = muscleGroup;
            }else{
                throw new InputMismatchException();
            }
        }catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exercise");
            alert.setHeaderText("Creation error");
            alert.setContentText("The input values didn't meet the requirements.");
            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exercise");
            alert.setHeaderText("Creation error");
            alert.setContentText("Failed to create the new exercise.");
            alert.showAndWait();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public String[] getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String[] muscleGroup) {
        this.muscleGroup = new String[4];
        this.muscleGroup = muscleGroup;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return getName();
    }
}
package Project.Model;

import javafx.scene.control.Alert;

import java.util.InputMismatchException;

public class Exercise {

    private String name, typeOfExercise, muscleGroup, instruction;
    private int exerciseID = 0;

    public Exercise(String name, String typeOfExercise, String muscleGroup, String instruction) {
        try {
            if (typeOfExercise.equals("Stretch") || typeOfExercise.equals("Strength")) {
                this.name = name;
                this.typeOfExercise = typeOfExercise;
                this.muscleGroup = muscleGroup;
                this.instruction = instruction;
                this.exerciseID = exerciseID++;
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

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getExerciseID() {
        return exerciseID;
    }

}
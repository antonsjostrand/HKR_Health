package Project;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ExerciseInformation {

    private static ExerciseInformation ourExercise;

    private String name, type, instruction, imagePath;
    private ArrayList<String> muscleGroupList = new ArrayList<>();

    public static ExerciseInformation getInstance() {
        try{
            if (ourExercise == null){
                ourExercise = new ExerciseInformation();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Information");
            alert.setHeaderText("Creation of object");
            alert.setContentText("Failed to create object of class: ExerciseInformation");
            alert.showAndWait();
        }
        return ourExercise;
    }

    private ExerciseInformation() {
    }
}

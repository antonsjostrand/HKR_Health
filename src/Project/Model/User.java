package Project.Model;

import Project.DatabaseConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class User extends Person implements Initializable {

    private static int userIDCounter = DatabaseConnection.getInstance().getUserID();
    private String startWeight, height;
    private int maxLift, userID = 0;

    public User(String firstName, String lastName, String password, String SSN, int age, String startWeight, String height, int maxLift) {
            super(firstName, lastName, password, SSN, age);
        try{
            userIDCounter++;
            this.startWeight = startWeight;
            this.height = height;
            this.maxLift = maxLift;
            this.userID = userIDCounter;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User");
            alert.setHeaderText("Creation error");
            alert.setContentText("Failed to create the new user.");
            alert.showAndWait();
        }
    }

    public String getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(String startWeight) {
        this.startWeight = startWeight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getMaxLift() {
        return maxLift;
    }

    public void setMaxLift(int maxLift) {
        this.maxLift = maxLift;
    }

    public int getUserID(){
        return userID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

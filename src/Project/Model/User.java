package Project.Model;

import Project.DatabaseConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class User extends Person implements Initializable {

    private String startWeight, height, username;


    public User(String username, String firstName, String lastName, String password, String SSN, int age, String startWeight, String height) {
            super(firstName, lastName, password, SSN, age);
        try{
            this.username = username;
            this.startWeight = startWeight;
            this.height = height;
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

    public String getUsername(){ return username; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

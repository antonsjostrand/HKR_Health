package Project.Model;

import Project.DatabaseConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class User extends Person implements Initializable {

    private String username;
    private int  startWeight, height;


    public User(String username, String firstName, String lastName, String password, String email, String SSN, int age, int startWeight, int height) {
            super(firstName, lastName, password, email, SSN, age);
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

    public int getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(int startWeight) {
        this.startWeight = startWeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUsername(){ return username; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void test(){}
}

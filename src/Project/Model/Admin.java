package Project.Model;

import javafx.scene.control.Alert;

public class Admin extends Person {

    private int adminID = 0;

    public Admin(String firstName, String lastName, String password, String email, String SSN, int age) {
        super(firstName, lastName, password, email, SSN, age);
        try{
            this.adminID = adminID++;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin");
            alert.setHeaderText("Creation error");
            alert.setContentText("Failed to create the new admin.");
            alert.showAndWait();
        }
    }

    public int getAdminID(){
        return adminID;
    }
}

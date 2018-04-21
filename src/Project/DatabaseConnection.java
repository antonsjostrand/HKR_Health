package Project;

import Project.Model.Admin;
import Project.Model.Exercise;
import Project.Model.Nutrition;
import Project.Model.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private static DatabaseConnection ourDBC;
    private String url = "jdbc:mysql://127.0.0.1:3306/hkr_health?user=root&password=root";
    private Statement st;

    public static DatabaseConnection getInstance() {
        try {
            if (ourDBC == null) {
                ourDBC = new DatabaseConnection();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection");
            alert.setHeaderText("Creation of object");
            alert.setContentText("Failed to create object of class: DatabaseConnection");
            alert.showAndWait();
        }
        return ourDBC;
    }


    private DatabaseConnection(){

    }

    //Skapar en koppling till databasen.
    public void connectToDB(){
        try{
            Connection c = DriverManager.getConnection(url);
            st = c.createStatement();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database connection");
            alert.setHeaderText("Connection failure");
            alert.setContentText("Failed to connect to the desired database.");
            alert.showAndWait();
        }
    }


    public void addUserToDB(User user){

    }

    public void addExerciseToDB(Exercise exercise){

    }

    public void addNutritionToDB(Nutrition nutrition){

    }

    public void addAdminToDB(Admin admin){

    }
}
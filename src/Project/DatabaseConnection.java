package Project;

import Project.Model.Admin;
import Project.Model.Exercise;
import Project.Model.Nutrition;
import Project.Model.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static DatabaseConnection ourDBC;
    private String url = "jdbc:mysql://127.0.0.1:3306/hkr_health?user=root&password=root";
    private Statement st;

    //Skapar ett objekt om det inte redan finns något när man man kallar på metoden.
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

    //Används ej.
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
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addExerciseToDB(Exercise exercise){

    }

    public void addNutritionToDB(Nutrition nutrition){

    }

    public void addAdminToDB(Admin admin){

    }
    
    //Metod som ska hämta det största userIDet för att man ska kunna skapa nya konton som fortsätter där man slutade
    //Om man slutade på userid 1000, så skall metoden hämta 1000 och nästa konto man hämtar får 1001.
    public int getUserID(){
        return 10;
    }
}
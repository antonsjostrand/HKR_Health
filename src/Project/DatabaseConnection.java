package Project;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private String url = "jdbc:mysql://127.0.0.1:3306/hkr_health?user=root&password=root";
    private Statement st;

    public DatabaseConnection(){
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


    //Skapa en metod som stänger databas-connectionen
    //Skapa en metod som addar nya users till databasen.
    //Skapa en metod som addar nya övningar till databasen.
}
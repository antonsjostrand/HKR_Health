package Project;

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
            //Skapa error ifall det inte gick att skapa ett objekt av databaseconnection
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


    public void
    //Skapa en metod som addar nya övningar till databasen.
}
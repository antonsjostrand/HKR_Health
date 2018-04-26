package Project;

import Project.Model.Admin;
import Project.Model.Exercise;
import Project.Model.Nutrition;
import Project.Model.User;
import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection ourDBC;
    private String url = "jdbc:mysql://127.0.0.1:3306/hkr_health?user=root&password=root";
    private Statement st, statement;

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
            statement = c.createStatement();

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
            st.executeUpdate("INSERT INTO person (firstName, lastName, password, age, SSN) " +
                    "VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() +
                            "', '" + user.getAge() + "', '" + user.getSSN() + "');");

            statement.executeUpdate("INSERT INTO user (height, startWeight, Person_SSN, username) " +
                    "VALUES ('" + user.getHeight() + "', '" + user.getStartWeight() + "', '" + user.getSSN() + "', '" +
                    user.getUsername() + "');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkUserName(String username) throws Exception {
        ResultSet rs = st.executeQuery("SELECT username FROM user WHERE username = '" + username + "'");
        if (rs.next()) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkSSN(String SSN) throws Exception {
        ResultSet rs = st.executeQuery("SELECT SSN FROM person WHERE SSN = '" + SSN + "'");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void addExerciseToDB(Exercise exercise){

    }

    public void addNutritionToDB(Nutrition nutrition){

    }

    public void addAdminToDB(Admin admin){

    }

}
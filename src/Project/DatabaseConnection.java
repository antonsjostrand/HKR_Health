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
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection");
            alert.setHeaderText("Creation of object");
            alert.setContentText("Failed to create object of class: DatabaseConnection");
            alert.showAndWait();
        }
        return ourDBC;
    }

    //Används ej.
    private DatabaseConnection() {

    }

    //Skapar en koppling till databasen.
    public void connectToDB() {
        try {
            Connection c = DriverManager.getConnection(url);
            st = c.createStatement();
            statement = c.createStatement();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database connection");
            alert.setHeaderText("Connection failure");
            alert.setContentText("Failed to connect to the desired database.");
            alert.showAndWait();
        }
    }

    //Addar en ny användare till databasen.
    public void addUserToDB(User user) {
        try {
            st.executeUpdate("INSERT INTO person (firstName, lastName, password, age, email, SSN) " +
                    "VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() +
                    "', '" + user.getAge() + "', '" + user.getEmail() + "', '" + user.getSSN() + "');");

            statement.executeUpdate("INSERT INTO user (height, startWeight, Person_SSN, username) " +
                    "VALUES ('" + user.getHeight() + "', '" + user.getStartWeight() + "', '" + user.getSSN() + "', '" +
                    user.getUsername() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Kontrollerar om ett användarnamn finns i databasen.
    public boolean checkUserNameDB(String username) throws Exception {
        ResultSet rs = st.executeQuery("SELECT username FROM user WHERE username = '" + username + "'");
        if (rs.next()) {
            return true;
        }
        return false;
    }

    //Kontrollerar om ett personnummer finns i databasen.
    public boolean checkSSNDB(String SSN) throws Exception {
        ResultSet rs = st.executeQuery("SELECT SSN FROM person WHERE SSN = '" + SSN + "'");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void addExerciseToDB(Exercise exercise) throws Exception {
        st.executeUpdate("INSERT INTO exercise (exerciseID, typeOfExercise, instruction, name, imagePath) " +
                              "VALUES ('" + exercise.getExerciseID() + "', '" + exercise.getTypeOfExercise() + "', '" +
                               exercise.getInstruction() + "', '" + exercise.getName() + "', '" + exercise.getImagePath() + "');");
    }

    public void addNutritionToDB(Nutrition nutrition) {

    }

    public void addAdminToDB(Admin admin) {

    }

    //Kontrollerar att man använder sig av ett användarnamn och lösenord som hör ihop.
    public boolean handleUserLogin(String username, String password) throws Exception {
        String SSN, retrievedPassword;
        ResultSet rsOne = st.executeQuery("SELECT Person_SSN FROM user WHERE username = '" + username + "'");
        if (rsOne.next()) {
            SSN = rsOne.getString(1);

            ResultSet rsTwo = st.executeQuery("SELECT password FROM person WHERE SSN = '" + SSN + "'");
            if (rsTwo.next()) {
                retrievedPassword = rsTwo.getString(1);

                if (password.equals(retrievedPassword)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Kontrollerar att man använder sig av ett användarnamn och lösenord som hör ihop.
    //Samt kollar så att man har ett adminid.
    public boolean handleAdminLogin(String username, String password) throws Exception {
        String SSN, retrievedPassword, retrievedAdminID;
        int parsedAdminID;
        ResultSet rsOne = st.executeQuery("SELECT Person_SSN FROM user WHERE username = '" + username + "'");
        if (rsOne.next()) {
            SSN = rsOne.getString(1);
            ResultSet rsTwo = st.executeQuery("SELECT password FROM person WHERE SSN = '" + SSN + "'");

            if (rsTwo.next()) {
                retrievedPassword = rsTwo.getString(1);

                if (password.equals(retrievedPassword)) {
                    ResultSet rsThree = st.executeQuery("SELECT adminID FROM admin WHERE Person_SSN = '" + SSN + "'");

                    if (rsThree.next()) {
                        retrievedAdminID = rsThree.getString(1);
                        parsedAdminID = Integer.parseInt(retrievedAdminID);

                        if (parsedAdminID > 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //Metod som kontrollerar att emailadressen man skrivit in när man begär ett nytt lösenord
    //stämmer överens med den man skrev in när man skapade kontot med hjälp av personens personnummer.
    public boolean checkEmailDB(String SSN, String email) throws Exception {
        String retrievedEmail;
        ResultSet rs = st.executeQuery("SELECT email FROM person WHERE SSN = '" + SSN + "'");
        if (rs.next()) {
            retrievedEmail = rs.getString(1);
            if (retrievedEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    //Metod som ändrar lösenordet i databasen
    public void updatePassword(String password, String SSN) throws Exception {
        st.executeUpdate("UPDATE person SET password = '" + password + "' WHERE SSN = '" + SSN + "'");
    }

    //Skapa metod som hämtar det största ID:et
    public int retrieveBiggestID(String value, String table) throws Exception {
        String retrievedID;
        int parsedID;

        ResultSet rs = st.executeQuery("SELECT MAX(" + value + ") FROM " + table);
        if (rs.next()) {
            retrievedID = rs.getString(1);

            parsedID = Integer.parseInt(retrievedID);
            return parsedID;
        }
        return 0;
    }

}
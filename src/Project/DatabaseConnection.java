package Project;

import Project.Model.*;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

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

    //Metod som används för att lägga till nya övningar i databasen.
    public void addExerciseToDB(Exercise exercise) throws Exception {
        String[] muscleGroup = exercise.getMuscleGroup();
        int numberOfMuscleGroups = muscleGroup.length;

        st.executeUpdate("INSERT INTO exercise (name, typeOfExercise, instruction, imagePath) " +
                "VALUES ('" + exercise.getName() + "', '" + exercise.getTypeOfExercise() + "', '" +
                exercise.getInstruction() + "', '" + exercise.getImagePath() + "');");

        for (int i = 0; i < numberOfMuscleGroups; i++) {
            st.executeUpdate("INSERT INTO musclegroup (muscleGroup, Exercise_name) " +
                    "VALUES ('" + muscleGroup[i] + "', '" + exercise.getName() + "');");
        }

    }

    //Skapa metod som kollar ifall en övning redan finns.
    public boolean checkExerciseName(String exerciseName) throws Exception {
        ResultSet rs = st.executeQuery("SELECT name FROM exercise WHERE name = '" + exerciseName + "'");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    //Skapa en metod som kollar ifall en nutrition redan finns.
    public boolean checkNutritionName(String nutritionName) throws Exception {
        ResultSet rs = st.executeQuery("SELECT name FROM nutrition WHERE name = '" + nutritionName + "'");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    //Metod som kollar om en person redan är admin.
    public boolean checkIfUserIsAdmin(String SSN) throws Exception {
        ResultSet rs = st.executeQuery("SELECT adminID FROM admin WHERE Person_SSN = '" + SSN + "';");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    //Metod som används för att lägga till nya råvaror i databasen.
    public void addNutritionToDB(Nutrition nutrition) throws Exception {
        st.executeUpdate("INSERT INTO nutrition ( name, kcal, protein, fat, carbs) " +
                "VALUES ('" + nutrition.getName() + "', '" + nutrition.getKcalPer100() + "', '" +
                nutrition.getProteinAmount() + "', '" + nutrition.getFatAmount() + "', '" + nutrition.getCarbohydratesAmount() + "');");
    }

    //Metod som används för att "promota" en användare till en admin.
    public void addAdminToDB(String userSSN) throws Exception {
        int newAdminID;
        newAdminID = retrieveBiggestID("adminID", "admin") + 1;

        st.executeUpdate("INSERT INTO admin (adminID, Person_SSN) VALUES (" + newAdminID + ", '" + userSSN + "');");

    }

    //skapa en metod som hämtar alla användare i databasen?
    public ArrayList<String> retrieveAllUsers() throws Exception {
        int numberOfUsers;
        ArrayList<String> userList = new ArrayList<>();

        ResultSet rsOne = st.executeQuery("SELECT COUNT(SSN) FROM person;");
        if (rsOne.next()) {

            ResultSet rsTwo = st.executeQuery("SELECT SSN, firstName, lastName FROM person;");
            if (!rsTwo.next()) {
                return null;
            } else {
                do {
                    for (int i = 1; i < 2; i++) {
                        userList.add(rsTwo.getString(i));
                        userList.add(rsTwo.getString(i + 1));
                        userList.add(rsTwo.getString(i + 2));
                    }

                } while (rsTwo.next());

                return userList;
            }
        }
        return null;
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

    //Metod som hämtar alla värden för att kunna skapa ett AccountInfo objekt.
    public AccountInfo retrieveAccountInfo(String SSN) throws Exception {
        int retrievedHeight, retrievedAge, retrievedWeight;

        ResultSet rs = st.executeQuery("SELECT Person.age, User.height, User.startWeight FROM person, user " +
                "WHERE SSN = Person_SSN AND SSN = '" + SSN + "';");

        if (rs.next()) {
            retrievedAge = rs.getInt(1);
            retrievedHeight = rs.getInt(2);
            retrievedWeight = rs.getInt(3);

            AccountInfo accInfo = new AccountInfo(retrievedHeight, retrievedWeight, retrievedAge);
            return accInfo;
        }

        return null;
    }

    //Metod som hämtar en användares SSN.
    public String retrieveUserSSN(String username) throws Exception {
        String retrievedSSN;
        ResultSet rs = st.executeQuery("SELECT Person_SSN FROM user WHERE username = '" + username + "'");

        if (rs.next()) {
            retrievedSSN = rs.getString(1);
            return retrievedSSN;
        }

        return "NO MATCH";
    }

    //Metod som hämtar dina current statistics med hjälp av SSN som PK. Datumet måste vara i formattet: 5/4-18. Alltså d/m-år.
    public AccountInfo retrieveCurrentStatistics(String SSN, String date) throws Exception {
        int retrievedHeight, retrievedAge, retrievedWeight;

        ResultSet rs = st.executeQuery("SELECT currentWeight, currentHeight, currentAge FROM current_statistics" +
                " WHERE User_Person_SSN = '" + SSN + "' AND timeOfCreation = STR_TO_DATE('" + date + "', '%d/%m-%Y');");

        if (rs.next()) {
            retrievedWeight = rs.getInt(1);
            retrievedHeight = rs.getInt(2);
            retrievedAge = rs.getInt(3);

            AccountInfo currentStats = new AccountInfo(retrievedHeight, retrievedWeight, retrievedAge);
            return currentStats;
        }
        return null;
    }

    //Metod som uppdaterar dina current statistics. Datumet måste vara i formattet: 5/4-18. Alltså d/m-år.
    public void updateCurrentStatistics(String SSN, String username, String date, int height, int weight, int age) throws Exception {
        st.executeUpdate("INSERT INTO current_statistics (currentWeight, currentHeight, currentAge, timeOfCreation, User_Person_SSN, User_username) " +
                "VALUES (" + weight + ", " + height + ", " + age + ", STR_TO_DATE('" + date + "', '%d/%m-%Y'), '" + SSN + "', '" + username + "');");
    }

    //En metod som hämtar samtliga feedbacks och vem som skrev dom.
    public ArrayList<String> retrieveFeedbacksAndWriters() throws Exception {
        ArrayList<String> feedbackList = new ArrayList<>();

        ResultSet rsOne = st.executeQuery("SELECT feedbackID, feedback, header, SSN, firstName, lastName FROM feedback, person WHERE User_Person_SSN = SSN;");
        if (!rsOne.next()) {
            return null;
        } else {
            do {
                for (int i = 1; i < 2; i++) {
                    feedbackList.add(rsOne.getString(1));
                    feedbackList.add(rsOne.getString(2));
                    feedbackList.add(rsOne.getString(3));
                    feedbackList.add(rsOne.getString(4));
                    feedbackList.add(rsOne.getString(5));
                    feedbackList.add(rsOne.getString(6));
                }

            } while (rsOne.next());

            return feedbackList;
        }
    }

    //Hämtar en feedback.
    public String retrieveChosenFeedback(String id) throws Exception {
        String feedback;

        ResultSet rs = st.executeQuery("SELECT feedback FROM feedback WHERE feedbackID = '" + id + "';");
        if (rs.next()) {
            feedback = rs.getString(1);

            return feedback;
        } else {
            return null;
        }
    }

    public void updateMeasurementStatistics(String SSN, int upperArmL, int upperArmR, int forearmL, int forearmR, int thighL, int thighR,
                                            int calfL, int calfR, int waist, int shoulderWidth, int chestWidth) throws Exception {
        st.executeUpdate("INSERT INTO Measurement (measurementID, upperArmL, upperArmR, forearmL, " +
                "forearmR, thighL, thighR, calfL, calfR, waist, shoulderWidth, chestWidth) " +
                "VALUES (" + SSN + ", " + upperArmL + ", " + upperArmR + ", " + forearmL + ", " + forearmR + ", " + thighL + ", " + thighR +
                ", " + calfL + ", " + calfR + ", " + waist + ", " + shoulderWidth + ", " + chestWidth + " )");

                // STR_TO_DATE('" + date + "', '%d/%m-%Y'), '" + SSN + "', '" + username + "');");
    }
}
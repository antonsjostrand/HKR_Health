package Project;

import Project.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

            if (retrievedID == null) {
                return 0;
            } else {
                parsedID = Integer.parseInt(retrievedID);
                return parsedID;
            }
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

    //metod som hämtar en användares namn.
    public String retrieveUserCompleteName(String username) throws Exception{
        String retrievedName, firstName, lastName;

        ResultSet rs = st.executeQuery("SELECT firstName, lastName FROM person, user WHERE SSN = Person_SSN AND username = '" + username + "';");

        if (rs.next()){
            firstName = rs.getString(1);
            lastName = rs.getString(2);

            retrievedName = firstName + " " + lastName;

            return retrievedName;
        }
        return null;
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

    //Metod som uppdaterar dina measurements
    public void updateMeasurementStatistics(int upperArmL, int upperArmR, int forearmL, int forearmR, int thighL, int thighR,
                                            int calfL, int calfR, int waist, int shoulderWidth, int chestWidth, String date, String SSN, String username, int diaryID) throws Exception {

        int retrievedID;
        retrievedID = DatabaseConnection.getInstance().retrieveBiggestID("measurementID", "measurement") + 1;

        st.executeUpdate("INSERT INTO Measurement (measurementID, upperArmL, upperArmR, forearmL, " +
                "forearmR, thighL, thighR, calfL, calfR, waist, shoulderWidth, chestWidth, dateOfCreation, " +
                "training_diary_User_Person_SSN, training_diary_User_username, training_diary_diaryID) " +
                "VALUES (" + retrievedID + ", " + upperArmL + ", " + upperArmR + ", " + forearmL + ", " + forearmR + ", " + thighL + ", " + thighR +
                ", " + calfL + ", " + calfR + ", " + waist + ", " + shoulderWidth + ", " + chestWidth +
                ", STR_TO_DATE('" + date + "', '%d/%m-%Y'), '" + SSN + "', '" + username + "', " + diaryID + ");");

    }

    //Metod som skapar en training diary i samband med att man skapar ett konto.
    public void addDiaryToDB(String ssn, String username) {
        try {
            int retrievedDiaryID;
            retrievedDiaryID = retrieveBiggestID("diaryID", "training_diary") + 1;

            st.executeUpdate("INSERT INTO training_diary (User_Person_SSN, User_username, diaryID) " +
                    "VALUES ('" + ssn + "', '" + username + "', " + retrievedDiaryID + ");");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metod som hämtar ett trainingdiary ID.
    public int retrieveDiaryID(String username) throws Exception {
        int retrievedID;

        ResultSet rs = st.executeQuery("SELECT diaryID FROM training_diary WHERE User_username = '" + username + "'");
        if (rs.next()) {
            retrievedID = rs.getInt(1);
            return retrievedID;
        } else {
            return Integer.parseInt(null);
        }
    }

    //Metod som skapar en ny feedback
    public void addFeedbackToDB(String ssn, String username, String header, String content) {
        try {

            int retrievedID = DatabaseConnection.getInstance().retrieveBiggestID("feedbackID", "feedback") + 1;
            st.executeUpdate("INSERT INTO feedback (feedbackID, feedback, header, User_Person_SSN, User_username) " +
                    "VALUES (" + retrievedID + ", '" + content + "', '" + header + "', '" + ssn + "', '" + username + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metod som hämtar samtliga exercises utan muskelgrupperna.
    public ArrayList<Exercise> retrieveExercise(String musclegroup) throws Exception {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        Exercise exercise;
        String name, type, instruction, imagePath;

        ResultSet rs = st.executeQuery("SELECT name, typeOfExercise, instruction, imagePath FROM exercise, musclegroup " +
                "WHERE name = Exercise_name AND muscleGroup = '" + musclegroup + "';");

        if (!rs.next()) {
            return null;
        } else {
            do {
                name = rs.getString(1);
                type = rs.getString(2);
                instruction = rs.getString(3);
                imagePath = rs.getString(4);

                exercise = new Exercise(name, type, instruction, imagePath);
                exerciseList.add(exercise);

            } while (rs.next());

            return exerciseList;
        }
    }

    //Hämtar en specifik övning
    public Exercise retrieveSpecificExercise(String name){
        try{
            Exercise retrievedExercise;
            String retrievedName, type, instruction, image;
            String[] muscleGroup = new String[4];
            int counter = 0;

            ResultSet rs = st.executeQuery("SELECT name, typeOfExercise, instruction, imagePath FROM exercise WHERE name = '" + name + "';");
            if (rs.next()){
                retrievedName = rs.getString(1);
                type = rs.getString(2);
                instruction = rs.getString(3);
                image = rs.getString(4);

                ResultSet rsTwo = st.executeQuery("SELECT muscleGroup FROM musclegroup WHERE Exercise_name = '" + name + "';");
                if (!rsTwo.next()){
                    return null;
                }else {
                    do {
                        muscleGroup[counter] = rsTwo.getString(1);
                        counter = counter + 1;
                    }while (rsTwo.next());

                    retrievedExercise = new Exercise(retrievedName, type, instruction, image);
                    retrievedExercise.setMuscleGroup(muscleGroup);

                    return retrievedExercise;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Metod som hämtar samtliga råvaror.
    public ArrayList<String> retrieveAllNutritions() throws Exception{
       ArrayList<String> retrievedNutrition = new ArrayList<>();

        ResultSet rs = st.executeQuery("SELECT name, protein, carbs, fat, kcal FROM nutrition;");
            if (!rs.next()){
                return null;
            }else{
                do {
                    retrievedNutrition.add(rs.getString(1));
                    retrievedNutrition.add(rs.getString(2));
                    retrievedNutrition.add(rs.getString(3));
                    retrievedNutrition.add(rs.getString(4));
                    retrievedNutrition.add(rs.getString(5));

                }while (rs.next());
                return retrievedNutrition;
            }
    }

    //Metod som hämtar en lista med specifika råvaror.
    public ArrayList<String> retrieveSpecificNutrions(String macro, String from, String to) throws Exception{
        ArrayList<String> retrievedNutrition = new ArrayList<>();

        ResultSet rs = st.executeQuery("SELECT name, protein, carbs, fat, kcal FROM nutrition WHERE " + macro + " >= " + from + " AND " + macro + " <= " + to + ";");

            if (!rs.next()){
                return null;
            }else{
                do {
                    retrievedNutrition.add(rs.getString(1));
                    retrievedNutrition.add(rs.getString(2));
                    retrievedNutrition.add(rs.getString(3));
                    retrievedNutrition.add(rs.getString(4));
                    retrievedNutrition.add(rs.getString(5));
                }while (rs.next());
                return retrievedNutrition;
            }
    }

    //Metod som raderar en feedback
    public void deleteFeedbackFromDB(String id) throws Exception{
        st.executeUpdate("DELETE FROM feedback WHERE feedbackID = " + Integer.parseInt(id) + ";");
    }
}
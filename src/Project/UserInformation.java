package Project;

import javafx.scene.control.Alert;

public class UserInformation {

    private static UserInformation ourUser;
    private String SSN, username, completeName;
    private int diaryID;

    //Skapar ett objekt om det inte redan finns något när man man kallar på metoden.
    public static UserInformation getInstance() {
        try{
            if (ourUser == null){
                ourUser = new UserInformation();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Information");
            alert.setHeaderText("Creation of object");
            alert.setContentText("Failed to create object of class: UserInformation");
            alert.showAndWait();
        }
        return ourUser;
    }

    //Används ej.
    private UserInformation() {
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDiaryID() {
        return diaryID;
    }

    public void setDiaryID(int diaryID) {
        this.diaryID = diaryID;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
}

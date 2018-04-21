package Project.Model;

import javafx.scene.control.Alert;

public class Person {

    private String firstName, lastName, password, SSN;
    private int age;

    public Person (String firstName, String lastName, String password, String SSN, int age){
        try {
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.SSN = SSN;
            this.age = age;
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Person");
            alert.setHeaderText("Creation error");
            alert.setContentText("Failed to create the new person.");
            alert.showAndWait();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

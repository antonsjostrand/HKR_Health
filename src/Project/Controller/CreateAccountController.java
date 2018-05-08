package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class CreateAccountController implements Initializable {

    private boolean checkUserName;
    private boolean checkSSN;
    private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                                    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Å', 'Ä', 'Ö', 'a',
                                    'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};
    private char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField heightTF;
    @FXML
    private TextField weightTF;
    @FXML
    private TextField ssnTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button cancelButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void createAccountButtonPressed(ActionEvent event) {
        try {
            checkInputNotEmpty();
            checkFirstNameFormat(firstNameTF.getText());
            checkLastNameFormat(lastNameTF.getText());
            checkAgeFormat(ageTF.getText());
            checkSSNFormat(ssnTF.getText());
            checkUserNameFormat(usernameTF.getText());
            checkHeightFormat(heightTF.getText());
            checkWeightFormat(weightTF.getText());
            checkPasswordFormat(passwordTF.getText());
            checkEmailFormat(emailTF.getText());

            //Kontrollerar om användarnamnet eller personnumret redan finns lagrat i databasen.
            checkUserName = DatabaseConnection.getInstance().checkUserNameDB(usernameTF.getText());
            checkSSN = DatabaseConnection.getInstance().checkSSNDB(ssnTF.getText());

            accountCreation(checkUserName, checkSSN);

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input failure");
            alert.setContentText("Input fields cannot be empty.");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input failure");
            alert.setContentText("Check that your age, SSN, height and weight information is correct.");
            alert.showAndWait();

        } catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input failure");
            alert.setContentText("Follow the guidelines to make sure the information is input the correct way! " +
                                 "Check the format on your input so it corresponds with the rules.");
            alert.showAndWait();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Kontrollerar så att inga textfields är tomma när man försöker skapa ett konto.
    public void checkInputNotEmpty(){
        if (usernameTF.getText().isEmpty() || firstNameTF.getText().isEmpty() || lastNameTF.getText().isEmpty() ||
                passwordTF.getText().isEmpty() || ssnTF.getText().isEmpty() || ageTF.getText().isEmpty() ||
                weightTF.getText().isEmpty() || heightTF.getText().isEmpty() || emailTF.getText().isEmpty()) {
            throw new NullPointerException();
        }
    }

    //Kontrollerar så att formen på användarnamnet är korrekt.
    public void checkUserNameFormat(String username){
        if (username.length() < 3 || username.length() > 16){
            usernameTF.clear();
            usernameTF.setText("Enter a valid username.");
            usernameTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Kontrollerar så att formatet på alla SSN stämmer överens med kraven.
    public void checkSSNFormat(String SSN){
        String firstPart = "", secondPart = "";
        char divider;

        if (SSN.length() == 11){
            for(int i = 0; i < 6; i++){
                firstPart = firstPart + SSN.charAt(i);
            }
            for (int counter = 0; counter < 4; counter++){
                secondPart = secondPart + SSN.charAt(counter + 7);
            }
            divider = SSN.charAt(6);

                if (divider == '-') {
                    Integer.parseInt(firstPart);
                    Integer.parseInt(secondPart);
                }else{
                    ssnTF.clear();
                    ssnTF.setText("Enter a correct SSN.");
                    ssnTF.requestFocus();
                    throw new InputMismatchException();
                }

        }else {
            ssnTF.clear();
            ssnTF.setText("Enter a correct SSN.");
            ssnTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Kontrollerar så att formattet på lösenordet är korrekt.
    public void checkPasswordFormat(String password){
        if (password.length() < 3 || password.length() > 20){
            passwordTF.clear();
            passwordTF.setText("Enter a valid password.");
            passwordTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    //Metod som skapar kontot eller ej beroende på dess parametrar.
    //Parametrarna är boolean värden som hämtats från DatabaseConnection.
    public void accountCreation(boolean username, boolean SSN){
        if (username == false && SSN == false) {
            User newUser = new User(usernameTF.getText(),
                    firstNameTF.getText(),
                    lastNameTF.getText(),
                    passwordTF.getText(),
                    emailTF.getText(),
                    ssnTF.getText(),
                    Integer.valueOf(ageTF.getText()),
                    Integer.valueOf(weightTF.getText()),
                    Integer.valueOf(heightTF.getText()));

            DatabaseConnection.getInstance().addUserToDB(newUser);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Account Created");
            alert.setContentText("Account creation successful. Press cancel to go back to the login scene.");
            alert.showAndWait();

            usernameTF.clear();
            firstNameTF.clear();
            lastNameTF.clear();
            passwordTF.clear();
            ssnTF.clear();
            ageTF.clear();
            weightTF.clear();
            heightTF.clear();
            emailTF.clear();

        } else if (username == true && SSN == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Username");
            alert.setContentText("Username already exists.");
            alert.showAndWait();

            usernameTF.clear();
            usernameTF.setText("Enter a valid username.");
            usernameTF.requestFocus();

        } else if (username == false && SSN == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("SSN");
            alert.setContentText("SSN already exists.");
            alert.showAndWait();

            ssnTF.clear();
            ssnTF.setText("Enter a valid SSN.");
            ssnTF.requestFocus();

        } else if (username == true && SSN == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Username & SSN");
            alert.setContentText("Username and SSN already exists.");
            alert.showAndWait();

            usernameTF.clear();
            usernameTF.setText("Enter a vaild username");
            ssnTF.clear();
            ssnTF.setText("Enter a valid SSN");
            ssnTF.requestFocus();
        }
    }

    //Kontrollerar så att åldern endast består av siffror.
    public void checkAgeFormat(String age){
        for (int i = 0; i < letters.length; i++){
            if (age.contains(String.valueOf(letters[i]))){
                ageTF.clear();
                ageTF.setText("Enter a valid age.");
                ageTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Kontrollerar så att längden man skriver in endast består av siffor.
    public void checkHeightFormat(String height){
        for (int i = 0; i < letters.length; i++){
            if (height.contains(String.valueOf(letters[i]))){
                heightTF.clear();
                heightTF.setText("Enter a valid height.");
                heightTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }
    //Kontroller så att vikten man skriver in endast består av siffror.
    public void checkWeightFormat(String weight){
        for (int i = 0; i < letters.length; i++){
            if (weight.contains(String.valueOf(letters[i]))){
                weightTF.clear();
                weightTF.setText("Enter a valid weight.");
                weightTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Kontrollerar så att förnamnet endast består av bokstäver.
    public void checkFirstNameFormat(String firstName){
        for(int i = 0; i < numbers.length; i++){
            if (firstName.contains(String.valueOf(numbers[i]))){
                firstNameTF.clear();
                firstNameTF.setText("Enter a proper firstname.");
                firstNameTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Kontrollerar så att efternamnet endast består av bokstäver.
    public void checkLastNameFormat(String lastName){
        for(int i = 0; i < numbers.length; i++){
            if (lastName.contains(String.valueOf(numbers[i]))){
                lastNameTF.clear();
                lastNameTF.setText("Enter a proper lastname.");
                lastNameTF.requestFocus();
                throw new InputMismatchException();
            }
        }
    }

    //Skapa metod för att kontrollera email på något sätt
    public void checkEmailFormat(String email){
        if (email.contains("@gmail.com") || email.contains("@hotmail.com") || email.contains("@hotmail.se")){
            return;
        }else{
            emailTF.clear();
            emailTF.setText("Enter a proper email.");
            emailTF.requestFocus();
            throw new InputMismatchException();
        }
    }

}

package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
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



public class CreateAccountController implements Initializable{

    private boolean checkUserName;
    private boolean checkSSN;

    @FXML private TextField firstNameTF;
    @FXML private TextField lastNameTF;
    @FXML private TextField ageTF;
    @FXML private TextField heightTF;
    @FXML private TextField weightTF;
    @FXML private TextField ssnTF;
    @FXML private TextField passwordTF;
    @FXML private TextField usernameTF;
    @FXML private Button createAccountButton;
    @FXML private Button cancelButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML
    void createAccountButtonPressed(ActionEvent event) {
        try{
            checkUserName = DatabaseConnection.getInstance().checkUserName(usernameTF.getText());
            checkSSN = DatabaseConnection.getInstance().checkSSN(ssnTF.getText());

            if (checkUserName == false && checkSSN == false) {
                        User newUser = new User(usernameTF.getText(),
                            firstNameTF.getText(),
                            lastNameTF.getText(),
                            passwordTF.getText(),
                            ssnTF.getText(),
                            Integer.valueOf(ageTF.getText()),
                            weightTF.getText(),
                            heightTF.getText());

                DatabaseConnection.getInstance().addUserToDB(newUser);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account Creation");
                alert.setHeaderText("Account Created");
                alert.setContentText("Account creation successful.");
                alert.showAndWait();

                usernameTF.clear();
                firstNameTF.clear();
                lastNameTF.clear();
                passwordTF.clear();
                ssnTF.clear();
                ageTF.clear();
                weightTF.clear();
                heightTF.clear();

            }else if (checkUserName == true && checkSSN == false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Username");
                alert.setContentText("Username already exists.");
                alert.showAndWait();

                usernameTF.clear();

            }else if (checkUserName == false && checkSSN == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("SSN");
                alert.setContentText("SSN already exists.");
                alert.showAndWait();

                ssnTF.clear();

            }else if (checkUserName == true && checkSSN == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Username & SSN");
                alert.setContentText("Username and SSN already exists.");
                alert.showAndWait();

                usernameTF.clear();
                ssnTF.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("HEJEHEHEHEH");
            //Adda error handling
        }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

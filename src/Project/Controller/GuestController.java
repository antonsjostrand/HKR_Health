package Project.Controller;

import Project.DatabaseConnection;
import Project.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class GuestController implements Initializable {

    private boolean loginStatus;

    @FXML
    private ImageView HKRlogo;
    @FXML
    private Button guestExercises;
    @FXML
    private Button guestStretch;
    @FXML
    private Button guestTimer;
    @FXML
    private javafx.scene.image.ImageView login;
    @FXML
    private javafx.scene.image.ImageView password;
    @FXML
    private javafx.scene.image.ImageView background;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button guestButton;
    @FXML
    private TextField userNameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button loginButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button lostPasswordButton;

    /* @FXML
     void adminButtonPressed(ActionEvent event) {
         try {
             loginStatus = DatabaseConnection.getInstance().handleAdminLogin(userNameTF.getText(), passwordTF.getText());

             if (loginStatus == true) {
                 changeScene("adminScene", event);

             } else if (loginStatus == false) {
                 userNameTF.clear();
                 passwordTF.clear();
                 userNameTF.requestFocus();
                 throw new InputMismatchException();
             }

         } catch (InputMismatchException e) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Admin login failed.");
             alert.setContentText("Username or password is not correct or you might not be an admin.");
             alert.showAndWait();

         } catch (Exception e) {
             e.printStackTrace();
         }
     }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void guestButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void guestPressedExercisesButton(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestExercises.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            // Error handling
        }
    }

   /* @FXML
    void lostPasswordButtonPressed(ActionEvent event) {
        try {
            changeScene("lostPasswordScene", event);

        } catch (IOException e) {
            //Fixa error handling
        }
    }*/


    @FXML
    void guestPressedStretchButton(ActionEvent event) {


        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestStretch.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            // Error handling
        }
    }

    @FXML
    void guestPressedTimerButton(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestTimer.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void guestPressedBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            // Error handling
        }


   /* @FXML void loginButtonPressed(ActionEvent event) {
        try {
            loginStatus = DatabaseConnection.getInstance().handleUserLogin(userNameTF.getText(), passwordTF.getText());

            if (loginStatus == true) {
                UserInformation.getInstance().setSSN(DatabaseConnection.getInstance().retrieveUserSSN(userNameTF.getText()));
                UserInformation.getInstance().setUsername(userNameTF.getText());
                changeScene("userScene", event);

            } else if (loginStatus == false) {
                userNameTF.clear();
                passwordTF.clear();
                userNameTF.requestFocus();
                throw new InputMismatchException();
            }
        }catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User login failed");
            alert.setContentText("Username or password is not correct, try again.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/



   /* public void changeScene(String resource, ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/" + resource + ".fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}*/
    }
}
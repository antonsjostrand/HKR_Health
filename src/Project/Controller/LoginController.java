package Project.Controller;

import Project.DatabaseConnection;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private boolean loginStatus;

    @FXML private ImageView startBackground;
    @FXML private AnchorPane anchorPane;
    @FXML private Button guestButton;
    @FXML private TextField userNameTF;
    @FXML private TextField passwordTF;
    @FXML private Button loginButton;
    @FXML private Button createAccountButton;
    @FXML private Button adminButton;
    @FXML private Button lostPasswordButton;

    @FXML void adminButtonPressed(ActionEvent event) {
        try {
            changeScene("adminScene", event);
         
        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML void createAccountButtonPressed(ActionEvent event) {
        try {
            changeScene("createAccountScene", event);

        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML void guestButtonPressed(ActionEvent event) {
        try {
            changeScene("guestScene", event);

        } catch (IOException e){
            //Fixa error handling
        }
    }

    @FXML void loginButtonPressed(ActionEvent event) {
        try {
            loginStatus = DatabaseConnection.getInstance().handleUserLogin(userNameTF.getText(), passwordTF.getText());

            if (loginStatus == true) {
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
            alert.setHeaderText("Username or password not correct.");
            alert.setContentText("Username or password is not correct, try again.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML void lostPasswordButtonPressed(ActionEvent event) {
        try {
            changeScene("lostPasswordScene", event);

        }catch (IOException e){
            //Fixa error handling
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/Resources/dumbells.png"));
        startBackground.setImage(image);

        DatabaseConnection.getInstance().connectToDB();

    }

    public void changeScene(String resource, ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/" + resource + ".fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}

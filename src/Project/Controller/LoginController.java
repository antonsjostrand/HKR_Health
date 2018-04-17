package Project.Controller;

import Project.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ImageView startBackground;
    @FXML private AnchorPane anchorPane;
    @FXML private Button guestButton;
    @FXML private TextField userNameTF;
    @FXML private TextField passwordTF;
    @FXML private Button loginButton;
    @FXML private Button createAccountButton;
    @FXML private Button adminButton;
    @FXML private Button lostPasswordButton;

    @FXML void adminButtonPressed(ActionEvent event) {

    }

    @FXML void createAccountButtonPressed(ActionEvent event) {

    }

    @FXML void guestButtonPressed(ActionEvent event) {

    }

    @FXML void loginButtonPressed(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML void lostPasswordButtonPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/Resources/dumbells.png"));
        startBackground.setImage(image);

        DatabaseConnection dbConnection = new DatabaseConnection();
    }
}

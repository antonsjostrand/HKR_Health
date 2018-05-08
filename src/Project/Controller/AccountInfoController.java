package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.AccountInfo;
import Project.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.sql.Connection.*;

public class AccountInfoController implements Initializable {

    @FXML
   private TextField updateHeight, updateWeight, updateAge, curHeight, curWeight, curAge, firstHeight, firstWeight, firstAge;;


   private AccountInfo accInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            accInfo = DatabaseConnection.getInstance().retrieveAccountInfo(UserInformation.getInstance().getSSN());
            firstHeight.setText(String.valueOf(accInfo.getHeight()));
            firstWeight.setText(String.valueOf(accInfo.getWeight()));
            firstAge.setText(String.valueOf(accInfo.getAge()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void goBack (ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            //Fixa error handling
        }
    }

    @FXML
    void saveButtonPressed () {
        try {
            accInfo = DatabaseConnection.getInstance().retrieveAccountInfo(UserInformation.getInstance().getSSN());
            curHeight.setText(String.valueOf(updateHeight.getText()));
            curWeight.setText(String.valueOf(updateWeight.getText()));
            curAge.setText(String.valueOf(updateAge.getText()));


        } catch (Exception e) {
            //Fixa error handling
        }
    }
}




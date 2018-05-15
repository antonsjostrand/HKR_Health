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
import javafx.scene.control.TextArea;
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
   private TextField updateHeight, updateWeight, updateAge, updateDate,
            searchDate, firstHeight, firstWeight, firstAge;;

   @FXML
   private TextArea textArea1, textArea2;


   private AccountInfo accInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            accInfo = DatabaseConnection.getInstance().retrieveAccountInfo(UserInformation.getInstance().getSSN());
            firstHeight.setText(String.valueOf(accInfo.getHeight()));
            firstWeight.setText(String.valueOf(accInfo.getWeight()));
            firstAge.setText(String.valueOf(accInfo.getAge()));

            accInfo = DatabaseConnection.getInstance().retrieveCurrentStatistics(
                    UserInformation.getInstance().getSSN(), updateDate.getText());

            textArea1.setText(" ");

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

            DatabaseConnection.getInstance().updateCurrentStatistics(UserInformation.getInstance().getSSN(),
                    UserInformation.getInstance().getUsername(),
                    updateDate.getText(),
                    Integer.parseInt(updateHeight.getText()),
                    Integer.parseInt(updateWeight.getText()),
                    Integer.parseInt(updateAge.getText()));


        } catch (Exception e) {
            //Fixa error handling
        }
    }
    @FXML
    void searchButtonPressed() {
        try {

        } catch (Exception e) {
            //Fixa error handling
        }
    }

}




package Project.Controller;

import Project.Model.AccountInfo;
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
import java.util.ResourceBundle;

public class AccountInfoController implements Initializable {

    @FXML
    TextField updateHeight, updateWeight, updateAge, currentHeight, currentWeight, currentAge, currentBmi;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    void saveInfo (){
        try{
            currentHeight.setText(updateHeight.getText());
            currentWeight.setText(updateWeight.getText());
            currentAge.setText(updateAge.getText());
            //currentBmi.setText();


        }
        catch (Exception e){
            //Fixa error handling
        }

    }
}

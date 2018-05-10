package Project.Controller;

import Project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class AddAdminController implements Initializable {

    @FXML private Button cancelButton;
    @FXML private Button promoteButton;
    @FXML private TextField ssnPromoteTF;
    @FXML private TextArea usersTA;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/adminScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void promoteButtonPressed(ActionEvent event) {
        boolean checkIfAdminStatus;
        try {
            checkSSNFormat(ssnPromoteTF.getText());
            checkIfAdminStatus = DatabaseConnection.getInstance().checkIfUserIsAdmin(ssnPromoteTF.getText());

            addNewAdmin(checkIfAdminStatus);

        }catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Input format incorrect.");
            alert.setContentText("The values entered doesn't correspond with the rules.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            ArrayList<String> userList = DatabaseConnection.getInstance().retrieveAllUsers();
            usersTA.setText("|        SSN        |    Firstname    &    Lastname    |");
            usersTA.appendText("\n---------------------------------------------------");



            for (int i = 0; i < userList.size(); i = i + 3){
                usersTA.appendText("\n" + userList.get(i) + "            " + userList.get(i+1) + "  " + userList.get(i+2));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
                ssnPromoteTF.clear();
                ssnPromoteTF.setText("Enter a correct SSN.");
                ssnPromoteTF.requestFocus();
                throw new InputMismatchException();
            }

        }else {
            ssnPromoteTF.clear();
            ssnPromoteTF.setText("Enter a correct SSN.");
            ssnPromoteTF.requestFocus();
            throw new InputMismatchException();
        }
    }

    public void addNewAdmin(boolean status) throws Exception{
        if (status == false){
            DatabaseConnection.getInstance().addAdminToDB(ssnPromoteTF.getText());


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin.");
            alert.setHeaderText("Admin added.");
            alert.setContentText("The person with SSN " + ssnPromoteTF.getText() + "is now an admin.");
            alert.showAndWait();

            ssnPromoteTF.clear();

        }else{
            ssnPromoteTF.clear();
            ssnPromoteTF.setText("person is already an admin.");
            ssnPromoteTF.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input failure.");
            alert.setHeaderText("Input format incorrect.");
            alert.setContentText("This person is already an admin.");
            alert.showAndWait();
        }
    }
}

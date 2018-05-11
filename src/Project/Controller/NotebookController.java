package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Diary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotebookController implements Initializable {

    ArrayList<Diary> myArray = new ArrayList<>();
    File file = new File("list.txt");


    @FXML
    private TextField date;
    @FXML
    private TextArea notes;
    @FXML
    private ImageView notebookLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/Resources/hkrlogo.png"));
        notebookLogo.setImage(image);
    }

    @FXML
    void goBack(ActionEvent event) {
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
        void saveDiary (ActionEvent event) {
        try {

                myArray.add(new Diary(date.getText(), (notes.getText())));
                writeToFile();

            } catch (Exception e) {
                //Fixa error handling

            }
        }

    private void writeToFile() {
        try {
            FileWriter output = new FileWriter(file);
            output.write(myArray.toString());
            output.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        }
    }
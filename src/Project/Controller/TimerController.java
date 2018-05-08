package Project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.jvm.hotspot.runtime.Thread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimerController extends java.lang.Thread implements Initializable{

    @FXML
    private ImageView timerLogo;
    @FXML
    private Button start;
    @FXML
    private Button stop;
    @FXML
    private Button reset;
    @FXML
    private int minutes;
    @FXML
    private int seconds;
    @FXML
    private int milliseconds;

    private boolean state = true;

    //Använd timeline animation!

    @FXML void startStopWatch() {
        state = true;

        java.lang.Thread t = new java.lang.Thread();

        for(;;) {

            if (state == true) {
                try {
                    sleep(1);

                    if(milliseconds>1000){
                        milliseconds = 0;
                        seconds++;
                    }
                    if(seconds>60){
                        milliseconds = 0;
                        seconds = 0;
                        minutes++;
                    }
                    milliseconds++;

                } catch (Exception e) {

                }
            }
            else {
                break;
            }
        }
        t.start();
    }

    @FXML void stopWatch() {
        state = false;
    }
    @FXML void resetWatch() {
        state = false;

        minutes = 0;
        seconds = 0;
        milliseconds = 0;

    }

    @FXML
    void guestPressedBack(javafx.event.ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            // Error handling
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/Resources/hkrlogo.png"));
        timerLogo.setImage(image);
    }
}

package Project.Controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuestTimerController implements Initializable {

    private AnimationTimer timer;
    private int seconds, minute;

    @FXML
    private Label secondLabel;

    @FXML
    private Label minuteLabel;

    @FXML
    void resetStopwatchButtonPressed(ActionEvent event) {
        try{
            secondLabel.setText("00");
            minuteLabel.setText("00");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void startStopwatchButtonPressed(ActionEvent event) {
        try {
            timer.start();

        }catch(Exception e){
            e.printStackTrace();
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
            // Error handling
        }
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

    @FXML void guestPressedBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/guestScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void stopStopwatchButtonPressed(ActionEvent event) {
        timer.stop();
    }

    @FXML
    void cancelButtonPressed(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/userScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timer = new AnimationTimer() {

            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime != 0) {
                    if (now > lastTime + 1_000_000_000) {
                        seconds++;
                        if (seconds < 10) {
                            secondLabel.setText("0" + Integer.toString(seconds));
                            lastTime = now;
                        } else {
                            secondLabel.setText(Integer.toString(seconds));
                            lastTime = now;
                        }
                        if (seconds == 60) {
                            minute++;
                            seconds = 0;
                            if (minute < 10) {
                                minuteLabel.setText("0" + Integer.toString(minute));
                                secondLabel.setText("00");
                                lastTime = now;
                            } else {
                                minuteLabel.setText(Integer.toString(minute));
                                secondLabel.setText("00");
                                lastTime = now;
                            }
                        }
                    }
                } else {
                    lastTime = now;

                }
            }

            @Override
            public void stop() {
                super.stop();
                lastTime = 0;
                seconds = 0;
            }
        };
    }
}
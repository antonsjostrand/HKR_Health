package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Connectar till databasen.
        DatabaseConnection.getInstance().connectToDB();

        //Skapar ikonen


        Parent root = FXMLLoader.load(getClass().getResource("View/loginScene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("HKR Health");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

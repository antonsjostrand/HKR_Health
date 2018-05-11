package Project.Controller;

import Project.DatabaseConnection;
import Project.Model.Measurement;
import Project.UserInformation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MeasurementController {
    @FXML
    private TextField upperArmL, upperArmR, forearmL, forearmR, thighL, thighR, calfL, calfR,
            waist, shoulderWidth, chestWidth;

    private Measurement measurement;

    @FXML
    void saveMeasurementButton(){
        try {
            DatabaseConnection.getInstance().updateMeasurementStatistics(
                    UserInformation.getInstance().getSSN(),
                    Integer.parseInt(upperArmL.getText()),
                    Integer.parseInt(upperArmR.getText()),
                    Integer.parseInt(forearmL.getText()),
                    Integer.parseInt(forearmR.getText()),
                    Integer.parseInt(thighL.getText()),
                    Integer.parseInt(thighR.getText()),
                    Integer.parseInt(calfL.getText()),
                    Integer.parseInt(calfR.getText()),
                    Integer.parseInt(waist.getText()),
                    Integer.parseInt(shoulderWidth.getText()),
                    Integer.parseInt(chestWidth.getText())
                    );

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

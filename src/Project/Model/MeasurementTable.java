package Project.Model;

import javafx.beans.property.SimpleStringProperty;

public class MeasurementTable {

    private final SimpleStringProperty upperArmL, upperArmR, forearmL, forearmR, thighL, thighR, calfL, calfR, waist, shoulderWidth, chestWidth, dateOfCreation;

    public MeasurementTable(String upperArmL, String upperArmR, String forearmL, String forearmR, String thighL, String thighR, String calfL, String calfR,
                            String waist, String shoulderWidth, String chestWidth, String dateOfCreation){
        this.upperArmL = new SimpleStringProperty(upperArmL);
        this.upperArmR = new SimpleStringProperty(upperArmR);
        this.forearmL = new SimpleStringProperty(forearmL);
        this.forearmR = new SimpleStringProperty(forearmR);
        this.thighL = new SimpleStringProperty(thighL);
        this.thighR = new SimpleStringProperty(thighR);
        this.calfL = new SimpleStringProperty(calfL);
        this.calfR = new SimpleStringProperty(calfR);
        this.waist = new SimpleStringProperty(waist);
        this.shoulderWidth = new SimpleStringProperty(shoulderWidth);
        this.chestWidth = new SimpleStringProperty(chestWidth);
        this.dateOfCreation = new SimpleStringProperty(dateOfCreation);
    }

    public String getUpperArmL() {
        return upperArmL.get();
    }

    public SimpleStringProperty upperArmLProperty() {
        return upperArmL;
    }

    public void setUpperArmL(String upperArmL) {
        this.upperArmL.set(upperArmL);
    }

    public String getUpperArmR() {
        return upperArmR.get();
    }

    public SimpleStringProperty upperArmRProperty() {
        return upperArmR;
    }

    public void setUpperArmR(String upperArmR) {
        this.upperArmR.set(upperArmR);
    }

    public String getForearmL() {
        return forearmL.get();
    }

    public SimpleStringProperty forearmLProperty() {
        return forearmL;
    }

    public void setForearmL(String forearmL) {
        this.forearmL.set(forearmL);
    }

    public String getForearmR() {
        return forearmR.get();
    }

    public SimpleStringProperty forearmRProperty() {
        return forearmR;
    }

    public void setForearmR(String forearmR) {
        this.forearmR.set(forearmR);
    }

    public String getThighL() {
        return thighL.get();
    }

    public SimpleStringProperty thighLProperty() {
        return thighL;
    }

    public void setThighL(String thighL) {
        this.thighL.set(thighL);
    }

    public String getThighR() {
        return thighR.get();
    }

    public SimpleStringProperty thighRProperty() {
        return thighR;
    }

    public void setThighR(String thighR) {
        this.thighR.set(thighR);
    }

    public String getCalfL() {
        return calfL.get();
    }

    public SimpleStringProperty calfLProperty() {
        return calfL;
    }

    public void setCalfL(String calfL) {
        this.calfL.set(calfL);
    }

    public String getCalfR() {
        return calfR.get();
    }

    public SimpleStringProperty calfRProperty() {
        return calfR;
    }

    public void setCalfR(String calfR) {
        this.calfR.set(calfR);
    }

    public String getWaist() {
        return waist.get();
    }

    public SimpleStringProperty waistProperty() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist.set(waist);
    }

    public String getShoulderWidth() {
        return shoulderWidth.get();
    }

    public SimpleStringProperty shoulderWidthProperty() {
        return shoulderWidth;
    }

    public void setShoulderWidth(String shoulderWidth) {
        this.shoulderWidth.set(shoulderWidth);
    }

    public String getChestWidth() {
        return chestWidth.get();
    }

    public SimpleStringProperty chestWidthProperty() {
        return chestWidth;
    }

    public void setChestWidth(String chestWidth) {
        this.chestWidth.set(chestWidth);
    }

    public String getDateOfCreation() {
        return dateOfCreation.get();
    }

    public SimpleStringProperty dateOfCreationProperty() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation.set(dateOfCreation);
    }
}

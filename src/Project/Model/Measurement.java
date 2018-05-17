package Project.Model;

public class Measurement {
    private int upperArmL, upperArmR, forearmL, forearmR, thighL, thighR, calfL, calfR,
    waist, shoulderWidth, chestWidth;
    private String date;

    public Measurement(int upperArmL, int upperArmR, int forearmL, int forearmR,
                       int thighL, int thighR, int calfL, int calfR,
                       int waist, int shoulderWidth, int chestWidth, String date) {
        this.upperArmL = upperArmL;
        this.upperArmR = upperArmR;
        this.forearmL = forearmL;
        this.forearmR = forearmR;
        this.thighL = thighL;
        this.thighR = thighR;
        this.calfL = calfL;
        this.calfR = calfR;
        this.waist = waist;
        this.shoulderWidth = shoulderWidth;
        this.chestWidth = chestWidth;
        this.date = date;
    }

    public int getUpperArmL() {
        return upperArmL;
    }

    public void setUpperArmL(int upperArmL) {
        this.upperArmL = upperArmL;
    }

    public int getUpperArmR() {
        return upperArmR;
    }

    public void setUpperArmR(int upperArmR) {
        this.upperArmR = upperArmR;
    }

    public int getForearmL() {
        return forearmL;
    }

    public void setForearmL(int forearmL) {
        this.forearmL = forearmL;
    }

    public int getForearmR() {
        return forearmR;
    }

    public void setForearmR(int forearmR) {
        this.forearmR = forearmR;
    }

    public int getThighL() {
        return thighL;
    }

    public void setThighL(int thighL) {
        this.thighL = thighL;
    }

    public int getThighR() {
        return thighR;
    }

    public void setThighR(int thighR) {
        this.thighR = thighR;
    }

    public int getCalfL() {
        return calfL;
    }

    public void setCalfL(int calfL) {
        this.calfL = calfL;
    }

    public int getCalfR() {
        return calfR;
    }

    public void setCalfR(int calfR) {
        this.calfR = calfR;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getShoulderWidth() {
        return shoulderWidth;
    }

    public void setShoulderWidth(int shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public int getChestWidth() {
        return chestWidth;
    }

    public void setChestWidth(int chestWidth) {
        this.chestWidth = chestWidth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Measurement: " + getDate();
    }
}

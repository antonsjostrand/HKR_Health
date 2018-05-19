package Project.Model;

import javafx.beans.property.SimpleStringProperty;

public class DailyIntakeTable {

    private final SimpleStringProperty date, protein, carbs, fat, kcal;

    public DailyIntakeTable(String date, String protein, String carbs, String fat, String kcal) {
        this.date = new SimpleStringProperty(date);
        this.protein = new SimpleStringProperty(protein);
        this.carbs = new SimpleStringProperty(carbs);
        this.fat = new SimpleStringProperty(fat);
        this.kcal = new SimpleStringProperty(kcal);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getProtein() {
        return protein.get();
    }

    public SimpleStringProperty proteinProperty() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein.set(protein);
    }

    public String getCarbs() {
        return carbs.get();
    }

    public SimpleStringProperty carbsProperty() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs.set(carbs);
    }

    public String getFat() {
        return fat.get();
    }

    public SimpleStringProperty fatProperty() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat.set(fat);
    }

    public String getKcal() {
        return kcal.get();
    }

    public SimpleStringProperty kcalProperty() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal.set(kcal);
    }

    @Override
    public String toString() {
        return "DailyIntakeTable{" +
                "date=" + date +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fat=" + fat +
                ", kcal=" + kcal +
                '}';
    }
}

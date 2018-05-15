package Project.Model;

import javafx.beans.property.SimpleStringProperty;

public class NutritionTable {

    private final SimpleStringProperty name, protein, carbs, fat, kcal;

    public NutritionTable(String name, String protein, String carbs, String fat, String kcal) {
        this.name = new SimpleStringProperty(name);
        this.protein = new SimpleStringProperty(protein);
        this.carbs = new SimpleStringProperty(carbs);
        this.fat = new SimpleStringProperty(fat);
        this.kcal = new SimpleStringProperty(kcal);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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
}

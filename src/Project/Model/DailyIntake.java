package Project.Model;

public class DailyIntake {

    private double protein, fat, carbs, kcal;
    private String date;

    public DailyIntake(double protein, double fat, double carbs, double kcal, String date){
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.kcal = kcal;
        this.date = date;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

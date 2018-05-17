package Project.Model;

import Project.DatabaseConnection;

public class Nutrition {

    private String name;
    private int kcalPer100, proteinAmount, fatAmount, carbohydratesAmount;

    public Nutrition(String name, int kcalPer100, int proteinAmount, int fatAmount, int carbohydratesAmount){
            try {
                this.name = name;
                this.kcalPer100 = kcalPer100;
                this.proteinAmount = proteinAmount;
                this.fatAmount = fatAmount;
                this.carbohydratesAmount = carbohydratesAmount;
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcalPer100() {
        return kcalPer100;
    }

    public void setKcalPer100(int kcalPer100) {
        this.kcalPer100 = kcalPer100;
    }

    public int getProteinAmount() {
        return proteinAmount;
    }

    public void setProteinAmount(int proteinAmount) {
        this.proteinAmount = proteinAmount;
    }

    public int getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(int fatAmount) {
        this.fatAmount = fatAmount;
    }

    public int getCarbohydratesAmount() {
        return carbohydratesAmount;
    }

    public void setCarbohydratesAmount(int carbohydratesAmount) {
        this.carbohydratesAmount = carbohydratesAmount;
    }

    @Override
    public String toString() {
        return getName();
    }
}

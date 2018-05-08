package Project.Model;

import Project.DatabaseConnection;

public class Nutrition {

    private String name;
    private int kcalPer100, nutritionID;
    private double proteinAmount, fatAmount, carbohydratesAmount;

    public Nutrition(String name, int kcalPer100, double proteinAmount, double fatAmount, double carbohydratesAmount){
            try {
                this.name = name;
                this.kcalPer100 = kcalPer100;
                this.proteinAmount = proteinAmount;
                this.fatAmount = fatAmount;
                this.carbohydratesAmount = carbohydratesAmount;
                this.nutritionID = DatabaseConnection.getInstance().retrieveBiggestID("nutritionID", "nutrition");
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}

package Project.Model;

public class Nutrition {

    private String name;
    private int kcalPer100, nutritionID = 0;
    private double proteinAmount, fatAmount, carbohydratesAmount;

    public Nutrition(String name, int kcalPer100, double proteinAmount, double fatAmount, double carbohydratesAmount){
            this.name = name;
            this.kcalPer100 = kcalPer100;
            this.proteinAmount = proteinAmount;
            this.fatAmount = fatAmount;
            this.carbohydratesAmount = carbohydratesAmount;
            this.nutritionID = nutritionID++;
    }
}

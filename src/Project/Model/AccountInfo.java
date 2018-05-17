package Project.Model;

public class AccountInfo {
    private int height, weight, age;
    private String date;

    public AccountInfo(int height, int weight, int age, String date) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.date = date;
    }


    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AccountInfo: " + getDate() ;
    }
}

package Project.Model;

public class AccountInfo {
    private int height, weight, age;

    public AccountInfo(int height, int weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }
}

package Project.Model;

public class Diary {
    private String date;
    private String dailyUpdate;


    public Diary(String date, String dailyUpdate) {
        this.date = date;
        this.dailyUpdate = dailyUpdate;
    }

    @Override
    public String toString() {
        return date + '\n' +
                dailyUpdate;



    }
}

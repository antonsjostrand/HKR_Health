package Project.Model;

public class Note {

    private String header, content, date;

    public Note(String header, String content, String date){
        this.header = header;
        this.content = content;
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Header: " + getHeader();
    }
}

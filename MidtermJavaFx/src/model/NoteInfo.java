package model;

public class NoteInfo {

    private String type;
    private String title;
    private String note;
    private String date;

    public NoteInfo(String type, String title, String note, String date)
    {
        this.type = type;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString()
    {
        return "("+ type + ") -" + title + "\n" + note ;
    }
}

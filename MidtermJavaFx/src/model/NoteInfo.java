package model;

public class NoteInfo {

    private String type;
    private String title;
    private String note;

    public NoteInfo(String type, String title, String note)
    {
        this.type = type;
        this.title = title;
        this.note = note;
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

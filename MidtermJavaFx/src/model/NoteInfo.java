package model;

import javafx.scene.text.Text;

public class NoteInfo{

    private String type;
    private Text title;
    private Text note;
    private String date;

    public NoteInfo(String type, Text title, Text note, String date)
    {
        this.type = type;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setNote(Text note) {
        this.note = note;
    }

    public Text getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString()
    {
        return "("+ type + ") -" + title + "\n" + note ;
    }

    public Text getTitle() {
        return title;
    }
}

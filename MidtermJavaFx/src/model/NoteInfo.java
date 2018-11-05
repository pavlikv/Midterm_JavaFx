package model;

import javafx.scene.control.Label;

public class NoteInfo implements INote{

    private String type;
    private String title;
    private Label note;
    private String date;

    public NoteInfo(String type, String title, Label note, String date)
    {
        this.type = type;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setNote(Label note) {
        this.note = note;
    }

    public Label getNote() {
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

    @Override
    public String getTitle() {
        return title;
    }
}

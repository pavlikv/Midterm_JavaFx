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

    public void setNote(Text note) {
        this.note = note;
    }

    public Text getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public Text getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "NoteInfo{" +
                "type='" + type + '\'' +
                ", title=" + title +
                ", note=" + note +
                ", date='" + date + '\'' +
                '}';
    }
}

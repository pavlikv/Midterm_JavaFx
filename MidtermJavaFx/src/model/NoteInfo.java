package model;

import javafx.scene.control.Label;

/**
 * @author Zach Kunitsa, Pavel Vaschuk
 * @version 1.0
 */
public class NoteInfo implements INote{

    private String type;
    private String title;
    private Label note;
    private String date;

    /**
     * @param type type
     * @param title title
     * @param note note
     * @param date date
     */
    public NoteInfo(String type, String title, Label note, String date)
    {
        this.type = type;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    /**
     * @param note set note
     */
    public void setNote(Label note) {
        this.note = note;
    }

    /**
     * @return note
     */
    public Label getNote() {
        return note;
    }


    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "NoteInfo{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", note=" + note +
                ", date='" + date + '\'' +
                '}';
    }
}

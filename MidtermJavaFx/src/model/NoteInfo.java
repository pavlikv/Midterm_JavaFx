package model;

import javafx.scene.text.Text;

/**
 * @author Zach Kunitsa, Pavel Vaschuk
 * @version 1.0
 */
public class NoteInfo{

    private String type;
    private Text title;
    private Text note;
    private String date;

    /**
     * constructor
     * @param type type
     * @param title title
     * @param note note
     * @param date date
     */
    public NoteInfo(String type, Text title, Text note, String date)
    {
        this.type = type;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    /**
     * @param note set note
     */
    public void setNote(Text note) {
        this.note = note;
    }

    /**
     * @return note
     */
    public Text getNote() {
        return note;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * @return title
     */
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
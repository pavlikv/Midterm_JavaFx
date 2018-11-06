package model;

import javafx.scene.text.Text;

/**
 * @author Zach Kunitsa, Pavel Vaschuk
 * @version 1.0
 */
public class ToDoNoteInfo{

    private Text title;
    private String completed;

    /**
     * @param title title of note
     * @param completed completed to do
     */
    public ToDoNoteInfo(Text title, String completed) {
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ToDoNoteInfo{" +
                "title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    /**
     * @return title
     */
    public Text getTitle() {
        return title;
    }
}
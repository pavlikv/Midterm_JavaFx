package model;


/**
 * @author Zach Kunitsa, Pavel Vaschuk
 * @version 1.0
 */
public class ToDoNoteInfo implements INote{

    private String title;
    private String completed;


    /**
     * @param title title of note
     * @param completed completed to do
     */
    public ToDoNoteInfo(String title, String completed) {
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

    @Override
    public String getTitle() {
        return title;
    }
}

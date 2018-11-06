package model;

import javafx.scene.text.Text;

public class ToDoNoteInfo{

    private Text title;
    private String completed;


    public ToDoNoteInfo(Text title, String completed) {
        this.title = title;
        this.completed = completed;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public void setCompleted(String changeTo){
        completed = changeTo;
    }

    public String getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "ToDoNoteInfo{" +
                "title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    public Text getTitle() {
        return title;
    }
}

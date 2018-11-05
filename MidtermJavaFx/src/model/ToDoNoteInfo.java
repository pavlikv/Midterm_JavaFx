package model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.control.CheckBox;

public class ToDoNoteInfo implements INote{

    private String title;
    private String completed;


    public ToDoNoteInfo(String title, String completed) {
        this.title = title;
        this.completed = completed;
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

    @Override
    public String getTitle() {
        return title;
    }
}

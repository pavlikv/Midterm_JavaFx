package model;

public class ToDoNoteInfo implements INote{

    private String title;
    private Boolean completed;


    public ToDoNoteInfo(String title, Boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public Boolean getCompleted() {
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

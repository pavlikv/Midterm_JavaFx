package model;

public class NoteInfo {

    private String type;
    private String title;

    public NoteInfo(String type, String title)
    {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString()
    {
        return type + " - " + title;
    }
}

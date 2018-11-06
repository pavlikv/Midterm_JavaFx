package controller;

import javafx.collections.ObservableList;
import model.DBData;
import model.NoteInfo;
import model.ToDoNoteInfo;


/**
 * @author Zach Kunitsa, Pavel Vaschuk
 * @version 1.0
 */
public class Controller
{
    private DBData model;

    /**
     * constructor
     */
    public Controller(){
        model = new DBData();
    }

    /**
     * @param type pass in type of note
     * @param title pass in title of note
     * @param note pass in actual note
     * @return true if note is added
     */
    public boolean handleNewNote(String type, String title, String note){
        if(type == null || type.equals("")) {
            return false;
        }
        model.addNote(type,title,note);
        return true;
    }

    /**
     * @param title title
     * @return true if note is deleted
     */
    public boolean handleDeleteNote(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.removeNote(title);
        return true;
    }

    /**
     * @return the note
     */
    public ObservableList<NoteInfo> handleGetNotes(){
        return model.getNotes();
    }


    /**
     * @param title title
     * @return true if added to do note
     */
    public boolean handleNewToDoItem(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.addToDoNote(title);
        return true;
    }

    /**
     * @param title title
     * @return return true if to do was removed
     */
    public boolean handleRemoveToDoItem(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.removeToDoNote(title);
        return true;
    }

    /**
     * @param title title
     * @return true if marked as completed
     */
    public boolean handleSetToCompleted(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.markAsComplete(title);
        return true;
    }

    /**
     * @return to do note
     */
    public ObservableList<ToDoNoteInfo> handleGetToDo(){

        return model.getToDONotes();
    }

    @Override
    public String toString() {
        return "Controller{" +
                "model=" + model +
                '}';
    }
}
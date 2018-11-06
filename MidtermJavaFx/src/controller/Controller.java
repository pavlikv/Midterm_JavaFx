package controller;

import javafx.collections.ObservableList;
import model.DBData;
import model.NoteInfo;
import model.ToDoNoteInfo;

/**
 * view class that builds the scene
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
     * @param type type of note
     * @param title title of note
     * @param note actual note
     * @return true if new note added
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
     * @return true if deleted note
     */
    public boolean handleDeleteNote(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.removeNote(title);
        return true;
    }

    /**
     * @return note
     */
    public ObservableList<NoteInfo> handleGetNotes(){
        return model.getNotes();
    }


    /**
     * @param title title
     * @return true if there is a new to do item
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
     * @return true of to do item removed
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
     * @return true if to do is completed
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
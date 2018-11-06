package controller;

import javafx.collections.ObservableList;
import model.DBData;
import model.NoteInfo;
import model.ToDoNoteInfo;

/**
 * view class that builds the scene
 * @author Zach Kunitsa, Pavel Vashchuk
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
     */
    public void handleNewNote(String type, String title, String note){
        if(type == null || type.equals("")) {
            System.out.println("Invalid newNote");
        } else {
            model.addNote(type, title, note);
        }

    }

    /**
     * @param title title of the item to remove
     */
    public void handleDeleteNote(String title){
        if(title == null || title.equals("")) {
            System.out.println("Invalid Delete Note");
        } else {
            model.removeNote(title);
        }
    }

    /**
     * @return note
     */
    public ObservableList<NoteInfo> handleGetNotes(){
        return model.getNotes();
    }


    /**
     * @param title title of the item to remove
     */
    public void handleNewToDoItem(String title){
        if(title == null || title.equals("")) {
            System.out.println("Invalid new to-do item");
        } else {
            model.addToDoNote(title);
        }
    }

    /**
     * @param title title of the item to remove
     */
    public void handleRemoveToDoItem(String title){
        if(title == null || title.equals("")) {
            System.out.println("Invalid newNote");
        } else {
            model.removeToDoNote(title);
        }
    }

    /**
     * @param title title of the item to remove
     */
    public void handleSetToCompleted(String title){
        if(title == null || title.equals("")) {
            System.out.println("Invalid newNote");
        } else {
            model.markAsComplete(title);
        }
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
package controller;

import javafx.collections.ObservableList;
import model.DBData;
import model.NoteInfo;
import model.ToDoNoteInfo;


public class Controller
{
    private DBData model;

    public Controller(){
        model = new DBData();
    }

    public boolean handleNewNote(String type, String title, String note){
        if(type == null || type.equals("")) {
            return false;
        }
        model.addNote(type,title,note);
        return true;
    }

    public boolean handleDeleteNote(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.removeNote(title);
        return true;
    }

    public ObservableList<NoteInfo> handleGetNotes(){
        return model.getNotes();
    }



    public boolean handleNewToDoItem(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.addToDoNote(title);
        return true;
    }

    public boolean handleRemoveToDoItem(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.removeToDoNote(title);
        return true;
    }

    public boolean handleSetToCompleted(String title){
        if(title == null || title.equals("")) {
            return false;
        }
        model.markAsComplete(title);
        return true;
    }

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
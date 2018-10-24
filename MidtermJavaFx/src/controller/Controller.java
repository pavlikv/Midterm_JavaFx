package controller;

import model.DBData;
import model.NoteInfo;

import java.util.List;

public class Controller
{
    private DBData model;

    public Controller(){
        model = new DBData();
    }

    public boolean handleNewNote(String type, String title, String note){
        //preform a little validation
        if(type == null || type.equals("")) {
            return false;
        }
        model.addNote(type,title,note);
        return true;
    }

    public boolean handleDeleteNote(String title){
        //preform a little validation
        if(title == null || title.equals("")) {
            return false;
        }
        model.removeNote(title);
        return true;
    }

    public List<NoteInfo> handleGetNotes(){
        return model.getNotes();
    }

}
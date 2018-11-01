package model;


import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBData {

    private Connection conn;

    public DBData(){
        try {
            //get connected to the database
            this.conn = DriverManager.getConnection("jdbc:sqlite:MidtermJavaFx.sqlite");
            Class.forName("org.sqlite.JDBC"); //fix out project path
            System.out.println("Connected to MidtermDB");

        } catch (SQLException | ClassNotFoundException e) {
            //rethrow our exception if we cannot connect
            throw new IllegalStateException("Cannot connect to model.db: " + e.getMessage());
        }
    }

    public void addNote(String type, String title, String note){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO notes VALUES ('" +
                    type + "','" +
                    title + "','" +
                    note + "', null,'"+ dateFormat.format(date) +"')"
                    );
        } catch(SQLException e){
            throw new IllegalStateException("Cannot insert note: " + e.getMessage());
        }
    }

    public void removeNote(String title){
        try
        {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM notes WHERE title='"+title+"'");

        } catch(SQLException e){
            throw new IllegalStateException("Cannot remove note: " + e.getMessage());
        }
    }

    public List<NoteInfo> getNotes() {

        try {

            ResultSet results = conn.createStatement().executeQuery(
                    "SELECT title, type, note, date FROM notes");

            List<NoteInfo> list = new ArrayList<>();
            while(results.next()){ //move to the next row and return true if successful
                String type = results.getString("type");
                String title = results.getString("title");
                String note = results.getString("note");
                String date = results.getString("date");


                list.add(new NoteInfo(type,title,note,date));
            }
            return list;

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot retrieve notes: " + e.getMessage());
        }
    }

    public void addToDoNote(String title){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO todonotes VALUES (null,'" +
                    title + "','" +
                    ",false')"
            );
        } catch(SQLException e){
            throw new IllegalStateException("Cannot insert todo note: " + e.getMessage());
        }
    }

    public void removeToDoNote(String title){
        try
        {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM todonotes WHERE title='"+title+"'");

        } catch(SQLException e){
            throw new IllegalStateException("Cannot remove todo note: " + e.getMessage());
        }
    }

    public void markAsComplete(String title){
        try
        {
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE todonotes SET completed = 'true' " +
                    "WHERE title='" + title +"'");

        } catch(SQLException e){
            throw new IllegalStateException("Cannot set to true: " + e.getMessage());
        }
    }

    public List<ToDoNoteInfo> getToDONotes() {

        try {

            ResultSet results = conn.createStatement().executeQuery(
                    "SELECT title, completed FROM todonotes");

            List<ToDoNoteInfo> list = new ArrayList<>();
            while(results.next()){ //move to the next row and return true if successful
                Boolean type = results.getBoolean("completed");
                String title = results.getString("title");

                list.add(new ToDoNoteInfo(title,type));
            }
            return list;

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot retrieve todo notes: " + e.getMessage());
        }
    }


}

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Zach Kunitsa, Pavel Vaschuk
 * @version 1.0
 */
public class DBData {

    public static final int TITLE_WRAPPING = 140;
    public static final int CODE_WRAPPING = 220;
    public static final int OTHER_TYPE_WRAPPING = 420;
    public static final int QUOTE_FONT_SIZE = 12;
    public static final int WRAPPING_VALUE = 580;
    private Connection conn;

    /**
     * connect to database
     */
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

    /**
     * @param type add type of note
     * @param title add title of not
     * @param note add note itself
     */
    public void addNote(String type, String title, String note){
        try {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter
                    .ofPattern("yyyy/MM/dd HH:mm");

            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO notes VALUES ('" +
                    type + "','" +
                    title + "','" +
                    note + "', null,'"+ time.format(dateFormatter) +"')"
            );
        } catch(SQLException e){
            throw new IllegalStateException("Cannot insert note: " + e.getMessage());
        }
    }

    /**
     * @param title remove note with title
     */
    public void removeNote(String title){
        try
        {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM notes WHERE title='"+title+"'");

        } catch(SQLException e){
            throw new IllegalStateException("Cannot remove note: " + e.getMessage());
        }
    }

    /**
     * @return the note
     */
    public ObservableList<NoteInfo> getNotes() {

        try {

            ResultSet results = conn.createStatement().executeQuery(
                    "SELECT title, type, note, date FROM notes");

            ObservableList<NoteInfo> list = FXCollections.observableArrayList();
            while(results.next()){ //move to the next row and return true if successful
                String type = results.getString("type");
                String titleAsString = results.getString("title");
                Text title = new Text(titleAsString);
                String noteAsString = results.getString("note");
                Text note = new Text(noteAsString);
                String date = results.getString("date");

                title.wrappingWidthProperty().setValue(TITLE_WRAPPING);
                if(type.equals("Code")){
                    note.wrappingWidthProperty().setValue(CODE_WRAPPING);
                }else {
                    note.wrappingWidthProperty().setValue(OTHER_TYPE_WRAPPING);
                }


                if(type.equals("Quote")){
                    note.setFont(Font.font("Verdana", FontPosture.ITALIC, QUOTE_FONT_SIZE));
                } else if(type.equals("URL")){
                    note.setUnderline(true);
                }

                list.add(new NoteInfo(type,title,note,date));
            }
            return list;

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot retrieve notes: " + e.getMessage());
        }
    }

    /**
     * add TO-DO note
     * @param title title
     */
    public void addToDoNote(String title){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO todonotes VALUES (null,'" +
                    title + "','" +
                    "false')"
            );
        } catch(SQLException e){
            throw new IllegalStateException("Cannot insert todo note: " + e.getMessage());
        }
    }

    /**
     * remove TO-DO note
     * @param title title
     */
    public void removeToDoNote(String title){
        try
        {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM todonotes WHERE title='"+title+"'");

        } catch(SQLException e){
            throw new IllegalStateException("Cannot remove todo note: " + e.getMessage());
        }
    }

    /**
     * Mark a TO-DO note as completed
     * @param title title
     */
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

    /**
     * @return TO-DO notes
     */
    public ObservableList<ToDoNoteInfo> getToDONotes() {

        try {

            ResultSet results = conn.createStatement().executeQuery(
                    "SELECT title, completed FROM todonotes");

            ObservableList<ToDoNoteInfo> list = FXCollections.observableArrayList();
            while(results.next()){ //move to the next row and return true if successful
                String completed = results.getString("completed");
                if(completed.equals("0")){
                    completed = "false";
                }
                Text title = new Text(results.getString("title"));
                title.wrappingWidthProperty().setValue(WRAPPING_VALUE);

                list.add(new ToDoNoteInfo(title,completed));
            }
            return list;

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot retrieve todo notes: " + e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "DBData{" +
                "conn=" + conn +
                '}';
    }
}
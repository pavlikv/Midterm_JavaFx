package model;

import java.sql.*;
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
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO notes VALUES (" +
                    type + "," +
                    title + "," +
                    note + ", null)"
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
            throw new IllegalStateException("Cannot insert note: " + e.getMessage());
        }
    }

    public List<NoteInfo> getNotes() {
        //todo..
        List<NoteInfo> list = new ArrayList<>();
        return list;
    }

}

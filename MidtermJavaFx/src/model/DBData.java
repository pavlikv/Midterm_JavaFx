package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public void addNote(){
        //todo ..
    }

    public void removeNote(){
        //TODO..
    }

    public List<NoteInfo> getNotes() {
        //todo..
        List<NoteInfo> list = new ArrayList<>();
        return list;
    }

}

package view;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.NoteInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAppUI extends Application {

    private Controller controller = new Controller();
    private TableView table = new TableView();

    @Override
    public void start(Stage stage) {
        stage.setScene(getScene());
        stage.setTitle("Notes");
        stage.show();
    }

    private Scene getScene()
    {
        HBox mainPanel = new HBox();
        mainPanel.setPadding(new Insets(10));
        mainPanel.setSpacing(100);

        mainPanel.setStyle("-fx-background-color: rgba(0,0,0,0.48)");
        mainPanel.getChildren().addAll(dataInputScreen(),dataViewScreen());
        table.refresh();
        return new Scene(mainPanel, 1000, 600);
    }

    private HBox dataViewScreen(){
        HBox mainPanel = new HBox();
        VBox vPanel = new VBox();
        Label label = new Label("Notes");
        label.setFont(Font.font("Arial", FontWeight.BOLD,20));

        //table.setEditable(true);
        table.setPrefWidth(350);
        table.setPrefHeight(490);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));


        table.getColumns().addAll(titleCol, typeCol, dateCol);

        //add the data from the database to the table
//        NoteInfo test = new NoteInfo("quote","this is the title","body of the note");
//        table.getItems().add(test);

        addDataToTable(controller.handleGetNotes());

        vPanel.setSpacing(5);
        vPanel.setPadding(new Insets(25, 0, 0, 10));
        vPanel.getChildren().addAll(label, table);

        //controller.handleNewNote("quote","title2","body of note2");
        mainPanel.getChildren().add(vPanel);
        return mainPanel;
    }

    private void addDataToTable(List<NoteInfo> notes){
        for( NoteInfo note : notes){
            table.getItems().add(note);
        }
    }

    private void addDataToTable(NoteInfo note){
        table.getItems().add(note);
    }

    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();

        return dateFormat.format(date);
    }

    private HBox dataInputScreen(){
        HBox panel = new HBox();
        panel.setAlignment(Pos.TOP_RIGHT);
        VBox Vpanel = new VBox();

        Vpanel.setSpacing(10);

        TextField title = new TextField();
        TextArea note = new TextArea();
        note.setPrefWidth(450);
        note.setPrefHeight(500);

        ComboBox<String> list = new ComboBox<>();
        list.getItems().addAll("Quote", "URL", "Code", "TO-DO");

        //whatever the type is, change styles and other preferences.
        list.valueProperty().addListener((observable, oldValue, newValue) -> {
            String noteTypeSelected = list.getSelectionModel().getSelectedItem().toString();
            switch (noteTypeSelected){
                case "Quote":
                    //quotations
                    //italics
                    break;
                case "URL":
                    //make as a link
                    break;
                case "Code":
                    //dark background
                    //code style
                    break;
                case "TO-DO":
                    //change to a to-do style note
                    break;

            }
        });

        Button save = new Button("Save");
        save.setPrefHeight(30);
        save.setPrefWidth(100);


        Vpanel.getChildren().addAll(
                createTextInput("Type: ", list),
                createTextInput("Title:  ", title),
                createTextInput("Note: ", note),
                save);

        save.setOnAction(event -> {
            NoteInfo newNote = new NoteInfo(list.getSelectionModel().getSelectedItem().toString(),
                    title.getText(),note.getText(),getDate());
            controller.handleNewNote(newNote.getType(), newNote.getTitle(), newNote.getNote());
            addDataToTable(newNote);
        });
        panel.getChildren().addAll(Vpanel);
        return panel;
    }
    private HBox createTextInput(String prompt, Node inputElement)
    {
        Label label = new Label(prompt);
        label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,15));
        HBox panel = new HBox();
        panel.getChildren().addAll(label, inputElement);
        return panel;
    }
}

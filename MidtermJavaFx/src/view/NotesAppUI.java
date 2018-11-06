package view;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.NoteInfo;
import model.ToDoNoteInfo;
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotesAppUI extends Application {

    private Controller controller = new Controller();
    private TableView<NoteInfo> noteTable = new TableView<>();
    private TableView<ToDoNoteInfo> toDoTable = new TableView<>();

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
        mainPanel.setSpacing(50);

        mainPanel.setStyle("-fx-background-color: rgba(0,0,0,0.48)");
        loadNoteTable();
        loadToDoTable();

        mainPanel.getChildren().addAll(dataInputScreen(),tabView());
        return new Scene(mainPanel, 1300, 600);
    }

    private VBox tabView(){
        String[] tabOptions = {"Notes", "To-Do"};
        VBox panel = new VBox();
        TabPane tabPane = new TabPane();

        for (String tabName: tabOptions) {
            Tab tab = new Tab();
            tab.setText(tabName);
            if(tabName.equals("Notes")){
                tab.setContent(noteTable);
            } else {
                tab.setContent(toDoTable);
            }
            tabPane.getTabs().add(tab);
        }
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Button markAsComplete = new Button("Mark as Complete");
        markAsComplete.setOnAction(event -> {
            ToDoNoteInfo selectedItemFromToDo = toDoTable.getSelectionModel().getSelectedItem();
            if(selectedItemFromToDo != null){
                //((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
                //toDoTable.getItems().get(toDoTable.getSelectionModel().getFocusedIndex()).setCompleted("true");
                controller.handleSetToCompleted(selectedItemFromToDo.getTitle().getText());
//                toDoTable.getColumns().set(toDoTable.getSelectionModel().getSelectedIndex(),)
            }

        });
        Button delete = new Button("Delete Selected");
        delete.setOnAction(event -> {
            NoteInfo selectedNotesItem = noteTable.getSelectionModel().getSelectedItem();
            ToDoNoteInfo selectedItemFromToDo = toDoTable.getSelectionModel().getSelectedItem();
            if(selectedItemFromToDo != null){
                toDoTable.getItems().remove(selectedItemFromToDo);
                controller.handleRemoveToDoItem(selectedItemFromToDo.getTitle().getText());
            }
            if(selectedNotesItem != null){
                noteTable.getItems().remove(selectedNotesItem);
                controller.handleDeleteNote(selectedNotesItem.getTitle().getText());
            }
        });

        panel.getChildren().addAll(tabPane,delete,markAsComplete);
        return panel;
    }

    private void loadNoteTable(){
        noteTable.setPrefWidth(720);
        noteTable.setPrefHeight(520);
        noteTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(150);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn noteCol = new TableColumn("Note");
        noteCol.setMinWidth(430);
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(120);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        noteTable.getColumns().addAll(titleCol, noteCol, dateCol);

        addDataToTableNotes(controller.handleGetNotes());
    }

    private void loadToDoTable(){
        toDoTable.setPrefWidth(720);
        toDoTable.setPrefHeight(520);
        toDoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(600);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn completedCol = new TableColumn("Completed");
        completedCol.setMinWidth(100);
        completedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));

        toDoTable.getColumns().addAll(titleCol, completedCol);

        addDataToTableToDo(controller.handleGetToDo());
    }

    private void addDataToTableToDo(ObservableList<ToDoNoteInfo> notes){

        for( ToDoNoteInfo note : notes){
            toDoTable.getItems().add(note);
        }
    }

    private void addDataToTableToDo(ToDoNoteInfo note){
        note.getTitle().wrappingWidthProperty().setValue(580);
        toDoTable.getItems().add(note);
    }

    private void addDataToTableNotes(ObservableList<NoteInfo> notes){
        for( NoteInfo note : notes){
            noteTable.getItems().add(note);
        }
    }

    private void addDataToTableNotes(NoteInfo note){
        note.getTitle().wrappingWidthProperty().setValue(140);
        if(note.getType().equals("Code")){
            note.getNote().wrappingWidthProperty().setValue(220);
        }else {
            note.getNote().wrappingWidthProperty().setValue(420);
        }

        if(note.getType().equals("URL")){
//            Runtime runtime = Runtime.getRuntime();
//            Desktop desktop = Desktop.getDesktop();
//            try{
//
//                desktop.browse(new URI(note.getNote().getText()));
//                runtime.exec(note.getNote().getText());
//
//
//            } catch (URISyntaxException|IOException e){
//
//            }

        }
        noteTable.getItems().add(note);
    }

    private String getDate(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm");

        return time.format(dateFormatter);
    }

    private HBox dataInputScreen(){
        HBox panel = new HBox();
        panel.setAlignment(Pos.TOP_RIGHT);
        VBox vPanel = new VBox();

        vPanel.setSpacing(10);

        TextField title = new TextField();
        TextArea note = new TextArea();
        note.setPrefWidth(450);
        note.setPrefHeight(500);
        note.setWrapText(true);

        ComboBox<String> list = new ComboBox<>();
        list.getItems().addAll("Quote", "URL", "Code", "TO-DO");

        //whatever the type is, change styles and other preferences.
        list.valueProperty().addListener((observable, oldValue, newValue) -> {
            String noteTypeSelected = list.getSelectionModel().getSelectedItem().toString();
            if(noteTypeSelected.equals("Code")){
                //change style for the code type
                note.setFont(Font.font(java.awt.Font.MONOSPACED, 18));
            }
        });

        Button save = new Button("Save");
        save.setPrefHeight(30);
        save.setPrefWidth(100);
        vPanel.getChildren().addAll(
                createTextInput("Type: ", list),
                createTextInput("Title:  ", title),
                createTextInput("Note: ", note),
                save);

        save.setOnAction(event -> {
            String noteTypeSelected = list.getSelectionModel().getSelectedItem();
            if(noteTypeSelected.equals("TO-DO")){
                ToDoNoteInfo newToDoNote = new ToDoNoteInfo(new Text(title.getText()),"false");
                controller.handleNewToDoItem(title.getText());
                addDataToTableToDo(newToDoNote);
            } else {
                NoteInfo newNote = new NoteInfo(noteTypeSelected,
                        new Text (title.getText()),new Text(note.getText()),getDate());
                if(noteTypeSelected.equals("Quote")){
                        Text quoteLabel = new Text("\"" + newNote.getNote().getText() + "\"");
                        quoteLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
                        newNote.setNote(quoteLabel);
                        controller.handleNewNote(newNote.getType(), newNote.getTitle().getText(), quoteLabel.getText());
                } else {
                    controller.handleNewNote(newNote.getType(), newNote.getTitle().getText(), note.getText());
                }
                addDataToTableNotes(newNote);
            }

        });
        panel.getChildren().addAll(vPanel);
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

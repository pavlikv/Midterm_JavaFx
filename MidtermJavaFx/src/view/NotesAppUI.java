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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * view class that builds the scene
 * @author Zach Kunitsa, Pavel Vashchuk
 * @version 1.0
 */
public class NotesAppUI extends Application {

    public static final int SCENE_WIDTH = 1300;
    public static final int SCENE_HEIGHT = 600;
    public static final int SCENE_SPACING = 50;
    public static final int NOTE_TABLE_WIDTH = 720;
    public static final int NOTE_TABLE_HEIGHT = 520;
    public static final int TITLE_COL_WIDTH = 150;
    public static final int NOTE_COL_WIDTH = 430;
    public static final int DATE_COL_WIDTH = 120;
    public static final int TITLE_COL_WIDTH_TODO = 600;
    public static final int COMPLETED_COL_WIDTH_TODO = 100;
    public static final int WRAPPING_VALUE = 580;
    public static final int TITLE_WRAPPING = 140;
    public static final int CODE_WRAPPING = 220;
    public static final int OTHER_TYPE_WRAPPING = 420;
    public static final int QUOTE_FONT_SIZE = 12;
    public static final int CODE_FONT_SIZE = 18;
    public static final int SAVE_BUTTON_HEIGHT = 30;
    public static final int SAVE_BUTTON_WIDTH = 100;
    public static final int NOTE_FIELD_WIDTH = 450;
    public static final int NOTE_FIELD_HEIGHT = 500;
    public static final int LABEL_FONT_SIZE = 15;

    private Controller controller = new Controller();
    private TableView<NoteInfo> noteTable = new TableView<>();
    private TableView<ToDoNoteInfo> toDoTable = new TableView<>();

    private ComboBox<String> list = new ComboBox<>();
    private Button markAsCompleteButton = new Button("Mark as Complete");
    private Button deleteButton = new Button("Delete Selected");
    private Button save = new Button("Save");
    private TextField titleField = new TextField();
    private TextArea noteField = new TextArea();

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
        mainPanel.setSpacing(SCENE_SPACING);

        mainPanel.setStyle("-fx-background-color: rgba(0,0,0,0.48)");
        loadNoteTable();
        loadToDoTable();

        mainPanel.getChildren().addAll(dataInputScreen(),tabView());
        return new Scene(mainPanel, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private VBox tabView(){
        String[] tabOptions = {"Notes", "To-Do"};
        VBox panel = new VBox();
        TabPane tabPane = new TabPane();
        HBox buttonPanel = new HBox();
        buttonPanel.setSpacing(10);

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

        markAsCompleteButton.setOnAction(event -> {
            ToDoNoteInfo selectedItemFromToDo = toDoTable.getSelectionModel().getSelectedItem();
            if(selectedItemFromToDo != null) {
                //toDoTable.getItems().get(toDoTable.getSelectionModel().getFocusedIndex()).setCompleted("true");
                controller.handleSetToCompleted(selectedItemFromToDo.getTitle().getText());
                //toDoTable.setStyle("");
            }
        });
        deleteButton.setOnAction(event -> {
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

        buttonPanel.getChildren().addAll(deleteButton,markAsCompleteButton);
        panel.setSpacing(10);
        panel.getChildren().addAll(tabPane,buttonPanel);
        return panel;
    }

    private void loadNoteTable(){
        noteTable.setPrefWidth(NOTE_TABLE_WIDTH);
        noteTable.setPrefHeight(NOTE_TABLE_HEIGHT);
        noteTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn titleCol = new TableColumn("Title");

        titleCol.setMinWidth(TITLE_COL_WIDTH);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn noteCol = new TableColumn("Note");
        noteCol.setMinWidth(NOTE_COL_WIDTH);
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(DATE_COL_WIDTH);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        noteTable.getColumns().addAll(titleCol, noteCol, dateCol);

        addDataToTableNotes(controller.handleGetNotes());
    }

    private void loadToDoTable(){
        toDoTable.setPrefWidth(NOTE_TABLE_WIDTH);
        toDoTable.setPrefHeight(NOTE_TABLE_HEIGHT);
        toDoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(TITLE_COL_WIDTH_TODO);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn completedCol = new TableColumn("Completed");
        completedCol.setMinWidth(COMPLETED_COL_WIDTH_TODO);
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
        note.getTitle().wrappingWidthProperty().setValue(WRAPPING_VALUE);
        toDoTable.getItems().add(note);
    }

    private void addDataToTableNotes(ObservableList<NoteInfo> notes){
        for( NoteInfo note : notes){
            noteTable.getItems().add(note);
        }
    }

    private void addDataToTableNotes(NoteInfo note){
        note.getTitle().wrappingWidthProperty().setValue(TITLE_WRAPPING);
        if(note.getType().equals("Code")){
            note.getNote().wrappingWidthProperty().setValue(CODE_WRAPPING);
        }else {
            note.getNote().wrappingWidthProperty().setValue(OTHER_TYPE_WRAPPING);
        }

        if(note.getType().equals("URL")){
            Text newUrlStyle = note.getNote();
            newUrlStyle.setUnderline(true);
            note.setNote(newUrlStyle);
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
        setNoteInputSize();

        setSaveButtonSize();

        list.getItems().addAll("Quote", "URL", "Code", "TO-DO");

        list.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(list.getSelectionModel().getSelectedItem().equals("Code")){
                noteField.setFont(Font.font(java.awt.Font.MONOSPACED, CODE_FONT_SIZE));
            } else {
                noteField.setFont(Font.font("Verdana", QUOTE_FONT_SIZE));
            }
            if(list.getSelectionModel().getSelectedItem().equals("TO-DO")){
                titleField.setVisible(false);
            } else {
                titleField.setVisible(true);
            }
        });

        vPanel.getChildren().addAll(
                createTextInput("Type: ", list),
                createTextInput("Title:  ", titleField),
                createTextInput("Note: ", noteField),
                save);

        save.setOnAction(event -> {
            String noteTypeSelected = list.getSelectionModel().getSelectedItem();
            if(noteTypeSelected.equals("TO-DO")){
                ToDoNoteInfo newToDoNote = new ToDoNoteInfo(new Text(noteField.getText()),"false");
                controller.handleNewToDoItem(noteField.getText());
                addDataToTableToDo(newToDoNote);
            } else {
                NoteInfo newNote = new NoteInfo(noteTypeSelected,
                        new Text (titleField.getText()),new Text(noteField.getText()),getDate());
                if(noteTypeSelected.equals("Quote")){
                    Text quoteLabel = new Text("\"" + newNote.getNote().getText() + "\"");
                    quoteLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, QUOTE_FONT_SIZE));
                    newNote.setNote(quoteLabel);
                    controller.handleNewNote(newNote.getType(), newNote.getTitle().getText(), quoteLabel.getText());
                } else {
                    controller.handleNewNote(newNote.getType(), newNote.getTitle().getText(), noteField.getText());
                }
                addDataToTableNotes(newNote);
            }

        });
        panel.getChildren().addAll(vPanel);
        return panel;
    }

    private void setSaveButtonSize(){
        save.setPrefHeight(SAVE_BUTTON_HEIGHT);
        save.setPrefWidth(SAVE_BUTTON_WIDTH);
    }
    private void setNoteInputSize(){
        noteField.setPrefWidth(NOTE_FIELD_WIDTH);
        noteField.setPrefHeight(NOTE_FIELD_HEIGHT);
        noteField.setWrapText(true);
    }

    private HBox createTextInput(String prompt, Node inputElement)
    {
        Label label = new Label(prompt);
        label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, LABEL_FONT_SIZE));
        HBox panel = new HBox();
        panel.getChildren().addAll(label, inputElement);
        return panel;
    }

    @Override
    public String toString() {
        return "NotesAppUI{" +
                "controller=" + controller +
                ", noteTable=" + noteTable +
                ", toDoTable=" + toDoTable +
                ", list=" + list +
                ", save=" + save +
                ", titleField=" + titleField +
                ", noteField=" + noteField +
                '}';
    }
}
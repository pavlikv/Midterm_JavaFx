package view;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotesAppUI extends Application {

    private Controller controller = new Controller();

    @Override
    public void start(Stage stage) {
        stage.setScene(getScene());
        stage.setTitle("Notes");
        stage.show();
    }

    private Scene getScene()
    {
        VBox mainPanel = new VBox();
        mainPanel.setPadding(new Insets(10));
        mainPanel.setSpacing(10);

        mainPanel.getChildren().addAll(dataInputScreen(),dataViewScreen());

        return new Scene(mainPanel, 1300, 600);
    }

    private HBox dataInputScreen(){
        HBox panel = new HBox();


        return panel;
    }

    private HBox dataViewScreen(){
        HBox panel = new HBox();


        return panel;
    }
}

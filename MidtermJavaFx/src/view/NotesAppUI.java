package view;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotesAppUI extends Application {

    private Controller controller = new Controller();

    @Override
    public void start(Stage stage) {
        stage.setScene(getScene());
        stage.setTitle("Working with MVC");
        stage.show();
    }

    private Scene getScene()
    {
        VBox mainPanel = new VBox();
        mainPanel.setPadding(new Insets(10));
        mainPanel.setSpacing(10);

        return new Scene(mainPanel, 600, 500);
    }
}

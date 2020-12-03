import candyselection.view.ForCheckBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private BorderPane pane;
    private VBox box;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        display();
        pane.setTop(box);
        Scene scene = new Scene(pane, 700, 500);
        setStage(primaryStage, scene);
    }

    private void setStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application");
        primaryStage.show();
    }

    private void display() throws IOException {
        ForCheckBox cb = new ForCheckBox();
        pane = new BorderPane();
        box = cb.getenvBox();
    }
}

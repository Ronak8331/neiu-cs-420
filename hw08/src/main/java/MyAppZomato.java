import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cuisines;
import restaurantdomain.model.Restaurant;
import restaurantdomain.view.DisplayCombo;
import java.io.IOException;
import java.net.URISyntaxException;

public class MyAppZomato extends Application {

    private DisplayCombo combo;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            combo = new DisplayCombo();
            BorderPane borderPane = new BorderPane();
            setBorderPane(borderPane);
            Scene scene = new Scene(borderPane,1000,500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Restaurants");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void setBorderPane(BorderPane borderPane) {
        HBox hBox = new HBox();
        setHbox(hBox);
        borderPane.setTop(hBox);
    }

    private void setHbox(HBox hBox) {
        hBox.setSpacing(15);
        ComboBox<Cuisines> cuisinesComboBox = combo.getCategories();
        ComboBox<Restaurant> restaurantComboBox = combo.getRestaurantComboBox();
        Text textBox = combo.getTextBox();
        hBox.getChildren().addAll(cuisinesComboBox,restaurantComboBox, textBox);
        HBox.setMargin(cuisinesComboBox,new Insets(30,5,5,10));
        HBox.setMargin(restaurantComboBox, new Insets(30,5,5,10));
        HBox.setMargin(textBox, new Insets(30,5,5,10));
    }
}

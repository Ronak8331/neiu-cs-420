import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cuisines;
import restaurantdomain.model.Restaurant;
import restaurantdomain.view.DisplayCombo;
import restaurantdomain.view.FirstPageRadioButtons;
import restaurantdomain.view.RadioButtons;
import java.io.IOException;
import java.net.URISyntaxException;

public class MyAppZomato extends Application {

    private DisplayCombo combo;
    private Stage window;
    private Scene firstScene;
    private Button backButton;
    private Scene secondScene;
    private BorderPane gPane;
    private Label gMessage;
    private VBox gVBox;
    private ToggleGroup tg;
    private RadioButtons grb;
    private HBox gHBox;
    private FirstPageRadioButtons firstPageRadioButtons;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            setFirstScene();
            window.setScene(firstScene);
            window.setTitle("This is First Screen");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private void setFirstScene() throws IOException, URISyntaxException, ParseException {
        combo = new DisplayCombo();
        BorderPane borderPane = new BorderPane();
        setBorderPane(borderPane);
        firstScene = new Scene(borderPane, 1000, 500);
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
        forRadioButton(hBox, cuisinesComboBox, restaurantComboBox, textBox);
        HBox.setMargin(cuisinesComboBox,new Insets(30,5,5,10));
        HBox.setMargin(restaurantComboBox, new Insets(30,5,5,10));
        HBox.setMargin(textBox, new Insets(30,5,5,10));
    }

    private void forRadioButton(HBox hBox, ComboBox<Cuisines> cuisinesComboBox, ComboBox<Restaurant> restaurantComboBox, Text textBox) {
        firstPageRadioButtons = new FirstPageRadioButtons();
        hBox.getChildren().addAll(cuisinesComboBox,firstPageRadioButtons.getRb1(),firstPageRadioButtons.getRb2(), restaurantComboBox, textBox);
        tg = new ToggleGroup();
        tg = firstPageRadioButtons.getTg();
        tg.selectedToggleProperty().addListener(new RadioButtonListener());
    }

    private class RadioButtonListener implements ChangeListener<Toggle>
    {
        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
        {
            if(newValue.equals(firstPageRadioButtons.getRb1()))
            {
                try {
                    setFirstScene();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    setSecondScene();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void setSecondScene() throws IOException, URISyntaxException, ParseException {
        initializeSecondSceneVariables();
        backButton.setOnAction(e -> {
            window.setScene(firstScene);
            firstPageRadioButtons.getRb1().setSelected(true);
        });
        designGPane();
        secondScene = new Scene(gPane,900,525);
        window.setScene(secondScene);
    }

    private void designGPane() {
        gPane.setTop(gMessage);
        gPane.setAlignment(gMessage, Pos.TOP_CENTER);
        gPane.setMargin(gMessage, new Insets(12,12,12,12));
        gPane.setCenter(gVBox);
        gPane.setBottom(backButton);
        gPane.setAlignment(backButton,Pos.BOTTOM_CENTER);
        gPane.setMargin(backButton, new Insets(12,12,12,12));
    }

    private void initializeSecondSceneVariables() throws IOException, URISyntaxException, ParseException {
        gPane = new BorderPane();
        grb = new RadioButtons();
        gHBox = grb.getGraphicHBox();
        gVBox = grb.getGVBox();
        backButton = new Button("Get Back to Home");
        gMessage = new Label("Graphics");
    }
}

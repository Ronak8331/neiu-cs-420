import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.*;
import restaurantdomain.view.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyAppZomato extends Application {

    private Stage window;
    private Scene firstScene, zeroScene;
    private Button backButton, welcomeButton,backButtonFirst;
    private BorderPane borderPane, borderPane1, borderPane2;
    private Label labelMessage;
    private VBox vBox1, vBox, vBox0;
    private RadioButtons radioButtons;
    private FirstPageComboBox firstPageComboBox;
    private CuisineComboBox cuisinesComboBox;
    private RatingsComboBox ratingsComboBox;
    private CityComboBox citiesComboBox;
    private ComboBox<FirstComboBox> combo1;
    private Text text;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            setZeroScene();
            window.setScene(zeroScene);
            window.setTitle("Food Hunt");
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setZeroScene() throws ParseException, IOException, URISyntaxException {
        zeroText();
        zeroButton();
        zeroBorder();
        zeroScene = new Scene(borderPane2,1200,500);
        setFirstScene();
    }

    private void zeroBorder() {
        borderPane2 = new BorderPane();
        borderPane2.setTop(text);
        borderPane2.setCenter(welcomeButton);
        borderPane2.setStyle("-fx-background-image: url('https://visualhunt.com/photos/1/muffin-sweet-bakery-treat.jpg?s=l');-fx-background-size: 1200, 500;-fx-background-repeat: no-repeat;");
    }

    private void zeroButton() {
        welcomeButton = new Button("Click here to continue");
        welcomeButton.setOnAction(e -> {
            window.setScene(firstScene);
        });
    }

    private void zeroText() {
        text = new Text();
        text.setFont(Font.font("Chiller", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setStrokeWidth(2);
        text.setStroke(Color.BLUE);
        text.setText("Welcome to Food Hunt");
    }

    private void setFirstScene() throws IOException, URISyntaxException, ParseException {
        firstSceneInitialization();
        HBox hBox = firstPageComboBox.getFirstHBox();
        vBox.getChildren().addAll(hBox, borderPane1);
        firstScene = new Scene(vBox, 1200, 500);
    }

    private void firstSceneInitialization() throws IOException, ParseException, URISyntaxException {
        paneInitialization();
        comboBoxInitialization();
    }

    private void paneInitialization() throws ParseException, IOException, URISyntaxException {
        cuisinesComboBox = new CuisineComboBox();
        ratingsComboBox = new RatingsComboBox();
        citiesComboBox = new CityComboBox();
        borderPane1 = new BorderPane();
        vBox = new VBox();
    }

    private void comboBoxInitialization() {
        firstPageComboBox = new FirstPageComboBox();
        combo1 = firstPageComboBox.getCombo1();
        combo1.setPromptText("---Select Category---");
        clickCombo();
    }

    private void clickCombo() {
        combo1.valueProperty().addListener(((observable, oldValue, newValue) -> {
            newValue = combo1.getSelectionModel().getSelectedItem();
            if(newValue.equals(FirstComboBox.RESTAURANTS_BY_CITY)){
                setUpCities(borderPane1);
            }
            if (newValue.equals(FirstComboBox.RESTAURANTS_BY_CUISINE)) {
                setUpCuisine(borderPane1);
            } else if (newValue.equals(FirstComboBox.RESTAURANTS_BY_RATING)) {
                setUpRatings(borderPane1);
            } else if(newValue.equals(FirstComboBox.BACK_TO_HOME)) {
                window.setScene(zeroScene);
            }
            else {
                try {
                    setSecondScene();
                } catch (IOException | ParseException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void setUpCuisine(BorderPane pane) {
        ComboBox<Cuisines> cuisineBox = cuisinesComboBox.getCategories();
        ListView<Restaurant> rest = cuisinesComboBox.getRestaurantComboBox();
        cuisinePane(pane, rest, cuisineBox);
    }

    private void cuisinePane(BorderPane pane, ListView<Restaurant> rest, ComboBox<Cuisines> comboBoxView) {
        pane.setTop(comboBoxView);
        BorderPane.setAlignment(comboBoxView, Pos.TOP_CENTER);
        BorderPane.setMargin(comboBoxView, new Insets(12, 12, 12, 12));
        pane.setCenter(rest);
    }

    private void setUpRatings(BorderPane pane) {
        ComboBox<Ratings> ratingBox = ratingsComboBox.getCategories();
        ListView<Restaurant> rest = ratingsComboBox.getRestaurantComboBox();
        ratingPane(pane, rest, ratingBox);
    }

    private void ratingPane(BorderPane pane, ListView<Restaurant> rest, ComboBox<Ratings> comboBoxView) {
        pane.setTop(comboBoxView);
        BorderPane.setAlignment(comboBoxView, Pos.TOP_CENTER);
        BorderPane.setMargin(comboBoxView, new Insets(12, 12, 12, 12));
        pane.setCenter(rest);
    }

    private void setUpCities(BorderPane pane) {
        ComboBox<Cities> cityBox = citiesComboBox.getCategories();
        ListView<Restaurant> rest = citiesComboBox.getRestaurantComboBox();
        cityPane(pane, rest, cityBox);
    }

    private void cityPane(BorderPane pane, ListView<Restaurant> rest, ComboBox<Cities> comboBoxView) {
        pane.setTop(comboBoxView);
        BorderPane.setAlignment(comboBoxView, Pos.TOP_CENTER);
        BorderPane.setMargin(comboBoxView, new Insets(12, 12, 12, 12));
        pane.setCenter(rest);
    }

    private void setSecondScene() throws IOException, URISyntaxException, ParseException {
        secondSceneInitialization();
        backButton.setOnAction(e -> {
            window.setScene(firstScene);
        });
        secondPane();
        Scene secondScene = new Scene(borderPane, 1200, 500);
        window.setScene(secondScene);
    }

    private void secondSceneInitialization() throws IOException, URISyntaxException, ParseException {
        secondRadioButton();
        vBox1 = radioButtons.getGVBox();
        labelMessage();
    }

    private void labelMessage() {
        backButton = new Button("Get Back to Home");
        labelMessage = new Label("Visuals");
    }

    private void secondRadioButton() throws IOException, URISyntaxException, ParseException {
        borderPane = new BorderPane();
        radioButtons = new RadioButtons();
    }

    private void secondPane() {
        labelDesign();
        borderPane.setCenter(vBox1);
        backButtonDesign();
    }

    private void backButtonDesign() {
        borderPane.setBottom(backButton);
        BorderPane.setAlignment(backButton, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(backButton, new Insets(12, 12, 12, 12));
    }

    private void labelDesign() {
        borderPane.setTop(labelMessage);
        BorderPane.setAlignment(labelMessage, Pos.TOP_CENTER);
        BorderPane.setMargin(labelMessage, new Insets(12, 12, 12, 12));
    }
}

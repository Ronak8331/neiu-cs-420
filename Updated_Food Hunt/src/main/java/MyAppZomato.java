import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Restaurant;
import restaurantdomain.view.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyAppZomato extends Application {

    private Stage window;
    private Scene firstScene, zeroScene;
    private Button welcomeButton;
    private BorderPane borderPane, borderPane1, borderPane2;
    private VBox vBox1, vBox;
    private HelperClassForListView costList;
    private HelperClassForListView deliveryList;
    private Text text;
    private MenuItem menu1, menu2, menu3, menu4, menu5, menu6, menu7;
    private Menu menu, menu10;
    private Graphs graphs;
    private GraphForDelivery graphFor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            scene0();
            window.setScene(zeroScene);
            window.setTitle("Food Hunt");
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scene0() throws ParseException, IOException, URISyntaxException {
        zeroText();
        zeroButton();
        zeroBorder();
        zeroScene = new Scene(borderPane2, 1200, 750);
        scene1();
    }

    private void zeroBorder() {
        borderPane2 = new BorderPane();
        borderPane2.setTop(text);
        borderPane2.setCenter(welcomeButton);
        borderPane2.setStyle("-fx-background-image: url('https://visualhunt.com/photos/1/muffin-sweet-bakery-treat.jpg?s=l');-fx-background-size: 1200, 500;-fx-background-repeat: no-repeat;");
    }

    private void zeroButton() {
        welcomeButton = new Button("Click here to continue");
        welcomeButton.setOnAction(e -> window.setScene(firstScene));
    }

    private void zeroText() {
        text = new Text();
        text.setFont(Font.font("Chiller", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setStrokeWidth(2);
        text.setStroke(Color.BLUE);
        text.setText("Welcome to Food Hunt");
    }

    private void scene1() throws IOException, ParseException, URISyntaxException {

        costList = new CostListView();
        deliveryList = new DeliveryListView();
        borderPane1 = new BorderPane();
        vBox = new VBox();
        menuNameFirstPage();
        actionListener();
        menuBarFirst();
    }

    private void menuBarFirst() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        vBox.getChildren().addAll(menuBar, borderPane1);
        firstScene = new Scene(vBox, 1200, 750);
    }

    private void actionListener() {
        addMenuItems();
        menu1.setOnAction(event -> setUpDeliveryCost(borderPane1, costList));
        menu4.setOnAction(event -> window.setScene(zeroScene));
        menu3.setOnAction(event -> {
            try {
                scene2();
            } catch (IOException | ParseException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        menu2.setOnAction(event -> setUpDeliveryCost(borderPane1, deliveryList));
    }

    private void addMenuItems() {
        menu.getItems().add(menu1);
        menu.getItems().add(menu2);
        menu.getItems().add(menu3);
        menu.getItems().add(menu4);
    }

    private void menuNameFirstPage() {
        menu1 = new MenuItem("Restaurant By Cost");
        menu2 = new MenuItem("Restaurant By Delivery");
        menu3 = new MenuItem("Visuals");
        menu4 = new MenuItem("Back to Home");
        menu = new Menu("Categories");
    }

    private void setUpDeliveryCost(BorderPane pane, HelperClassForListView comboBox) {
        ListView<Restaurant> rest = comboBox.getRestaurantListView();
        setPane(pane, rest, comboBox.getListView());
    }

    private void setPane(BorderPane pane, ListView<Restaurant> rest, ListView<? extends Object> nodes) {
        pane.setLeft(nodes);
        nodes.setPrefHeight(750);
        pane.setCenter(rest);
        rest.setPrefHeight(750);
    }

    private void scene2() throws IOException, ParseException, URISyntaxException {
        borderPane = new BorderPane();
        vBox1 = new VBox();
        graphs = new Graphs();
        graphFor = new GraphForDelivery();
        menuNameForSecondScene();
        actionForSecondScene();
        menuBar();
    }

    private void menuBar() {
        MenuBar menuBar2 = new MenuBar();
        menuBar2.getMenus().add(menu10);
        vBox1.getChildren().addAll(menuBar2, borderPane);
        Scene secondScene = new Scene(vBox1, 1200, 750);
        window.setScene(secondScene);
    }

    private void actionForSecondScene() {
        menu10.getItems().add(menu6);
        menu10.getItems().add(menu7);
        menu10.getItems().add(menu5);
        menu5.setOnAction(event -> window.setScene(firstScene));
        menu6.setOnAction(event -> {
            try {
                barChart(borderPane);
            } catch (IOException | ParseException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        menu7.setOnAction(event -> {
            try {
                barChartForDelivery(borderPane);
            } catch (IOException | ParseException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    private void menuNameForSecondScene() {
        menu5 = new MenuItem("Back to Home");
        menu6 = new MenuItem("Cost Bar Chart");
        menu7 = new MenuItem("Delivery Bar Chart");
        menu10 = new Menu("Select Visual");
    }

    private void barChart(BorderPane pane) throws IOException, ParseException, URISyntaxException {
        BarChart<String, Number> barChart = graphs.graphForCost();
        pane.setCenter(barChart);
    }

    private void barChartForDelivery(BorderPane pane) throws IOException, ParseException, URISyntaxException {
        BarChart<String, Number> barChart2 = graphFor.graphsForDelivery();
        pane.setCenter(barChart2);
    }
}

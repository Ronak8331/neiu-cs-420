package MyApp;
import api.generatePath;
import api.FoodHunt;
import collectionFx.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, URISyntaxException {
        primaryStage.setTitle("Cuisines according to City Name");
        ArrayList<String> allCities = new ArrayList<>();

        for(City c:City.values())
        {
            allCities.add(c.toString());
        }

        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(allCities));
        ListView listView = new ListView();
        Text num_of_cuisine = new Text();
        TilePane tile_pane = new TilePane(combo_box,listView,num_of_cuisine);
        Scene scene = new Scene(tile_pane, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        combo_box.setOnAction((event)->{String temp = (String)combo_box.getSelectionModel().getSelectedItem();
            try{
                FoodHunt food = new FoodHunt();
                String city = temp;
                InputStream JSON = food.makeConnection("https://developers.zomato.com/api/v2.1/cities","?q="+city);
                generatePath gp = new generatePath("readable.txt", JSON);
                gp.dataWriter("id","location_suggestions");
                long cityId = gp.getId();
                InputStream JSON2 = food.makeConnection("https://developers.zomato.com/api/v2.1/cuisines","?city_id="+cityId);
                generatePath gp2 = new generatePath("readable2.txt", JSON2);
                gp2.dataWriter("cuisine","cuisines");

                GetData gt = new GetData("readable2.txt");
                ArrayList<String> data = gt.getDataFromFile();
                for(String cuisine: data)
                {
                    listView.getItems().add(cuisine);
                }
                num_of_cuisine.setText("Number of Cuisine "+ String.valueOf(data.size()));

            }catch (URISyntaxException e)
            {
                System.out.println(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            });

    }

    public static void main(String[] args) throws URISyntaxException {
        launch(args);

    }
}

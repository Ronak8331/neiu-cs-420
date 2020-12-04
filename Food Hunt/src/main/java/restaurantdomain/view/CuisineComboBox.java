package restaurantdomain.view;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cuisines;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;
import static restaurantdomain.model.ConvertDataToCollections.getAllRestaurants;
import static restaurantdomain.model.ConvertDataToCollections.getRestaurantByCuisin;

public class CuisineComboBox {

    private ComboBox<Cuisines> cuisinesComboBox;
    private final Map<Cuisines, List<Restaurant>> map;
    private final ObservableList<Cuisines> cuisineType;
    private ListView<Restaurant> restaurantListView;
    private Text textBox;
    private List<Cuisines> cuisinesList;

    public CuisineComboBox() throws ParseException, IOException, URISyntaxException {
        map = getRestaurantByCuisin();
        textBox = new Text();
        addDataToList();
        cuisineType = observableArrayList(cuisinesList);
        setUpCuisines();
    }

    private void addDataToList() {
        cuisinesList = new ArrayList<>();
        for (Map.Entry<Cuisines, List<Restaurant>> h : map.entrySet())
            cuisinesList.add(h.getKey());
    }

    private void setUpCuisines() {
        restaurantListView = new ListView<>();
        cuisinesComboBox = new ComboBox<>();
        cuisinesComboBox.setPromptText("---Select Cuisine---");
        cuisinesComboBox.getItems().clear();
        cuisinesComboBox.getItems().addAll(sortCuisines());
        cuisinesComboBox.getSelectionModel().clearSelection();
        setCuisinesComboBox();
    }

    private ObservableList<Cuisines> sortCuisines() {
        return cuisineType.sorted((o1, o2) -> {
            if (o1.getNumber() > o2.getNumber())
                return 1;
            else if (o1.getNumber() < o2.getNumber())
                return -1;
            else
                return 0;
        });
    }

    private void setCuisinesComboBox() {
        cuisinesComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Cuisines cuisine = cuisinesComboBox.getSelectionModel().getSelectedItem();
            ObservableList<Restaurant> restaurants = observableArrayList(map.get(cuisine));
            restaurantListView.setCellFactory(TextFieldListCell.forListView((new RestaurantString())));
            restaurantListView.setItems(restaurants);
        });
    }

    public ComboBox<Cuisines> getCategories() {
        return cuisinesComboBox;
    }

    public ListView<Restaurant> getRestaurantComboBox() {
        return restaurantListView;
    }

    public Text getTextBox() {
        return textBox;
    }

    public static class RestaurantString extends StringConverter<Restaurant> {

        private final String SEP0 = " has ";
        private final String SEP1 = " restaurant which has ";
        private final String SEP2 = " rating.";
        private final String SEP3 = " ----------- ";

        @Override
        public String toString(Restaurant restaurant) {
            if (restaurant == null)
                return null;
            else
                return restaurant.getCity() + SEP0 + restaurant.getName() + SEP1 + restaurant.getRating() + SEP2 + SEP3 + restaurant.getTiminigs();
        }

        @Override
        public Restaurant fromString(String string) {
            List<Restaurant> restaurants = null;
            try {
                restaurants = getAllRestaurants().stream()
                        .filter(restaurant -> restaurant.getName().equals(string))
                        .limit(1)
                        .collect(Collectors.toList());


            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return restaurants.isEmpty() ? null : restaurants.get(0);
        }
    }
}

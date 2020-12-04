package restaurantdomain.view;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cities;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;
import static restaurantdomain.model.ConvertDataToCollections.getAllRestaurants;
import static restaurantdomain.model.ConvertDataToCollections.getRestaurantByCity;


public class CityComboBox {

    private ComboBox<Cities> citiesComboBox;
    private final Map<Cities, List<Restaurant>> citiesListMap;
    private final ObservableList<Cities> citiesObservableList;
    private ListView<Restaurant> restaurantListView;
    private Text textBox;
    private List<Cities> citiesList;

    public CityComboBox() throws ParseException, IOException, URISyntaxException {
        citiesListMap = getRestaurantByCity();
        textBox = new Text();
        addDataToList();
        citiesObservableList = observableArrayList(citiesList);
        setUpCities();
    }

    private void addDataToList() {
        citiesList = new ArrayList<>();
        for (Map.Entry<Cities, List<Restaurant>> h : citiesListMap.entrySet())
            citiesList.add(h.getKey());
    }

    private void setUpCities() {
        restaurantListView = new ListView<>();
        citiesComboBox = new ComboBox<>();
        citiesComboBox.setPromptText("---Select City---");
        citiesComboBox.getItems().clear();
        citiesComboBox.getItems().addAll(sortCities());
        citiesComboBox.getSelectionModel().clearSelection();
        setCitiesComboBox();
    }

    private ObservableList<Cities> sortCities() {
        return citiesObservableList.sorted((o1, o2) -> {
            if (o1.getNumber() > o2.getNumber())
                return 1;
            else if (o1.getNumber() < o2.getNumber())
                return -1;
            else
                return 0;
        });
    }

    private void setCitiesComboBox() {
        citiesComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Cities cuisine = citiesComboBox.getSelectionModel().getSelectedItem();
            ObservableList<Restaurant> restaurants = observableArrayList(citiesListMap.get(cuisine));
            restaurantListView.setCellFactory(TextFieldListCell.forListView((new RestaurantString())));
            restaurantListView.setItems(restaurants);
        });
    }

    public ComboBox<Cities> getCategories() {
        return citiesComboBox;
    }

    public ListView<Restaurant> getRestaurantComboBox() {
        return restaurantListView;
    }

    public Text getTextBox() {
        return textBox;
    }

    public static class RestaurantString extends StringConverter<Restaurant> {

        private final String SEP0 = " has ";
        private final String SEP2 = " rating and ";
        private final String SEP3 = " cuisine type.";
        private final String SEP4 = " ----------- ";

        @Override
        public String toString(Restaurant restaurant) {
            if (restaurant == null)
                return null;
            else
                return restaurant.getName() + SEP0 + restaurant.getRating() + SEP2 + restaurant.getCuisine() + SEP3 + SEP4 + restaurant.getTiminigs();
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

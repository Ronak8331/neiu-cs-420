package restaurantdomain.view;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Ratings;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;
import static restaurantdomain.model.ConvertDataToCollections.getAllRestaurants;
import static restaurantdomain.model.ConvertDataToCollections.getRestaurantByRating;

public class RatingsComboBox {

    private ComboBox<Ratings> ratingsComboBox;
    private final Map<Ratings, List<Restaurant>> ratingsListMap;
    private final ObservableList<Ratings> ratingsObservableList;
    private ListView<Restaurant> restaurantListView;
    private List<Ratings> ratingsList;

    public RatingsComboBox() throws ParseException, IOException, URISyntaxException {
        ratingsListMap = getRestaurantByRating();
        addDataToList();
        ratingsObservableList = observableArrayList(ratingsList);
        setUpRatings();
    }

    private void addDataToList() {
        ratingsList = new ArrayList<>();
        for (Map.Entry<Ratings, List<Restaurant>> h : ratingsListMap.entrySet())
            ratingsList.add(h.getKey());
    }

    private void setUpRatings() {
        restaurantListView = new ListView<>();
        ratingsComboBox = new ComboBox<>();
        ratingsComboBox.setPromptText("---Select Rating---");
        ratingsComboBox.getItems().clear();
        ratingsComboBox.getItems().addAll(sortRatings());
        ratingsComboBox.getSelectionModel().clearSelection();
        setRatingsComboBox();
    }

    private ObservableList<Ratings> sortRatings() {
        return ratingsObservableList.sorted(Comparator.comparingDouble(Ratings::getMax));
    }

    private void setRatingsComboBox() {
        ratingsComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Ratings ratings = ratingsComboBox.getSelectionModel().getSelectedItem();
            ObservableList<Restaurant> restaurants = observableArrayList(ratingsListMap.get(ratings));
            restaurantListView.setCellFactory(TextFieldListCell.forListView((new RestaurantString())));
            restaurantListView.setItems(restaurants);
        });
    }


    public ComboBox<Ratings> getCategories() {
        return ratingsComboBox;
    }

    public ListView<Restaurant> getRestaurantComboBox() {
        return restaurantListView;
    }


    public static class RestaurantString extends StringConverter<Restaurant> {

        @Override
        public String toString(Restaurant restaurant) {
            String SEP0 = " has ";
            String SEP1 = " restaurant which has ";
            String SEP2 = " rating and ";
            String SEP4 = " ------- ";
            String SEP3 = " cuisine type.";
            if (restaurant == null)
                return null;
            else
                return restaurant.getCity() + SEP0 + restaurant.getName() + SEP1 + restaurant.getRating() + SEP2 + restaurant.getCuisine() + SEP3 + SEP4 + restaurant.getTiminigs();
        }

        @Override
        public Restaurant fromString(String string) {
            List<Restaurant> restaurants = null;
            try {
                restaurants = getAllRestaurants().stream()
                        .filter(restaurant -> restaurant.getName().equals(string))
                        .limit(1)
                        .collect(Collectors.toList());


            } catch (IOException | URISyntaxException | ParseException ioException) {
                ioException.printStackTrace();
            }
            assert restaurants != null;
            return restaurants.isEmpty() ? null : restaurants.get(0);
        }
    }
}



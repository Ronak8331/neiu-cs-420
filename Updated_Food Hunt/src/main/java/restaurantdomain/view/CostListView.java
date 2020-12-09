package restaurantdomain.view;

import javafx.scene.control.ListView;
import javafx.util.StringConverter;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cost;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static restaurantdomain.model.ToMaps.allRestaurants;
import static restaurantdomain.model.ToMaps.restaurantByCost;

public class CostListView extends HelperClassForListView {

    private final Map<Cost, List<Restaurant>> costListMap;
    private ListView<Cost> costListView;

    public CostListView() throws ParseException, IOException, URISyntaxException {
        super();
        costListMap = restaurantByCost();
        setRestaurantListView(new ListView<>());
        getDataList();
    }

    @Override
    public List<Cost> getDataList() {
        addCostDataToList(costListMap);
        costListView = new ListView<>();
        costListView.getItems().addAll(super.getDataList());
        super.clickEvents(costListMap, costListView);
        return super.getDataList();
    }

    public ListView<Cost> getListView() {
        return costListView;
    }

    public static class RestaurantString extends StringConverter<Restaurant> {

        private List<Restaurant> restaurants = null;

        @Override
        public String toString(Restaurant restaurant) {
            String SEP0 = " has ";
            String SEP1 = " restaurant which has ";
            String SEP2 = " rating and ";
            String SEP4 = " ---- ";
            String SEP3 = " cuisine type.";
            String SEP5 = " --- costs $";
            String SEP6 = " for two persons.";
            return restaurant == null ? null : restaurant.getCity() + SEP0 + restaurant.getName() + SEP1 + restaurant.getRating() + SEP2 + restaurant.getCuisine() + SEP3 + SEP4 + restaurant.getTiminigs() + SEP5 + restaurant.getCost() + SEP6;
        }

        @Override
        public Restaurant fromString(String string) {
            try {
                restaurants = allRestaurants().stream()
                        .filter(restaurant -> restaurant.getName().equals(string))
                        .limit(1)
                        .collect(Collectors.toList());
            } catch (IOException | ParseException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
            assert restaurants != null;
            return restaurants.isEmpty() ? null : restaurants.get(0);
        }
    }
}

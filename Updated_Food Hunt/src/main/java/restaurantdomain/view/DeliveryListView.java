package restaurantdomain.view;

import javafx.scene.control.ListView;
import javafx.util.StringConverter;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Delivery;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static restaurantdomain.model.ToMaps.allRestaurants;
import static restaurantdomain.model.ToMaps.restaurantByDelivery;

public class DeliveryListView extends HelperClassForListView {

    private final Map<Delivery, List<Restaurant>> map;
    private ListView<Delivery> deliveryListView;

    public DeliveryListView() throws ParseException, IOException, URISyntaxException {
        super();
        map = restaurantByDelivery();
        setRestaurantListView(new ListView<>());
        getDeliveryList();
    }

    @Override
    public List<Delivery> getDeliveryList() {
        addDeliveryDataToList(map);
        deliveryListView = new ListView<>();
        deliveryListView.getItems().addAll(super.getDeliveryList());
        super.clickEvents(map, deliveryListView);
        return super.getDeliveryList();
    }

    public ListView<Delivery> getListView() {
        return deliveryListView;
    }

    public static class RestaurantString extends StringConverter<Restaurant> {

        private List<Restaurant> restaurants = null;

        @Override
        public String toString(Restaurant restaurant) {
            String SEP1 = " located at ";
            String SEP3 = " online delivery ";
            if (restaurant == null)
                return null;
            else if (restaurant.getDelivery().equals("1")) {
                String SEP0 = " provides ";
                return restaurant.getName() + SEP0 + SEP3 + SEP1 + restaurant.getCity();
            } else if (restaurant.getDelivery().equals("0")) {
                String SEP2 = " does not provide ";
                return restaurant.getName() + SEP2 + SEP3 + SEP1 + restaurant.getCity();
            } else
                return null;
        }

        @Override
        public Restaurant fromString(String string) {
            try {
                restaurants = allRestaurants().stream()
                        .filter(restaurant -> restaurant.getName().equals(string))
                        .limit(1)
                        .collect(Collectors.toList());
            } catch (IOException | URISyntaxException | ParseException ioException) {
                ioException.printStackTrace();
            }
            return restaurants.isEmpty() ? null : restaurants.get(0);
        }
    }
}

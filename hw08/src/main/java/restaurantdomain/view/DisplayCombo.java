package restaurantdomain.view;

import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import static restaurantdomain.model.ConvertDataToCollections.getAllRestaurants;
import static restaurantdomain.model.ConvertDataToCollections.getRestaurantByCuisin;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cuisines;
import restaurantdomain.model.Restaurant;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class DisplayCombo {

    private ComboBox<Cuisines> categories;
    private ComboBox<Restaurant> restaurantComboBox;
    private final Text textBox;
    private final Map<Cuisines, List<Restaurant>> map;
    private final ObservableList<Cuisines> cuisineType;

    public DisplayCombo() throws ParseException, IOException, URISyntaxException {
        map = getRestaurantByCuisin();
        cuisineType = observableArrayList((map.keySet()));
        textBox = new Text();
        setUpCuisines();
        setUpRestaurants();
    }

    private static class RestaurantString extends StringConverter<Restaurant> {

        private final String SEP0 = "City= ";
        private final String SEP1 = ", Rating= ";
        private final String SEP2 = ", Timings= ";
        private final String SEP3 = ", Cuisine= ";
        @Override
        public String toString(Restaurant restaurant) {
            if(restaurant == null)
                return null;
            else
                return SEP0 + restaurant.getCity() + SEP1 + restaurant.getRating() + SEP2 + restaurant.getTiminigs() + SEP3 + restaurant.getCuisine();
        }

        @Override
        public Restaurant fromString(String string) {
            String city = string.split(SEP1)[0];
            try {

                for(Restaurant restaurant : getAllRestaurants()) {
                    if(restaurant.getCity().equals(city))
                        return restaurant;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private void setUpCuisines() {
        categories = new ComboBox<>();
        categories.setPromptText("----select cuisine----");
        categories.getItems().addAll(sortCuisines());
        categories.valueProperty().addListener((observable, oldValue, newValue) -> {
                    textBox.setVisible(false);
                    restaurantComboBox.getItems().clear();
                    restaurantComboBox.getItems().addAll(map.get(newValue));
                    restaurantComboBox.setVisible(true);
                });
    }

    private ObservableList<Cuisines> sortCuisines() {
        return cuisineType.sorted((o1, o2) -> {
            if(o1.getNumber() > o2.getNumber())
                return 1;
            else if(o1.getNumber() < o2.getNumber())
                return -1;
            else
                return 0;
        });
    }
    private void setUpRestaurants() {
        restaurantComboBox = new ComboBox<>();
        restaurantComboBox.setPromptText("--Select Restaurant--");
        restaurantComboBox.setConverter(new RestaurantString());
        restaurantComboBox.setVisible(false);
        restaurantListener();
        comboBoxUpdate();
    }

    private void comboBoxUpdate() {
        restaurantComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Restaurant restaurant, boolean empty){
                super.updateItem(restaurant, empty);
                if(empty || restaurant == null)
                    setText("--Select Restaurant--");
                else{
                    RestaurantString converter = new RestaurantString();
                    setText(converter.toString(restaurant));
                }
            }
        });
    }
    private void restaurantListener() {
        restaurantComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                String display = "City= " + newValue.getCity() + "\n"
                        + "Rating= "+newValue.getRating() + "\n"
                        + "Timings= "+ newValue.getTiminigs() + "\n"
                        + "Cuisine= " + newValue.getCuisine();
                textBox.setText(display);
                textBox.setVisible(true);
            }
        });
    }
    public ComboBox<Cuisines> getCategories() {
        return categories;
    }

    public ComboBox<Restaurant> getRestaurantComboBox() {
        return restaurantComboBox;
    }

    public Text getTextBox() {
        return textBox;
    }
}

package restaurantdomain.view;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import restaurantdomain.model.Cost;
import restaurantdomain.model.Delivery;
import restaurantdomain.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javafx.collections.FXCollections.observableArrayList;


public abstract class HelperClassForListView {

    private List<Delivery> deliveryList;
    private List<Cost> dataList;
    private ListView<Restaurant> restaurantListView;
    private ObservableList<Restaurant> restaurants;

    public HelperClassForListView() {
        deliveryList = new ArrayList<>();
        dataList = new ArrayList<>();
    }

    public ListView<Restaurant> getRestaurantListView() {
        return restaurantListView;
    }

    public void setRestaurantListView(ListView<Restaurant> restaurantListView) {
        this.restaurantListView = restaurantListView;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<Cost> getDataList() {
        return dataList;
    }

    public void setDataList(List<Cost> dataList) {
        this.dataList = dataList;
    }

    public void addDeliveryDataToList(Map<Delivery, List<Restaurant>> map) {
        deliveryList = new ArrayList<>(map.keySet());
        setDeliveryList(deliveryList);
    }

    public void addCostDataToList(Map<Cost, List<Restaurant>> costListMap) {
        dataList = new ArrayList<>(costListMap.keySet());
        setDataList(dataList);
    }

    public abstract ListView<? extends Object> getListView();

    public void clickEvents(Map<? extends Object, List<Restaurant>> rest, ListView<? extends Object> list) {
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Object delivery = list.getSelectionModel().getSelectedItem();
            restaurants = observableArrayList(rest.get(delivery));
            getRestaurantListView().setCellFactory((delivery.getClass().getName()).equals("restaurantdomain.model.Cost") ? TextFieldListCell.forListView((new CostListView.RestaurantString())) : TextFieldListCell.forListView((new DeliveryListView.RestaurantString())));
            getRestaurantListView().setItems(restaurants);
        });
    }
}

package restaurantdomain.view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Delivery;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static restaurantdomain.model.ToMaps.restaurantByDelivery;

public class GraphForDelivery {

    private final CategoryAxis x;
    private final NumberAxis y;
    private final BarChart<String, Number> bc;
    private final List<Delivery> deliveryList;
    private final Map<Delivery, List<Restaurant>> deliveryListMap;
    private final List<Integer> count;
    private final XYChart.Series<String, Number> series;
    private int bound;

    public GraphForDelivery() throws ParseException, IOException, URISyntaxException {
        x = new CategoryAxis();
        y = new NumberAxis();
        bc = new BarChart<>(x, y);
        count = new ArrayList<>();
        series = new XYChart.Series<>();
        deliveryListMap = restaurantByDelivery();
        deliveryList = new ArrayList<>(deliveryListMap.keySet());
    }

    public BarChart<String, Number> graphsForDelivery() {
        bc.setTitle("Restaurants provide online delivery");
        x.setLabel("Delivery");
        y.setLabel("Total number of restaurants");
        seriesDataSetting();
        if (bc.getData().size() == 0) {
            bc.getData().add(series);
        }
        return bc;
    }

    private void seriesDataSetting() {
        int bound1 = deliveryList.size();
        IntStream.range(0, bound1).forEach(j -> count.add(j, deliveryListMap.get(deliveryList.get(j)).size()));
        int bound3 = deliveryList.size();
        IntStream.range(0, bound3).forEach(i -> series.getData().add(new XYChart.Data<>((deliveryList.get(i).toString()) + "\n" + "Number of Restaurants  " + deliveryListMap.get(deliveryList.get(i)).size(), count.get(i))));
        series.setName("Total Number of Restaurant ");
    }
}

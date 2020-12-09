package restaurantdomain.view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Cost;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static restaurantdomain.model.ToMaps.restaurantByCost;

public class Graphs {

    private final CategoryAxis x;
    private final NumberAxis y;
    private final BarChart<String, Number> bc;
    private final List<Cost> costList;
    private final Map<Cost, List<Restaurant>> costListMap;
    private final List<Integer> count;
    private final XYChart.Series<String, Number> series;


    public Graphs() throws ParseException, IOException, URISyntaxException {
        series = new XYChart.Series<>();
        costListMap = restaurantByCost();
        costList = new ArrayList<>(costListMap.keySet());
        x = new CategoryAxis();
        y = new NumberAxis();
        bc = new BarChart<>(x, y);
        count = new ArrayList<>();
    }

    public BarChart<String, Number> graphForCost() {
        bc.setTitle("Restaurants provide online delivery by cost");
        x.setLabel("Cost Range");
        y.setLabel("Number of Restaurants");
        seriesDataSetting();
        bc.setAnimated(false);
        if (bc.getData().size() == 0) {
            bc.getData().add(series);
        }
        return bc;
    }

    private void seriesDataSetting() {
        series.setName("Number Of Restaurants");
        IntStream.range(0, costList.size()).forEach(j -> count.add(j, costListMap.get(costList.get(j)).size()));
        IntStream.range(0, costList.size()).forEach(i -> series.getData().add(new XYChart.Data<>((costList.get(i).toString()) + "\n" + "Number of Restaurants  " + costListMap.get(costList.get(i)).size(), count.get(i))));
    }

}

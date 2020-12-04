package restaurantdomain.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.parser.ParseException;
import restaurantdomain.model.Restaurant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static restaurantdomain.model.ConvertDataToCollections.getAllRestaurants;

public class PieChartForData {

    private javafx.scene.chart.PieChart ratingPieChart, cityPieChart, cuisinePieChart;
    private final List<Restaurant> restaurantList;
    private List<String> distinctRating, distinctCity, distinctCuisine;


    public PieChartForData() throws IOException, URISyntaxException, ParseException {
        restaurantList = getAllRestaurants();
        getList();
        initializeRatingPieChart();
        initializeCityPieChart();
        initializeCuisinePieChart();
    }

    private void getList() {
        distinctRating = restaurantList.stream().map(Restaurant::getRating).distinct().collect(Collectors.toList());
        distinctCity = restaurantList.stream().map(Restaurant::getCity).distinct().collect(Collectors.toList());
        distinctCuisine = restaurantList.stream().map(Restaurant::getCuisine).distinct().collect(Collectors.toList());
    }

    public void initializeRatingPieChart() {
        javafx.scene.chart.PieChart.Data[] data = new javafx.scene.chart.PieChart.Data[distinctRating.size()];
        IntStream.range(0, distinctRating.size()).forEach(i -> {
            String rating = distinctRating.get(i);
            data[i] = new javafx.scene.chart.PieChart.Data(rating, restaurantList.stream().filter(days -> days.getRating().equals(rating)).count());
        });
        ObservableList<javafx.scene.chart.PieChart.Data> ratingData = FXCollections.observableArrayList(data);
        ratingPieChart = new javafx.scene.chart.PieChart(ratingData);
        ratingPieChart.setTitle("Number of restaurants by rating");
    }

    private void initializeCityPieChart() {
        javafx.scene.chart.PieChart.Data[] cityData = new javafx.scene.chart.PieChart.Data[distinctCity.size()];
        IntStream.range(0, distinctCity.size()).forEach(i -> {
            String city = distinctCity.get(i);
            cityData[i] = new javafx.scene.chart.PieChart.Data(city, restaurantList.stream().filter(days -> days.getCity().equals(city)).count());
        });
        cityPieChart = new javafx.scene.chart.PieChart(FXCollections.observableArrayList(cityData));
        cityPieChart.setTitle("Number of restaurants by city");
    }

    private void initializeCuisinePieChart() {
        javafx.scene.chart.PieChart.Data[] cuisineData = new javafx.scene.chart.PieChart.Data[distinctCuisine.size()];
        IntStream.range(0, distinctCuisine.size()).forEach(i -> {
            String cuisine = distinctCuisine.get(i);
            cuisineData[i] = new javafx.scene.chart.PieChart.Data(cuisine, restaurantList.stream().filter(days -> days.getCuisine().equals(cuisine)).count());
        });
        cuisinePieChart = new javafx.scene.chart.PieChart(FXCollections.observableArrayList(cuisineData));
        cuisinePieChart.setTitle("Number of restaurants by cuisine type");
    }

    public javafx.scene.chart.PieChart getRatingPieChart() {
        return ratingPieChart;
    }

    public javafx.scene.chart.PieChart getCityPieChart() {
        return cityPieChart;
    }

    public javafx.scene.chart.PieChart getCuisinePieChart() {
        return cuisinePieChart;
    }
}

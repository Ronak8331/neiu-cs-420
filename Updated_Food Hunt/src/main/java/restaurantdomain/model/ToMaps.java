package restaurantdomain.model;

import api.ReadFromFile;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.IntStream;

public class ToMaps {

    private static List<Restaurant> restaurants;

    private ToMaps() {
    }

    public static List<Restaurant> allRestaurants() throws IOException, ParseException, URISyntaxException {
        String readData = ReadFromFile.readData();
        restaurants = new ArrayList<>();
        int length = readData.split("\n").length;
        IntStream.range(0, length).forEach(i -> {
            String city = (readData.split("\n")[i].split("---", 10)[0]);
            String name = (readData.split("\n")[i].split("---")[1]);
            String rating = readData.split("\n")[i].split("---")[2];
            String timing = readData.split("\n")[i].split("---")[3];
            String cuisine = readData.split("\n")[i].split("---")[4].split(",")[0];
            String cost = readData.split("\n")[i].split("---")[5];
            String delivery = readData.split("\n")[i].split("---")[6];
            restaurants.add(new Restaurant(city, name, rating, timing, cuisine, cost, delivery));
        });
        return restaurants;
    }

    public static Map<Cost, List<Restaurant>> restaurantByCost() throws IOException, ParseException, URISyntaxException {
        Map<Cost, List<Restaurant>> rest_cost = new HashMap<>();
        allRestaurants().forEach(r -> {
            Arrays.stream(Cost.values()).filter(name -> (Double.parseDouble(r.getCost()) >= name.getMin() && (Double.parseDouble(r.getCost()) <= name.getMax()))).forEach(name -> {
                if (!rest_cost.containsKey(name)) {
                    rest_cost.put(name, new ArrayList<>((Collections.singletonList(r))));
                } else {
                    rest_cost.get(name).add(r);
                }
            });
        });
        return rest_cost;
    }

    public static Map<Delivery, List<Restaurant>> restaurantByDelivery() throws IOException, ParseException, URISyntaxException {
        Map<Delivery, List<Restaurant>> rest_delivery = new HashMap<>();
        allRestaurants().forEach(rt -> {
            Arrays.stream(Delivery.values()).filter(name -> rt.getDelivery().equals(name.getName())).forEach(name -> {
                if (!rest_delivery.containsKey(name)) {
                    rest_delivery.put(name, new ArrayList<>((Collections.singletonList(rt))));
                } else {
                    rest_delivery.get(name).add(rt);
                }
            });
        });
        return rest_delivery;
    }
}

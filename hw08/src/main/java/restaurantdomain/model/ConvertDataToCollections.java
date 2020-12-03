package restaurantdomain.model;

import org.json.simple.parser.ParseException;
import api.ReadFromFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.IntStream;

public class ConvertDataToCollections {

    private static List<Restaurant> restaurants;

    private ConvertDataToCollections() {}

    public static List<Restaurant> getAllRestaurants() throws ParseException, IOException, URISyntaxException {
        return getRestaurants();
    }

    private static List<Restaurant> getRestaurants() throws IOException, ParseException, URISyntaxException {
        String readData = ReadFromFile.readData();
        restaurants = new ArrayList<>();
        int length = readData.split("\n").length;
        IntStream.range(0, length).forEach(i -> {
            String city = (readData.split("\n")[i].split("---", 10)[0]);
            String name = (readData.split("\n")[i].split("---")[1]);
            String rating = readData.split("\n")[i].split("---")[2];
            String timing = readData.split("\n")[i].split("---")[3];
            String cuisine = readData.split("\n")[i].split("---")[4].split(",")[0];
            restaurants.add(new Restaurant(city, name, rating, timing, cuisine));
        });
        return restaurants;
    }

    public static Map<Cuisines, List<Restaurant>> getRestaurantByCuisin() throws IOException, ParseException, URISyntaxException {
        Map<Cuisines, List<Restaurant>> rest = new HashMap<>();

        getRestaurants().forEach(rt -> valueForGettingName(rest, rt));
        return rest;
    }

    private static void valueForGettingName(Map<Cuisines, List<Restaurant>> rest, Restaurant rt) {
        Arrays.stream(Cuisines.values()).filter(name -> rt.getCuisine().equals(name.getName())).forEach(name -> addToTheList(name, rt, rest));
    }

    private static void addToTheList(Cuisines cuisin, Restaurant restaurant, Map<Cuisines, List<Restaurant>> rest){

        if(!rest.containsKey(cuisin)){
            rest.put(cuisin,new ArrayList<>((Arrays.asList(restaurant))));
        }
        else{
            rest.get(cuisin).add(restaurant);
        }
    }
}

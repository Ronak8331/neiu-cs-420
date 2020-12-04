package restaurantdomain.model;

import api.ReadFromFile;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.IntStream;

public class ConvertDataToCollections {

    private static List<Restaurant> restaurants;

    private ConvertDataToCollections() {
    }

    public static List<Restaurant> getAllRestaurants() throws IOException, URISyntaxException, ParseException {
        return getRestaurants();
    }

    private static List<Restaurant> getRestaurants() throws IOException, URISyntaxException, ParseException {
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

    public static Map<Cuisines, List<Restaurant>> getRestaurantByCuisin() throws IOException, URISyntaxException, ParseException {
        Map<Cuisines, List<Restaurant>> rest = new HashMap<>();
        getRestaurants().forEach(rt -> valueForGettingName(rest, rt));
        return rest;
    }

    private static void valueForGettingName(Map<Cuisines, List<Restaurant>> rest, Restaurant rt) {
        Arrays.stream(Cuisines.values()).filter(name -> rt.getCuisine().equals(name.getName())).forEach(name -> addToTheList(name, rt, rest));
    }

    private static void addToTheList(Cuisines cuisin, Restaurant restaurant, Map<Cuisines, List<Restaurant>> rest) {

        if (!rest.containsKey(cuisin)) {
            rest.put(cuisin, new ArrayList<>((Arrays.asList(restaurant))));
        } else {
            rest.get(cuisin).add(restaurant);
        }
    }

    public static Map<Ratings, List<Restaurant>> getRestaurantByRating() throws IOException, URISyntaxException, ParseException {
        Map<Ratings, List<Restaurant>> rest_rating = new HashMap<>();
        getAllRestaurants().forEach(r -> valueForGettingNameByRating(rest_rating, r));
        return rest_rating;
    }

    private static void valueForGettingNameByRating(Map<Ratings, List<Restaurant>> rest, Restaurant rt) {
        Arrays.stream(Ratings.values()).filter(name -> (Double.parseDouble(rt.getRating()) >= name.getMin() && (Double.parseDouble(rt.getRating()) <= name.getMax()))).forEach(name -> addToTheListOfRating(name, rt, rest));
    }


    private static void addToTheListOfRating(Ratings ratings, Restaurant restaurant, Map<Ratings, List<Restaurant>> rest) {

        if (!rest.containsKey(ratings)) {
            rest.put(ratings, new ArrayList<>((Arrays.asList(restaurant))));
        } else {
            rest.get(ratings).add(restaurant);
        }
    }

    public static Map<Cities, List<Restaurant>> getRestaurantByCity() throws IOException, URISyntaxException, ParseException {
        Map<Cities, List<Restaurant>> rest = new HashMap<>();
        getRestaurants().forEach(rt -> valueForGettingCity(rest, rt));
        return rest;
    }

    private static void valueForGettingCity(Map<Cities, List<Restaurant>> rest, Restaurant rt) {
        Arrays.stream(Cities.values()).filter(name -> rt.getCity().equals(name.getName())).forEach(name -> addToTheListOfCity(name, rt, rest));
    }

    private static void addToTheListOfCity(Cities city, Restaurant restaurant, Map<Cities, List<Restaurant>> rest) {

        if (!rest.containsKey(city)) {
            rest.put(city, new ArrayList<>((Arrays.asList(restaurant))));
        } else {
            rest.get(city).add(restaurant);
        }
    }
}

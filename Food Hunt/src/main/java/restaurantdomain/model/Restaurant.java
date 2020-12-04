package restaurantdomain.model;

import java.util.Objects;

public class Restaurant {

    private String city;
    private String name = "";
    private String rating = "";
    private String timings = "";
    private String cuisine = "";

    public Restaurant(String city, String name, String rating, String timings, String cuisine) {
        this.city = city;
        this.name = name;
        this.rating = rating;
        this.timings = timings;
        this.cuisine = cuisine;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getTiminigs() {
        return timings;
    }

    public String getCuisine() {
        return cuisine;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", timings='" + timings + '\'' +
                ", cuisine='" + cuisine + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(name, that.name) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(timings, that.timings) &&
                Objects.equals(cuisine, that.cuisine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, name, rating, timings, cuisine);
    }

}

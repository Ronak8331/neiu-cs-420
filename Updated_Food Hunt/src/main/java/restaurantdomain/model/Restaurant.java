package restaurantdomain.model;

import java.util.Objects;

public class Restaurant {

    private final String city, name, rating, timings, cuisine, cost, delivery;

    public Restaurant(String city, String name, String rating, String timings, String cuisine, String cost, String delivery) {
        this.city = city;
        this.name = name;
        this.rating = rating;
        this.timings = timings;
        this.cuisine = cuisine;
        this.cost = cost;
        this.delivery = delivery;
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

    public String getCost() {
        return cost;
    }

    public String getDelivery() {
        return delivery;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", timings='" + timings + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", cost='" + cost + '\'' +
                ", delivery='" + delivery + '\'' +
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
                Objects.equals(cuisine, that.cuisine) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(delivery, that.delivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, name, rating, timings, cuisine, cost, delivery);
    }

}

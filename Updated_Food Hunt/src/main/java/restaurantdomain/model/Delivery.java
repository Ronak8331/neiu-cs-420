package restaurantdomain.model;

public enum Delivery {

    ONLINE_DELIVER("1"),
    NO_DELIVERY("0"),
    ;

    private String name;

    Delivery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

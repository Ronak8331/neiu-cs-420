package restaurantdomain.model;

public enum FirstComboBox {

    RESTAURANTS_BY_CUISINE(1, "Restaurant By Cuisines"),
    RESTAURANTS_BY_RATING(2, "Restaurant By Rating"),
    RESTAURANTS_BY_CITY(3, "Restaurant By City"),
    VISUALS(4, "Visuals"),
    BACK_TO_HOME(5,"Back to Home")
    ;

    private String name;
    private int number;

    FirstComboBox(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}

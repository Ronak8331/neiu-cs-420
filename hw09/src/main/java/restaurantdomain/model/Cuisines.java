package restaurantdomain.model;

public enum Cuisines {
    ITALIAN(1,"Italian"),
    CAFE(2,"Cafe"),
    NORTH_INDIAN(3,"North Indian"),
    AMERICAN(4,"American"),
    MODERN_AUSTRALIAN(5,"Modern Australian"),
    SEAFOOD(6,"Seafood"),
    BAKERY(7,"Bakery"),
    CONTEMPORARY(8,"Contemporary"),
    ASIAN(9,"Asian"),
    SOUTH_INDIAN(10,"South Indian"),
    BURGER(11,"Burger"),
    FRENCH(12,"French"),
    MIDDLE_EASTERN(13,"Middle Eastern"),
    COFFEE_TEA(14,"Coffee and Tea"),
    THAI(15,"Thai"),
    BAR_FOOD(16,"Bar Food");

    private String name;
    private int number;
    Cuisines(int number ,String name) {
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

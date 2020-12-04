package restaurantdomain.model;

public enum Cuisines {
    ITALIAN(1, "Italian"),
    CAFE_FOOD(2, "Cafe Food"),
    NORTH_INDIAN(3, "North Indian"),
    AMERICAN(4, "American"),
    FAST_FOOD(5, "Fast Food"),
    CHINESE(6, "Chinese"),
    BAKERY(7, "Bakery"),
    ASIAN(8, "Asian"),
    SOUTH_INDIAN(9, "South Indian"),
    FRENCH(10, "French"),
    MEXICAN(11, "Mexican"),
    CAFE(12, "Cafe"),
    CONTINENTAL(13, "Continental"),
    BIRYANI(14, "Biryani"),
    EUROPEAN(15, "European"),
    PETISCOS(16, "Petiscos"),
    MEDITERRANEAN(17, "Mediterranean"),
    SINGAPOREAN(18, "Singaporean"),
    DESSERTS(19, "Desserts"),
    BEVERAGES(20, "Beverages"),
    SPANISH(21, "Spanish"),
    PIZZA(22, "Pizza"),
    ;


    private String name;
    private int number;

    Cuisines(int number, String name) {
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

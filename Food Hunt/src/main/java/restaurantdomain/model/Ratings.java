package restaurantdomain.model;

public enum Ratings {

    EXCELLENT(4.5, 5.0),
    GREAT(4.0, 4.4),
    GOOD(3.5, 3.9),
    MODERATE(1.0, 3.4),;

    private final double min;
    private final double max;

    Ratings(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

}

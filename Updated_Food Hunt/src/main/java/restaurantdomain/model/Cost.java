package restaurantdomain.model;

public enum Cost {

    Between_0To100(0, 100),
    Between_101To500(101, 500),
    Between_501To1000(501, 1000),
    Between_1001To2000(1001, 2000),
    ;

    private final double min;
    private final double max;

    Cost(double min, double max) {
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

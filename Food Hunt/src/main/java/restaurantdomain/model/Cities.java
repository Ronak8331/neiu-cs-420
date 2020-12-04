package restaurantdomain.model;

public enum Cities {

    MATHURA(1, "Mathura"),
    KARAIKUDI(2, "Karaikudi"),
    SINGAPORE(3, "Singapore"),
    THIRUVALLA(4, "Thiruvalla"),
    KOLKATA(5, "Kolkata"),
    PURI(6, "Puri"),
    MANDI(7, "Mandi"),
    BRAGA(8, "Braga"),
    HOSUR(9, "Hosur"),
    NAGAON(10, "Nagaon"),
    SANTAREM(11, "Santarém"),
    ACKERMAN(12, "Ackerman"),
    LANCASTER(13, "Lancaster"),
    BARWON_HEADS(14, "Barwon Heads"),
    KURNOOL(15, "Kurnool"),
    AIZAWL(16, "Aizawl"),
    WEST_GOSFORD(17, "West Gosford"),
    AGRA(18, "Agra"),
    DERBY(19, "Derby"),
    DAVANAGERE(20, "Davanagere"),
    BATALA(21, "Batala"),
    CENTRALIA(22, "Centralia"),
    BRYCE(22, "Bryce"),
    BARDHAMAN(22, "Bardhaman"),
    KHARAGPUR(23, "Kharagpur"),
    JAMESTOWN(24, "Jamestown"),
    MUSSOORIE(25, "Mussoorie"),
    MORADABAD(26, "Moradabad"),
    SEAFORD(27, "Seaford"),
    ROORKEE(28, "Roorkee"),
    AGARTALA(29, "Agartala"),
    KOLLAM(30, "Kollam"),
    ;

    private String name;
    private int number;

    Cities(int number, String name) {
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

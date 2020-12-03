package candyselection.model;

import java.util.Objects;

public class CandySelection {

    private String string;
    private boolean chocolate, fruity, caramel, peanutyAlmondy, nougat, crispedricewafer, hard, bar;
    private float sugarpercent, pricepercent, winpercent;

    public CandySelection(String string, boolean chocolate, boolean fruity, boolean caramel, boolean peanutyAlmondy, boolean nougat, boolean crispedricewafer, boolean hard, boolean bar, float sugarpercent, float pricepercent, float winpercent) {
        this.string = string;
        this.chocolate = chocolate;
        this.fruity = fruity;
        this.caramel = caramel;
        this.peanutyAlmondy = peanutyAlmondy;
        this.nougat = nougat;
        this.crispedricewafer = crispedricewafer;
        this.hard = hard;
        this.bar = bar;
        this.sugarpercent = sugarpercent;
        this.pricepercent = pricepercent;
        this.winpercent = winpercent;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "name='" + string + '\'' +
                ", chocolate=" + chocolate +
                ", fruity=" + fruity +
                ", caramel=" + caramel +
                ", peanutyalmondy=" + peanutyAlmondy +
                ", nougat=" + nougat +
                ", crispedricewafer=" + crispedricewafer +
                ", hard=" + hard +
                ", bar=" + bar +
                ", sugarpercent=" + sugarpercent +
                ", pricepercent=" + pricepercent +
                ", winpercent=" + winpercent +
                '}';
    }

    public String getString() {
        return string;
    }

    public boolean isChocolate() {
        return chocolate;
    }

    public boolean isFruity() {
        return fruity;
    }

    public boolean isCaramel() {
        return caramel;
    }

    public boolean isPeanutAlmond() {
        return peanutyAlmondy;
    }

    public boolean isNougat() {
        return nougat;
    }

    public boolean isCrippleware() {
        return crispedricewafer;
    }

    public boolean isHard() {
        return hard;
    }

    public boolean isBar() {
        return bar;
    }

    public float getSugarcane() {
        return sugarpercent;
    }

    public float getPrecedence() {
        return pricepercent;
    }

    public float getPercent() {
        return winpercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandySelection)) return false;
        CandySelection that = (CandySelection) o;
        return chocolate == that.chocolate &&
                fruity == that.fruity &&
                caramel == that.caramel &&
                peanutyAlmondy == that.peanutyAlmondy &&
                nougat == that.nougat &&
                crispedricewafer == that.crispedricewafer &&
                hard == that.hard &&
                bar == that.bar &&
                Float.compare(that.sugarpercent, sugarpercent) == 0 &&
                Float.compare(that.pricepercent, pricepercent) == 0 &&
                Float.compare(that.winpercent, winpercent) == 0 &&
                Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, chocolate, fruity, caramel, peanutyAlmondy, nougat, crispedricewafer, hard, bar, sugarpercent, pricepercent, winpercent);
    }

}

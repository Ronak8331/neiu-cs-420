package restaurantdomain.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class FirstPageRadioButtons {

    private RadioButton rb1,rb2;
    private ToggleGroup tg;
    private HBox firstHBox;

    public FirstPageRadioButtons()
    {
        tg = new ToggleGroup();
        initializeRadioButton();
        setRadioButtonHBox();

    }

    private void initializeRadioButton()
    {
        rb1 = new RadioButton("Home");
        rb2 = new RadioButton("For Graphics");
        tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
    }

    private void setRadioButtonHBox()
    {
        firstHBox = new HBox();
        firstHBox.getChildren().addAll(rb1,rb2);
        firstHBox.setPadding(new Insets(10,0,0,0));
        firstHBox.setAlignment(Pos.TOP_CENTER);
        firstHBox.setSpacing(25);
    }

    public ToggleGroup getTg() {
        return tg;
    }

    public RadioButton getRb1() {
        return rb1;
    }

    public RadioButton getRb2() {
        return rb2;
    }

}

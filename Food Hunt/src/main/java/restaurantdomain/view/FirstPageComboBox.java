package restaurantdomain.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import restaurantdomain.model.FirstComboBox;

public class FirstPageComboBox {

    private ComboBox<FirstComboBox> combo1;
    private HBox firstHBox;

    public FirstPageComboBox() {
        comboBoxInitialization();
        setHBox();
    }

    private void comboBoxInitialization() {
        combo1 = new ComboBox<>();
        combo1.getItems().addAll(FirstComboBox.values());
    }

    private void setHBox() {
        firstHBox = new HBox();
        firstHBox.getChildren().addAll(combo1);
        firstHBox.setPadding(new Insets(10, 0, 0, 0));
        firstHBox.setAlignment(Pos.TOP_CENTER);
        firstHBox.setSpacing(25);
    }

    public ComboBox<FirstComboBox> getCombo1() {return combo1;}

    public HBox getFirstHBox() {
        return firstHBox;
    }
}

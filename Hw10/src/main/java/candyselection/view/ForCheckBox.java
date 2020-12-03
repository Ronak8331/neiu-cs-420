package candyselection.view;

import candyselection.model.CandySelection;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static candyselection.model.DataSelection.candy;
import static javafx.collections.FXCollections.observableArrayList;

public class ForCheckBox {

    private CheckBox checkBox, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7;
    private HBox hBox1, hBox3, hBox4, hbox5;
    private VBox vBox, vBox1, vBox2;
    private final List<CandySelection> candySelections;
    private List<CandySelection> selections;
    private Button button;
    private final ForRadioButtons forRadioButtons;
    private final ListView<String> listView;
    private final List<String> strings;
    private final Text errorText;

    public ForCheckBox() throws IOException {
        candySelections = candy();
        forRadioButtons = new ForRadioButtons();
        listView = new ListView<>();
        errorText = new Text();
        strings = new ArrayList<>();
        checkBoxs();
        forHBox();
        onAction();
    }

    private void checkBoxs() {
        checkBox = new CheckBox("Chocolate");
        checkBox1 = new CheckBox("Fruity");
        checkBox2 = new CheckBox("Caramel");
        checkBox3 = new CheckBox("Peanuty-Almondy");
        checkBox4 = new CheckBox("Nougat");
        checkBox5 = new CheckBox("Crisped Rice Wafer");
        checkBox6 = new CheckBox("Hard");
        checkBox7 = new CheckBox("Bar");
    }

    private void forHBox()
    {
        radioButtons();
        vBox = new VBox();
        vBox.getChildren().addAll(hBox1, hBox4, hbox5, hBox3, vBox1, vBox2);
    }

    private void radioButtons() {
        hBox1();
        submit();
        radioHBox1();
        radioHBox2();
        display();
        error();
    }

    private void hBox1() {
        hBox1 = new HBox();
        hBox1.getChildren().addAll(checkBox, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7);
    }

    private void submit() {
        button = new Button("Submit");
        hBox3 = new HBox(button);
    }

    private void radioHBox1()
    {
        hBox4 = forRadioButtons.gethBox();
    }

    private void radioHBox2()
    {
        hbox5 = forRadioButtons.gethBox2();
    }

    private void display() {
        vBox1 = new VBox();
        vBox1.getChildren().add(listView);
    }

    private void error() {
        vBox2 = new VBox();
        vBox2.getChildren().add(errorText);
    }

    private void onAction() {
        button.setOnAction(event -> selectOptions());
    }

    public VBox getenvBox() {
        return vBox;
    }

    private void selectOptions() {
        selections = candySelections;
        listView.getItems().clear();
        strings.clear();
        if(checkboxSelection() != 0) {
            if(selections.isEmpty() == true) {
                errorText.setText("No any candy exists");
            }
            else{
                errorText.setText("");
                if (forRadioButtons.getSelectedValue2().equals("Show Any")) {
                    any();
                }
                if (forRadioButtons.getSelectedValue2().equals("Show All")) {
                    all();
                }
                if (forRadioButtons.getSelectedValue2().equals("Show Highest")) {
                    highest();
                }
            }

        }
        else
            errorText.setText("Please select at least one checkbox");
    }

    private void all() {
        if (forRadioButtons.getSelectedValue1().equals("Sugar Percent"))
            selections.stream().sorted(Comparator.comparingDouble(CandySelection::getSugarcane).reversed())
                    .forEach(i -> strings.add(i.getString()));
        else if (forRadioButtons.getSelectedValue1().equals("Price Percent"))
            selections.stream().sorted(Comparator.comparingDouble(CandySelection::getPrecedence).reversed())
                    .forEach(i -> strings.add(i.getString()));
        else
            selections.stream().sorted(Comparator.comparingDouble(CandySelection::getPercent).reversed())
                    .forEach(i -> strings.add(i.getString()));
        listView.setItems(observableArrayList(strings));
    }

    private void any() {
        strings.add(selections.stream().findAny().get().getString());
        listView.setItems(observableArrayList(strings));
    }

    private void highest() {
        double maximumValue = getMaximumValue();
        selections.stream()
                .filter(i -> {
                    if (forRadioButtons.getSelectedValue1().equals("Price Percent"))
                        return i.getPrecedence() == (float) maximumValue;
                    else if (forRadioButtons.getSelectedValue1().equals("Win Percent"))
                        return i.getPercent() == (float) maximumValue;
                    else
                        return i.getSugarcane() == (float) maximumValue;

                })
                .forEach(i -> strings.add(i.getString()));
        listView.setItems(observableArrayList(strings));
    }

    private double getMaximumValue() {
        double doubleVal = selections.stream().mapToDouble(value -> {
            if (forRadioButtons.getSelectedValue1().equals("Price Percent"))
                return value.getPrecedence();
            if (forRadioButtons.getSelectedValue1().equals("Win Percent"))
                return value.getPercent();
            else
                return value.getSugarcane();
        }).max().getAsDouble();
        return doubleVal;
    }

    private int checkboxSelection() {
        int numberOfDistinct = 0;
        return candySelection(numberOfDistinct);
    }

    private int candySelection(int numberOfDistinct) {
        if (checkBox.isSelected()) {
            selections = selections.stream().filter(CandySelection::isChocolate).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox1.isSelected()) {
            selections = selections.stream().filter(CandySelection::isFruity).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox2.isSelected()) {
            selections = selections.stream().filter(CandySelection::isCaramel).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox3.isSelected()) {
            selections = selections.stream().filter(CandySelection::isPeanutAlmond).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox4.isSelected()) {
            selections = selections.stream().filter(CandySelection::isNougat).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox5.isSelected()) {
            selections = selections.stream().filter(CandySelection::isCrippleware).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox6.isSelected()) {
            selections = selections.stream().filter(CandySelection::isHard).collect(Collectors.toList());
            numberOfDistinct++;
        }
        else if (checkBox7.isSelected()) {
            selections = selections.stream().filter(CandySelection::isBar).collect(Collectors.toList());
            numberOfDistinct++;
        }
        return numberOfDistinct;
    }
}

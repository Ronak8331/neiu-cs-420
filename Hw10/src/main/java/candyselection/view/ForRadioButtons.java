package candyselection.view;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class ForRadioButtons {

        private RadioButton radioButton, radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
        private final ToggleGroup toggleGroup1, toggleGroup2;
        private HBox hBox, hBox2;

        public ForRadioButtons() {
            this.toggleGroup1 = new ToggleGroup();
            this.toggleGroup2 = new ToggleGroup();
            initializeRadioButton1();
            initializeRadioButton2();
            setRadioButtonHBox1();
            setRadioButtonHBox2();
        }

        private void initializeRadioButton1()
        {
            radioButton = new RadioButton("Sugar Percent");
            radioButton1 = new RadioButton("Price Percent");
            radioButton2 = new RadioButton("Win Percent");
            radioButton.setToggleGroup(toggleGroup1);
            radioButton1.setToggleGroup(toggleGroup1);
            radioButton2.setToggleGroup(toggleGroup1);
        }

        private void initializeRadioButton2()
        {
            radioButton3 = new RadioButton("Show Highest");
            radioButton4 = new RadioButton("Show Any");
            radioButton5 = new RadioButton("Show All");
            radioButton3.setToggleGroup(toggleGroup2);
            radioButton4.setToggleGroup(toggleGroup2);
            radioButton5.setToggleGroup(toggleGroup2);
        }

        private void setRadioButtonHBox1()
        {
            hBox = new HBox();
            hBox.getChildren().addAll(radioButton, radioButton1, radioButton2);
        }
        private void setRadioButtonHBox2()
        {
            hBox2 = new HBox();
            hBox2.getChildren().addAll(radioButton3, radioButton4, radioButton5);
        }

        public HBox gethBox() {
            return hBox;
        }

        public HBox gethBox2() {
        return hBox2;
    }

        public String getSelectedValue1()
        {
            String val = toggleGroup1.selectedToggleProperty().toString();
            if(val.contains("Sugar Percent"))
                return "Sugar Percent";
            else if(val.contains("Price Percent"))
                return "Price Percent";
            else
                return "Win Percent";
        }

        public String getSelectedValue2()
        {
            String val = toggleGroup2.selectedToggleProperty().toString();
            if(val.contains("Show Highest"))
                return "Show Highest";
            else if(val.contains("Show Any"))
                return "Show Any";
            else
                return "Show All";
        }
}

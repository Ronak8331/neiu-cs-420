package restaurantdomain.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

public class RadioButtons {

    private RadioButton grb1,grb2,grb3;
    private final ToggleGroup tg;
    private HBox graphicHBox;
    private javafx.scene.chart.PieChart ratingPieChart, cityPieChart, cuisinePieChart;
    private VBox gVBox;
    private final PieChartForData chartData;


    public RadioButtons() throws IOException, URISyntaxException, ParseException {
        chartData = new PieChartForData();
        tg = new ToggleGroup();
        initializeRadioButton();
        setGraphicRadioButtonHBox();
        createRadioListener();
    }

    private void createRadioListener()
    {
        tg.selectedToggleProperty().addListener(new GraphicRadioListener());
    }

    private class GraphicRadioListener implements ChangeListener<Toggle>
    {
        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
        {
            if(newValue.equals(grb1))
            {
                gVBox.getChildren().remove(cityPieChart);
                gVBox.getChildren().remove(cuisinePieChart);
                getRatingPieChart();
                gVBox.getChildren().add(ratingPieChart);
            }
            else if(newValue.equals(grb2))
            {
                gVBox.getChildren().remove(ratingPieChart);
                gVBox.getChildren().remove(cuisinePieChart);
                getCityPieChart();
                gVBox.getChildren().add(cityPieChart);
            }
            else
            {
                gVBox.getChildren().remove(cityPieChart);
                gVBox.getChildren().remove(ratingPieChart);
                getCuisinePieChart();
                gVBox.getChildren().add(cuisinePieChart);
            }
        }
    }

    private void initializeRadioButton()
    {
        grb1 = new RadioButton("Restaurant Distribution by Rating");
        grb2 = new RadioButton("Restaurant Distribution by Location");
        grb3 = new RadioButton("Restaurant Distribution by Cuisine Type");
        grb1.setToggleGroup(tg);
        grb2.setToggleGroup(tg);
        grb3.setToggleGroup(tg);
    }

    private void setGraphicRadioButtonHBox()
    {
        gVBox = new VBox();
        graphicHBox = new HBox();
        graphicHBox.getChildren().addAll(grb1,grb2,grb3);
        graphicHBox.setPadding(new Insets(10,0,0,0));
        graphicHBox.setAlignment(Pos.CENTER);
        graphicHBox.setSpacing(25);
        gVBox.getChildren().add(graphicHBox);

    }

    public HBox getGraphicHBox() {
        return graphicHBox;
    }

    private void getRatingPieChart()
    {
        ratingPieChart = chartData.getRatingPieChart();
    }

    private void getCityPieChart()
    {
        cityPieChart = chartData.getCityPieChart();
    }

    private void getCuisinePieChart(){
        cuisinePieChart = chartData.getCuisinePieChart();
    }
    public VBox getGVBox() {
        return gVBox;
    }

}

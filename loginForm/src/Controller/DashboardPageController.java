package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.util.Duration;

import javax.script.Bindings;
import java.util.Random;

public class DashboardPageController {
    @FXML
    private LineChart<?, ?> fxLinechart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    private int timer = 0;


    @FXML
    private PieChart fxPieChart;

    @FXML
    public void initialize() {

//        x.setAutoRanging(false);
//        x.setLowerBound(0);
//        x.setUpperBound(24);
//        x.setTickUnit(3);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Filled", 40),
                new PieChart.Data("free", 60));


        fxPieChart.getData().addAll(pieChartData);

        // Iterate through the chart's data and set the color for each segment
        for (PieChart.Data data : fxPieChart.getData()) {
            if (data.getName().equals("Filled")) {
                data.getNode().setStyle("-fx-pie-color: #78E08F;");
            }
            if (data.getName().equals("free")) {
                data.getNode().setStyle("-fx-pie-color: #4c4c4c;");
            }
        }


        fxLinechart.setCreateSymbols(false);

        fxLinechart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
//
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Temparature");
//        series.getData().add(new XYChart.Data("17:00", 15));
//        series.getData().add(new XYChart.Data("17:05", 20));
//        series.getData().add(new XYChart.Data("17:10", 15));
//        series.getData().add(new XYChart.Data("17:15", 60));
//
//
//
//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("Water Level");
//        series2.getData().add(new XYChart.Data("17:00", 1));
//        series2.getData().add(new XYChart.Data("17:05", 3));
//        series2.getData().add(new XYChart.Data("17:10", 5));
//        series2.getData().add(new XYChart.Data("17:15", 8));
//        fxLinechart.getData().addAll(series, series2);


        x.setTickLabelGap(4);
        x.setTickMarkVisible(false);


        XYChart.Series seriesTemp = new XYChart.Series<>();
        seriesTemp.setName("Temparature");


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), e -> {


            Random r = new Random();


            String nextMinute = "17:" + (timer);


            seriesTemp.getData().add(new XYChart.Data<>(nextMinute, r.nextDouble() * 100));

            if (seriesTemp.getData().size() >= 6) {
                seriesTemp.getData().remove(0);
            }


        }));
        fxLinechart.getData().add(seriesTemp);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        XYChart.Series seriesPH = new XYChart.Series<>();
        seriesPH.setName("pH");

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(2.5), e -> {

            Random r = new Random();
            String nextMinute = "17:" + (timer);
            timer = timer + 10;
            seriesPH.getData().add(new XYChart.Data<>(nextMinute, r.nextDouble() * 100));

            if (seriesPH.getData().size() >= 6) {
                seriesPH.getData().remove(0);
            }

        }));
        fxLinechart.getData().add(seriesPH);
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();

//        int numPoints = seriesTemp.getData().size();
//        if (numPoints > 4) {
//            x.setLowerBound(numPoints - 4);
//            x.setUpperBound(numPoints - 1);
//        }

    }
}

package software.ulpgc.kata4.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata4.model.Movie;
import software.ulpgc.kata4.viewmodel.Histogram;
import software.ulpgc.kata4.viewmodel.HistogramBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main extends JFrame {
    public Main() {
        this.setTitle("histograma de peliculas");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.display(hitogramOf(movies()));
        main.setVisible(true);
    }

    private void display(Histogram histogram) {
        this.getContentPane().add(displayOf(histogram));
        this.revalidate();
    }

    private Component displayOf(Histogram histogram) {
        return new ChartPanel(decorate(chartOf(histogram)));
    }

    private JFreeChart decorate(JFreeChart jFreeChart) {
        return jFreeChart;
    }

    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.title(),
                histogram.x(),
                "count",
                datasetOf(histogram)
        );
    }

    private XYSeriesCollection datasetOf(Histogram histogram) {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(seriesOf(histogram));
        return collection;
    }

    private XYSeries seriesOf(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.legend());
        for(int bin : histogram) {
            series.add(bin, histogram.cout(bin));
        }
        return series;
    }

    private static Histogram hitogramOf(Stream<Movie> movies) {
        return HistogramBuilder.with(movies)
                .title("peliculas por año")
                .x("año")
                .legend("num peliculas")
                .build(Movie::year);
    }
    

    private static Stream<Movie> movies() {
        return new RemoteMovieLoader(MovieDeserializer::from)
                .loadAll()
                .limit(1000)
                .filter(m -> m.year() >= 1900)
                .filter(m -> m.year() <= 2025);
    }

}

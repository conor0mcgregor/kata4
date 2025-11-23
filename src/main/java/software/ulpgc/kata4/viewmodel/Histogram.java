package software.ulpgc.kata4.viewmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Histogram implements Iterable<Integer> {
    private final Map<Integer, Integer> frecuense;
    private final Map<String, String> labels;

    public Histogram(Map<String, String> labels) {
        this.labels = labels;
        this.frecuense = new HashMap<>();
    }

    public String title() {
        return labels.getOrDefault("title", "");
    }
    public String x() {
        return labels.getOrDefault("x", "");
    }
    public String legend() {
        return labels.getOrDefault("legend", "");
    }

    public void add(int bin) {
        frecuense.put(bin, cout(bin) + 1);
    }

    public Integer cout(int bin) {
        return frecuense.getOrDefault(bin, 0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return frecuense.keySet().iterator();
    }

    public int size() {
        return frecuense.size();
    }

    public boolean isEmpty() {
        return frecuense.isEmpty();
    }


}

package anashkinv.androidschool2017.model;

import java.util.HashMap;

public class DayPrice {
    private HashMap<String, Float> history = new HashMap<String, Float>();

    public HashMap<String, Float> getHistory() {
        return history;
    }

    public void addHistory(String timestamp, Float price) {
        this.history.put(timestamp, price);
    }
}

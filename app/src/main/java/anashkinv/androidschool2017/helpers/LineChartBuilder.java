package anashkinv.androidschool2017.helpers;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineChartBuilder {
    private LineChart chart;

    public LineChartBuilder(LineChart chart) {
        this.chart = chart;
        setupChart();
    }

    private void setupLine(LineDataSet set) {
        set.setDrawCircleHole(false);
        set.setColor(Color.parseColor("#ab62ce"));
        set.setCircleColor(Color.parseColor("#ab62ce"));
        set.setDrawValues(false);
    }

    private void setupChart() {
        chart.getDescription().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setAxisLineColor(0);
        chart.setBackgroundColor(Color.parseColor("#f0f0f1"));
        chart.setDrawGridBackground(false);
        chart.getLegend().setEnabled(false);
    }

    public void addData(HashMap<String, Float> points) {
        List<Entry> entries = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Float> point : points.entrySet()) {
            entries.add(new Entry(i, point.getValue()));
            i++;
        }
        LineDataSet set = new LineDataSet(entries, "");
        setupLine(set);
        LineData data = new LineData(set);
        chart.setData(data);
        chart.invalidate();
    }
}

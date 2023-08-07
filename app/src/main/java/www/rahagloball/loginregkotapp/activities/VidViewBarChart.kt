package www.rahagloball.loginregkotapp.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R
class VidViewBarChart constructor() : AppCompatActivity() {
    //    LineChart lineChart;
    var data: IntArray = intArrayOf(3, 8, 13, 17, 22, 26, 32, 38, 42)
    var data1: IntArray = intArrayOf(26, 22, 17, 13, 8, 3, 23, 13, 8)
    var colorArray: IntArray = intArrayOf(R.color.AppBlue, R.color.AppYellow)
    var colorClassArray: IntArray = intArrayOf(Color.CYAN, Color.YELLOW)
    var legendName: Array<String> = arrayOf("Videos", "Cuties")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vid_view_bar_chart)

//        lineChart = findViewById(R.id.chart);

//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        LineDataSet lineDataSet1 = new LineDataSet(dataValuevids(data), "Video Views");
//        dataSets.add(lineDataSet1);
//        LineDataSet lineDataSetcts = new LineDataSet(dataValuects(data1), "Cuties Views");
//        dataSets.add(lineDataSetcts);
//
//        lineChart.setDrawBorders(true);
//        lineChart.setBorderColor(Color.BLUE);
//        lineChart.setNoDataTextColor(Color.BLUE);
//        lineChart.setDrawGridBackground(true);
//        lineChart.setDrawBorders(true);
//
//        Legend legend = lineChart.getLegend();
//        legend.setEnabled(true);
//        legend.setTextColor(Color.RED);
//        legend.setTextSize(15);
//        legend.setForm(Legend.LegendForm.LINE);
//        legend.setFormSize(20);
//        legend.setXEntrySpace(15);
//        legend.setFormToTextSpace(10);
//
//        lineDataSet1.setLineWidth(2);
//        lineDataSet1.setValueFormatter(new MyValueFormatter());
//        lineDataSet1.setColor(Color.CYAN);
//        lineDataSetcts.setLineWidth(3);
//        lineDataSetcts.setColor(Color.YELLOW);
//
//        lineDataSet1.setDrawCircles(true);
//        lineDataSet1.setDrawCircleHole(true);
//        lineDataSet1.setCircleColor(Color.GRAY);
//        lineDataSet1.setCircleHoleColor(Color.GREEN);
//        lineDataSet1.setCircleRadius(5);
//        lineDataSet1.setCircleHoleRadius(4);
//
//
//        LegendEntry[] legendEntries = new LegendEntry[2];
//        for (int i = 0; i < legendEntries.length; i++) {
//
//            LegendEntry entry = new LegendEntry();
//            entry.formColor = colorClassArray[i];
//            entry.label = String.valueOf(legendName[i]);
//            legendEntries[i] = entry;

//        }

//        legend.setCustom(legendEntries);

//        MyAxixValueFormatter
//        XAxis xAxis = lineChart.getXAxis();
//        YAxis yAxisLeft = lineChart.getAxisLeft();
//        YAxis yAxisRight = lineChart.getAxisRight();

//        xAxis.setValueFormatter(new MyAxixValueFormatter());

//        Description description = new Description();
//        description.setText("Views");
//        description.setXOffset(10);
//        description.setYOffset(10);
//        description.setTextColor(Color.BLACK);
//        description.setTextSize(20);
//        lineChart.setDescription(description);

//        LineData data = new LineData(dataSets);
//        lineChart.setData(data);
//        lineChart.invalidate();
    } //    private ArrayList<Entry> dataValuevids(int[] data) {
    //
    //        ArrayList<Entry> dataVals = new ArrayList<Entry>();
    //
    //        for (int i = 0; i < data.length; i++) {
    //            dataVals.add(new Entry(i, data[i]));
    //        }
    ////        dataVals.add(new Entry(0, 10));
    ////        dataVals.add(new Entry(2, 25));
    ////        dataVals.add(new Entry(4, 7));
    ////        dataVals.add(new Entry(6, 8));
    ////        dataVals.add(new Entry(8, 35));
    //
    //        return dataVals;
    //    }
    //    private ArrayList<Entry> dataValuects(int[] data1) {
    //
    //        ArrayList<Entry> dataVals1 = new ArrayList<Entry>();
    //        for (int i = 0; i < data1.length; i++) {
    //            dataVals1.add(new Entry(i, data1[i]));
    //        }
    //
    ////        dataVals.add(new Entry(0, 20));
    ////        dataVals.add(new Entry(3, 5));
    ////        dataVals.add(new Entry(5, 27));
    ////        dataVals.add(new Entry(7, 18));
    ////        dataVals.add(new Entry(9, 25));
    //
    //        return dataVals1;
    //    }
    //    private static class MyValueFormatter extends ValueFormatter {
    //
    //        @Override
    //        public String getFormattedValue(float value) {
    //
    //            return value + "#";
    //        }
    //
    //
    //    }
    //
    //    private static class MyAxixValueFormatter extends IndexAxisValueFormatter {
    //
    //        @Override
    //        public String getAxisLabel(float value, AxisBase axis) {
    //            return "Day" + value;
    //        }
    //    }
}
package www.rahagloball.loginregkotapp.fragments.ovrlstcks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class LtstVidsStackFrgmnt  //    private BarChart barChart;
    : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ttlltsttck_layout, container, false)
        //
//        barChart = viewexp.findViewById(R.id.bar_chart);
//
        // Create a list of 7 days starting from today
        val labels = ArrayList<String>()
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        for (i in 0..6) {
            val label = sdf.format(calendar.time)
            labels.add(label)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

//        // Create a list of random values for the y-axis
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 7; i++) {
//            float value = random.nextFloat() * 100;
//            entries.add(new BarEntry(i, value));
//        }
//
//        // Set the data for the bar chart
//        BarDataSet dataSet = new BarDataSet(entries, "Views");
//        BarData data = new BarData(dataSet);
//        barChart.setData(data);
//
//        // Set the labels for the x-axis
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
//        xAxis.setDrawGridLines(false);
//
//        // Set the values for the y-axis
//        YAxis yAxis = barChart.getAxisLeft();
//        yAxis.setAxisMinimum(0f);
//        yAxis.setDrawGridLines(false);
//
//        // Disable the right y-axis
//        barChart.getAxisRight().setEnabled(false);
//
//        // Set the chart description
//        Description description = new Description();
//        description.setText("");
//        barChart.setDescription(description);
//
//        // Set other chart settings as needed
//        barChart.setTouchEnabled(true);
//        barChart.setDragEnabled(true);
//        barChart.setScaleEnabled(true);
//        barChart.animateY(1000);
//        barChart.invalidate();
        return viewexp
    }
}
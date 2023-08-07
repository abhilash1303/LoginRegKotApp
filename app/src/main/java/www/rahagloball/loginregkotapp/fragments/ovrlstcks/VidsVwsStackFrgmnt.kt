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

class VidsVwsStackFrgmnt  //    private PieChart pieChart;
    : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ttlvidsvwstck_layout, container, false)


        // Create the pie chart and set its data
//        pieChart = viewexp.findViewById(R.id.pie_chart);
//        ArrayList<PieEntry> entries = new ArrayList<>();

// Generate the labels for the chart (7 days starting from a specific date)
        val labels = ArrayList<String>()
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        for (i in 0..6) {
            val label = sdf.format(calendar.time)
            labels.add(label)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

// Generate random data for the chart
//        Random rand = new Random();
//        for (int i = 0; i < 7; i++) {
//            float value = rand.nextFloat() * 100;
//            entries.add(new PieEntry(value, labels.get(i)));
//        }
//
//// Set the data for the chart
//        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart Data");
//        PieData data = new PieData(dataSet);
//        pieChart.setData(data);
//
//// Set colors for the chart
//        ArrayList<Integer> colors = new ArrayList<>();
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//        dataSet.setColors(colors);
//
//// Set other chart options
//        pieChart.setUsePercentValues(true);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(5, 10, 5, 5);
//        pieChart.setDragDecelerationFrictionCoef(0.95f);
//        pieChart.setDrawHoleEnabled(false);
//        pieChart.animateY(1000, Easing.EaseInOutCubic);
//        pieChart.getLegend().setEnabled(false);
//
//
//        // Set some options for the pie chart
//        pieChart.getLegend().setEnabled(false);
//        pieChart.setEntryLabelTextSize(14f);
//        pieChart.setEntryLabelColor(Color.WHITE);
//        pieChart.setCenterTextColor(Color.WHITE);
//        pieChart.setCenterTextSize(20f);
//        pieChart.setCenterText("Days");
        return viewexp
    }
}
package www.rahagloball.loginregkotapp.fragments.anlytcs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class  TabErnFragment  //    LineChart chart;
    : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_earn_layout, container, false)
        //        chart = viewexp.findViewById(R.id.chart_cts);

        // Get the current date
        val calendar = Calendar.getInstance()
        val currentDayOfMonth = calendar[Calendar.DAY_OF_MONTH]

        // Generate the labels for the X-axis (7 days starting from today)
        val labels = ArrayList<String>()
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        for (i in 0..6) {
            calendar[Calendar.DAY_OF_MONTH] = currentDayOfMonth + i
            val label = sdf.format(calendar.time)
            labels.add(label)
        }


//        XAxis xAxis = chart.getXAxis();
//        xAxis.setTextColor(Color.WHITE); // Change the color to red
//
//
//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTextColor(Color.WHITE);

//        YAxis yAxis = chart.getYAxis();
//        yAxis.setTextColor(Color.WHITE); // Change the color to red


//        // Set up the chart
//        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart.getAxisRight().setEnabled(false);
//        chart.getDescription().setEnabled(false);
//        chart.setTouchEnabled(false);
//
//        LineData data = new LineData();
//        data.addDataSet(getMoneyDataSet(currentDayOfMonth));
//        data.addDataSet(getMoneySpentDataSet(currentDayOfMonth));
//        chart.setData(data);
//
//        // Refresh the chart
//        chart.invalidate();
        return viewexp
    }

    //
    //    private LineDataSet getMoneyDataSet(int currentDayOfMonth) {
    //        ArrayList<Entry> entries = new ArrayList<>();
    //        for (int i = 0; i < 7; i++) {
    //            float moneyData = getMoneyData(currentDayOfMonth + i);
    //            entries.add(new Entry(i, moneyData));
    //        }
    //
    //
    ////        chart.setExtraTopOffset(20f);
    //        LineDataSet dataSet = new LineDataSet(entries, "Supporters");
    //        dataSet.setColor(Color.BLUE);
    //        dataSet.setDrawValues(false);
    //        chart.setExtraBottomOffset(10f);
    //        dataSet.setValueTextColor(Color.WHITE);
    //        dataSet.setCircleColor(Color.BLUE);
    //        dataSet.setLineWidth(2f);
    //        dataSet.setCircleRadius(4f);
    //        dataSet.setDrawValues(false);
    //        dataSet.setDrawCircleHole(false);
    //
    //        return dataSet;
    //    }
    //
    //    private LineDataSet getMoneySpentDataSet(int currentDayOfMonth) {
    //        ArrayList<Entry> entries = new ArrayList<>();
    //        for (int i = 0; i < 7; i++) {
    //            float moneySpentData = getMoneySpentData(currentDayOfMonth + i);
    //            entries.add(new Entry(i, moneySpentData));
    //        }
    //
    //        LineDataSet dataSet = new LineDataSet(entries, "Super Supporters");
    //        dataSet.setColor(Color.RED);
    //        dataSet.setValueTextColor(Color.WHITE);
    //        dataSet.setCircleColor(Color.RED);
    //        dataSet.setLineWidth(2f);
    //        dataSet.setCircleRadius(4f);
    //        dataSet.setDrawValues(false);
    //        dataSet.setDrawCircleHole(false);
    //
    //        return dataSet;
    //    }
    private fun getMoneyData(dayOfMonth: Int): Float {
        // Here, you would retrieve the money data for the given day from your data source
        // For the sake of example, let's just return a random value between 0 and 100
        return (Math.random() * 100).toFloat()
    }

    private fun getMoneySpentData(dayOfMonth: Int): Float {
        // Here, you would retrieve the money spent data for the given day from your data source
        // For the sake of example, let's just return a random value between 0 and 100
        return (Math.random() * 100).toFloat()
    }
}
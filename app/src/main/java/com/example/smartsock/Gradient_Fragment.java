package com.example.smartsock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.HeatDataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.HeatMap;
import com.anychart.charts.Pie;
import com.anychart.enums.SelectionMode;
import com.anychart.graphics.vector.SolidFill;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Gradient_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gradient_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Gradient_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gradient_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Gradient_Fragment newInstance(String param1, String param2) {
        Gradient_Fragment fragment = new Gradient_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gradient_, container, false);


        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        HeatMap riskMap = AnyChart.heatMap();
        riskMap.hovered()
                .stroke("6 #fff")
                .fill(new SolidFill("#545f69", 1d))
                .labels("{ fontColor: '#fff' }");
        riskMap.interactivity().selectionMode(SelectionMode.NONE);
        riskMap.title().enabled(true);
        riskMap.title()
                .text("Left Side of Limb")
                .padding(0d, 0d, 20d, 0d);
        riskMap.labels().enabled(true);
        riskMap.labels()
                .minFontSize(14d)
                .format("function() {\n" +
                        "      var namesList = [\"Low\", \"Med\", \"High\", \"Ext\"];\n" +
                        "      return namesList[this.heat];\n" +
                        "    }");

        riskMap.yAxis(0).stroke(null);
        riskMap.yAxis(0).labels().padding(0d, 15d, 0d, 0d);
        riskMap.yAxis(0).ticks(false);
        riskMap.xAxis(0).stroke(null);
        riskMap.xAxis(0).ticks(false);
        riskMap.tooltip().title().useHtml(true);
        riskMap.tooltip()
                .useHtml(true)
                .titleFormat("function() {\n" +
                        "      var namesList = [\"Low\", \"Med\", \"High\", \"Ext\"];\n" +
                        "      return '<b>' + namesList[this.heat] + '</b> Pressure';\n" +
                        "    }")
                .format("function () {\n" +
                        "       return '<span style=\"color: #CECECE\">Row (x): </span>' + this.x + '<br/>' +\n" +
                        "           '<span style=\"color: #CECECE\">Column (y): </span>' + this.y;\n" +
                        "   }");

        //Colours
        //Red = #d84315; Orange = #ef6c00; Yellow = #ffb74d; Blue = #90caf9"

        List<DataEntry> data = new ArrayList<>();
        //Row 1
        data.add(new CustomHeatDataEntry("1","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("1","2",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("1","3",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("1","4",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("1","5",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("1","6",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("1","7",0, "#90caf9"));

        //Row 2
        data.add(new CustomHeatDataEntry("2","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("2","2",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("2","3",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("2","4",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("2","5",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("2","6",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("2","7",0, "#90caf9"));

        //Row 3
        data.add(new CustomHeatDataEntry("3","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("3","2",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("3","3",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("3","4",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("3","5",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("3","6",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("3","7",0, "#90caf9"));

        //Row 4
        data.add(new CustomHeatDataEntry("4","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("4","2",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("4","3",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("4","4",3, "#d84315"));
        data.add(new CustomHeatDataEntry("4","5",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("4","6",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("4","7",0, "#90caf9"));

        //Row 5
        data.add(new CustomHeatDataEntry("5","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("5","2",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("5","3",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("5","4",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("5","5",2, "#ef6c00"));
        data.add(new CustomHeatDataEntry("5","6",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("5","7",0, "#90caf9"));

        //Row 6
        data.add(new CustomHeatDataEntry("6","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("6","2",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("6","3",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("6","4",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("6","5",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("6","6",1, "#ffb74d"));
        data.add(new CustomHeatDataEntry("6","7",0, "#90caf9"));

        //Row 7
        data.add(new CustomHeatDataEntry("7","1",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("7","2",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("7","3",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("7","4",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("7","5",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("7","6",0, "#90caf9"));
        data.add(new CustomHeatDataEntry("7","7",0, "#90caf9"));

        riskMap.data(data);
        anyChartView.setChart(riskMap);

        return view;

    }

    private class CustomHeatDataEntry extends HeatDataEntry {
        CustomHeatDataEntry(String x, String y, Integer heat, String fill) {
            super(x, y, heat);
            setValue("fill", fill);
        }
    }
}
package com.example.smartsock;

import static java.lang.Math.round;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.HeatDataEntry;
import com.anychart.charts.HeatMap;
import com.anychart.enums.SelectionMode;
import com.anychart.graphics.vector.SolidFill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class fragment_2dArrayGraph extends Fragment {

    TextView incomingdata;
    StringBuilder messages;

    String text;
    StringBuilder text2;
    String singlestring;
    String[][] values_array;
    int num_rows = 10;
    int num_cols = 10;
    String pressure_number = "hi";


    public fragment_2dArrayGraph() {
        // Required empty public constructor
    }

    public static fragment_2dArrayGraph newInstance(String param1, String param2) {
        fragment_2dArrayGraph fragment = new fragment_2dArrayGraph();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            text = intent.getStringExtra("theMessage");
            text2 = messages.append(text);
            singlestring = text2.toString();
            incomingdata.setText(singlestring);
            //incomingdata.setText("")
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2d_array_graph, container, false);


        incomingdata =(TextView) view.findViewById(R.id.incomingdata);
        messages = new StringBuilder();
        incomingdata.setMovementMethod(new ScrollingMovementMethod());
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver, new IntentFilter("incomingMessage"));


        // Create HeatMap
        HeatMap riskMap = AnyChart.heatMap();
        riskMap.credits(false);
        // Heatmap Features
        riskMap.hovered()
                .stroke("6 #fff")
                .fill(new SolidFill("#545f69", 1d))
                .labels("{ fontColor: '#fff' }");
        riskMap.interactivity().selectionMode(SelectionMode.NONE);
        riskMap.title().enabled(false);
        riskMap.title()
                .text("Left Side of Limb")
                .padding(0d, 0d, 20d, 0d);
        riskMap.labels().enabled(false);
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
                        "      return '<b>' + namesList[this.heat] + '</b> Pressure' + '</b> ' + this.heat;\n" +
                        "    }")
                .format("function () {\n" +
                        "       return '<span style=\"color: #CECECE\">Row (x): </span>' + this.x + '<br/>' +\n" +
                        "           '<span style=\"color: #CECECE\">Voltage (y): </span>' + this.heat;\n" +
                        "           '<span style=\"color: #CECECE\">Column (y): </span>' + this.y;\n" +
                        "   }");
        //riskMap.legend(true);

        riskMap.xScroller().enabled(true);
        //riskMap.xZoom().setToPointsCount(8);
        riskMap.yScroller().enabled(true);
        //riskMap.yZoom().setToPointsCount(10);

        // Data
        int [][] voltage_values = {{0, 0, 200, 300, 400, 450, 650, 800, 800, 800, 800},
                                   {0, 100, 200, 300, 400, 500, 500, 700, 900, 900, 800},
                                   {0, 100, 200, 300, 400, 550, 800, 1000, 1000, 900, 800},
                                   {0, 100, 200, 300, 400, 500, 500, 500, 900, 900, 800},
                                   {0, 100, 200, 350, 450, 700, 700, 500, 500, 500, 500},
                                   {0, 50, 50, 100, 200, 300, 600, 600, 400, 400, 400},
                                   {0, 0, 0, 0, 200, 300, 400, 400, 300, 200, 0},
                                   {0, 0, 0, 0, 0, 0, 200, 200, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 50, 100, 200, 600, 700, 600, 200, 100, 50, 0},
                                   {0, 100, 200, 300, 700, 800, 700, 300, 200, 100, 0}};

        int Max_pressure = 1000;
        int Min_Pressure = 0;
        float max_pressure_colour = 0; //Red on the HUE Color Spectrum
        float min_pressure_colour = 240; //Blue on the HUE colour Spectrum


       // Create data list
        List<DataEntry> data = new ArrayList<>();
        for (int row2 = 0; row2 <= voltage_values.length - 1; row2++) {
            for (int col2 = 0; col2 <= voltage_values[0].length - 1; col2++) {
                //double voltages = voltage_values[col2][row2] * 100;
                //int voltages_int = (int) voltages;
                data.add(new CustomHeatDataEntry(row2 + 1 + "",col2 + 1 + "", voltage_values[col2][row2],IntegerToHslColour(voltage_values[col2][row2],Max_pressure, min_pressure_colour,max_pressure_colour )));
            }
        }

        // Apply data to the chart
        riskMap.data(data);

        // Render the chart
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setChart(riskMap); //This loads the graph again


/*        // Simulate Real-time updates
        final int delayMillis = 500;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {
                // create new data List and populate it with values
                List<DataEntry> data = new ArrayList<>();
                for (int row3 = 0; row3 <= voltage_values.length - 1; row3++) {
                    for (int col3 = 0; col3 <= voltage_values[0].length - 1; col3++) {
                        //double voltages = voltage_values[col2][row2] * 100;
                        //int voltages_int = (int) voltages;
                        int randomNum = ThreadLocalRandom.current().nextInt(Min_Pressure, Max_pressure + 1);
                        data.add(new CustomHeatDataEntry(row3 + 1 + "", col3 + 1 + "", randomNum, IntegerToHslColour(randomNum, Max_pressure, min_pressure_colour, max_pressure_colour)));
                    }
                }
                // apply the new List to the existing chart
                riskMap.data(data);

                handler.postDelayed(this, delayMillis);
            }
        };

        handler.postDelayed(runnable, delayMillis);*/
        return view;
    }

    private class CustomHeatDataEntry extends HeatDataEntry {
        CustomHeatDataEntry(String row, String col, int voltage, String fill) {
            super(row, col, voltage);
            setValue("fill", fill);
        }
    }


     /*
     IntegerToHslColour Function Description:
     The function below simply takes a number within a range from zero to a maximum number, where
     the max number is set in the function. It will then find the percentage of this number based
     on the set maximum number. E.g., a number of 50 in a range of 0 to 100 results in a percentage
     of 50%.
     The percentage is then used to find an appropriate HUE angle on the HUE colour wheel. hue0 and
     hue1 set the colour range (e.g. blue to red or Green to red). E.g. setting Hue0 to 0 degrees
     corresponds to red and setting hue1 to 240 degrees corresponds to a colour of blue.
     */

    // Percentage: A decimal value between 0 and 1
    // Hue0: The HUE value of the color you want to get when the percentage is 0
    // Hue1: The HUE value of the color you want to get when the percentage is 1
    // Pressure: The pressure value received from pressure sensors
    // max_pressure: set the max pressure
    // hsl = Hue Saturation Lightness
    // hsl function (STRING): "hsl(HUE, Saturation, Lightness)" or "hsl(degrees, percentage, percentage)"

    public String IntegerToHslColour(float pressure,float max_pressure, float hue0, float hue1) {
        // Set --> pressure = 165; max_pressure = 330; hue0 = 240 degrees (BLUE); hue1 = 0 degrees (RED)
        float percentage = 1 - ((max_pressure - pressure)/max_pressure);
        // Output --> percentage = 0.50 or 50%
        float hue = percentage * (hue1 - hue0) + hue0;
        // Output --> hue = 120 degrees
        //int hue2 = (int) Math.round(hue);
        // Output --> hue2 = 120
        return "hsl("+hue+", 100%, 50%)";
        // Output --> return = "hsl(120.768, 100%, 50%)" = GREEN on HUE colour wheel
    }

}
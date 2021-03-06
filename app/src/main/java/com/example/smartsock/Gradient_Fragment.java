package com.example.smartsock;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.HeatDataEntry;
import com.anychart.charts.HeatMap;
import com.anychart.core.ui.table.Row;
import com.anychart.enums.SelectionMode;
import com.anychart.graphics.vector.SolidFill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Gradient_Fragment extends Fragment {

    public Gradient_Fragment() {
        // Required empty public constructor
    }

    public static Gradient_Fragment newInstance(String param1, String param2) {
        Gradient_Fragment fragment = new Gradient_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gradient_, container, false);

        //Getting values in row and col inputs
        EditText Row = view.findViewById(R.id.edittextRow);
        EditText Col = view.findViewById(R.id.edittextCol);
        Button apply_button = view.findViewById(R.id.apply_button);
        Button clear =view.findViewById(R.id.clear);
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        HeatMap riskMap = AnyChart.heatMap();

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

        //Colours: Red = #d84315; Orange = #ef6c00; Yellow = #ffb74d; Blue = #90caf9"

        // make this scalable
        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets whatever is input in the username field and converts the text into a string
                String inputRow = Row.getText().toString();
                String inputCol = Col.getText().toString();

                //data.add(new CustomHeatDataEntry(inputRow,inputCol,0, "#90caf9"));
                if (inputRow.isEmpty() || inputCol.isEmpty() ) {
                    //Display a message toast to user to enter the details
                    Toast.makeText(getContext(), "Please enter both number of Rows and Columns!", Toast.LENGTH_LONG).show();
                }

                else if (Integer.parseInt(inputRow) == 0 || Integer.parseInt(inputCol) == 0 ){
                        Toast.makeText(getContext(), "Please enter values greater then 0", Toast.LENGTH_LONG).show();
                }

                else {
                    //anyChartView.clear();
                    List<DataEntry> data = new ArrayList<>();
                    for (int row = 0; row <= Integer.parseInt(inputRow) - 1; row++) {
                        for (int col = 0; col <= Integer.parseInt(inputCol) - 1; col++) {
                            data.add(new  CustomHeatDataEntry(row, col, 1, "#90caf9"));
                        }
                    }
                    riskMap.data(data);
                    anyChartView.setChart(riskMap);
                    Row.setText("");
                    Col.setText("");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //List<DataEntry> data = new ArrayList<>();
                anyChartView.clear();
                //data.add(new  CustomHeatDataEntry(0, 0, 0, "#90caf9"));
                //riskMap.data(data);
                //anyChartView.setChart(riskMap);
                //anyChartView.clear();
                //anyChartView.recomputeViewAttributes(anyChartView);
            }
        });

        return view;
    }

    private class CustomHeatDataEntry extends HeatDataEntry {
        CustomHeatDataEntry(int row, int col, int voltage, String fill) {
            super(String.valueOf(row), String.valueOf(col), voltage);
            setValue("fill", fill);
        }
    }


    public void randomarray(int numRows, int numCols, int lowerlim, int upperlimit) {
        int [][] array = new int[numRows][numCols];
        Random random = new Random();
        for(int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(upperlimit - lowerlim) + lowerlim;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}

/*
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
*/

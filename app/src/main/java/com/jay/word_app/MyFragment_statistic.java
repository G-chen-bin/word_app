package com.jay.word_app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;


public class MyFragment_statistic extends Fragment {

    private Context mContext;
    private ColumnChartView mColumnChartCc;
    private ColumnChartData data; // 柱形图对应的各种属性
    private boolean hasAxes = true; // 是否要添加横纵轴的属性
    private boolean hasAxesNames = true; // 是否设置横纵轴的名字
    private boolean hasLabels = false; // 是否显示柱形图的数据
    private boolean hasLabelForSelected = true; // 是否点中显示数据

    private static final int DEFAULT_DATA = 0;
    private static final int SUBCOLUMNS_DATA = 1;
    private static final int STACKED_DATA = 2;
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
    private static final int NEGATIVE_STACKED_DATA = 4;

    private int dataType = SUBCOLUMNS_DATA;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mContext = getActivity();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_layout,container,false);

        mColumnChartCc = (ColumnChartView) view.findViewById(R.id.column_chart_cc);
        mColumnChartCc.setOnValueTouchListener(new ValueTouchListener());
        generateData();
        return view;
    }

    private void generateData() {
        switch (dataType) {
            case DEFAULT_DATA:
                generateDefaultData();
                break;
            case SUBCOLUMNS_DATA:
                generateSubcolumnsData();
                break;
           /* case STACKED_DATA:
                generateStackedData();
                break;
            case NEGATIVE_SUBCOLUMNS_DATA:
                generateNegativeSubcolumnsData();
                break;
            case NEGATIVE_STACKED_DATA:
                generateNegativeStackedData();
                break;
            default:
                generateDefaultData();
                break;*/
        }
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.column_chart, menu);

    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            reset();
            generateData();
            return true;
        }
        if (id == R.id.action_subcolumns) {
            dataType = SUBCOLUMNS_DATA;
            generateData();
            toggleLabels();
            return true;
        }
        if (id == R.id.action_stacked) {
            dataType = STACKED_DATA;
            generateData();
            return true;
        }
        if (id == R.id.action_negative_subcolumns) {
            dataType = NEGATIVE_SUBCOLUMNS_DATA;
            generateData();
            return true;
        }
        if (id == R.id.action_negative_stacked) {
            dataType = NEGATIVE_STACKED_DATA;
            generateData();
            return true;
        }
        if (id == R.id.action_toggle_labels) {
            toggleLabels();
            return true;
        }
        if (id == R.id.action_toggle_axes) {
            toggleAxes();
            return true;
        }
        if (id == R.id.action_toggle_axes_names) {
            toggleAxesNames();
            return true;
        }
        if (id == R.id.action_animate) {
            prepareDataAnimation();
            mColumnChartCc.startDataAnimation();
            return true;
        }
        if (id == R.id.action_toggle_selection_mode) {
            toggleLabelForSelected();

            Toast.makeText(mContext,
                    "Selection mode set to " + mColumnChartCc.isValueSelectionEnabled() + " select any point.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_toggle_touch_zoom) {
            mColumnChartCc.setZoomEnabled(!mColumnChartCc.isZoomEnabled());
            Toast.makeText(mContext, "IsZoomEnabled " + mColumnChartCc.isZoomEnabled(), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_zoom_both) {
            mColumnChartCc.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
            return true;
        }
        if (id == R.id.action_zoom_horizontal) {
            mColumnChartCc.setZoomType(ZoomType.HORIZONTAL);
            return true;
        }
        if (id == R.id.action_zoom_vertical) {
            mColumnChartCc.setZoomType(ZoomType.VERTICAL);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void reset() {
        hasAxes = true;
        hasAxesNames = true;
        hasLabels = false;
        hasLabelForSelected = false;
        dataType = DEFAULT_DATA;
        mColumnChartCc.setValueSelectionEnabled(hasLabelForSelected);

    }
    /**
     * type 0~4
     */
    private void generateDefaultData() {
        int numberSubcolumns = 1;
        int numColumns = 8;
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; i++) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numberSubcolumns; j++) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        mColumnChartCc.setColumnChartData(data);
    }

    /**
     * Generates columns with subcolumns, columns have larger separation than subcolumns.
     */
    private void generateSubcolumnsData() {
        int numSubcolumns = 1;
        int numColumns = 5;
        // Column can have many subcolumns, here I use 4 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> axisValueList = new ArrayList<>();
        Float[] floats = {30f, 5f, 50f, 15f,30f};
        String[] selecedNames = {"前天", "昨天","今天", "明天", "后天"};

        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(floats[i], ChartUtils.pickColor()));
            axisValueList.add(new AxisValue(i).setLabel(selecedNames[i]));
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);

        }

        data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis(axisValueList));
        data.setAxisYLeft(new Axis());

        mColumnChartCc.setColumnChartData(data);

        mColumnChartCc.setZoomEnabled(false);//设置缩放
        mColumnChartCc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /**
     * Generates columns with stacked subcolumns.
     */
    /*private void generateStackedData() {

        int numSubcolumns = 1;
        int numColumns = 4;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        Float[] floats = {30f, 5f, 50f, 15f};
        String[] selecedNames = {"选项一", "选项二", "选项三", "选项四"};

        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(floats[i], ChartUtils.pickColor()));
            axisValues.add(new AxisValue(i).setLabel(selecedNames[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        data = new ColumnChartData(columns);

        data.setAxisXBottom(new Axis(axisValues));
        data.setAxisYLeft(new Axis());

        mColumnChartCc.setColumnChartData(data);

        // Set value touch listener that will trigger changes for chartTop.
        // mColumnChartCc.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        mColumnChartCc.setValueSelectionEnabled(true);

    }*/

    /*private void generateNegativeSubcolumnsData() {

        int numSubcolumns = 4;
        int numColumns = 4;
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                int sign = getSign();
                values.add(new SubcolumnValue((float) Math.random() * 50f * sign + 5 * sign, ChartUtils.pickColor
                        ()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        mColumnChartCc.setColumnChartData(data);
    }

    private void generateNegativeStackedData() {

        int numSubcolumns = 4;
        int numColumns = 8;
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                int sign = getSign();
                values.add(new SubcolumnValue((float) Math.random() * 20f * sign + 5 * sign, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        // Set stacked flag.
        data.setStacked(true);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        mColumnChartCc.setColumnChartData(data);
    }*/



    /*private int getSign() {
        int[] sign = new int[]{-1, 1};
        return sign[Math.round((float) Math.random())];
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;

        if (hasLabels) {
            hasLabelForSelected = false;
            mColumnChartCc.setValueSelectionEnabled(hasLabelForSelected);
        }

        generateData();
    }

    private void toggleLabelForSelected() {
        hasLabelForSelected = !hasLabelForSelected;
        mColumnChartCc.setValueSelectionEnabled(hasLabelForSelected);

        if (hasLabelForSelected) {
            hasLabels = false;
        }

        generateData();
    }

    private void toggleAxes() {
        hasAxes = !hasAxes;

        generateData();
    }

    private void toggleAxesNames() {
        hasAxesNames = !hasAxesNames;

        generateData();
    }

    private void prepareDataAnimation() {
        for (Column column : data.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 100);
            }
        }
    }*/

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {
        @Override
        public void onValueSelected(int i, int i1, SubcolumnValue value) {

        }

        @Override
        public void onValueDeselected() {

        }
    }
}

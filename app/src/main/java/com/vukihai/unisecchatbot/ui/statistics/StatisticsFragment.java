package com.vukihai.unisecchatbot.ui.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.vukihai.unisecchatbot.R;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel statisticsViewModel;
    PieChart pieChart;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statisticsViewModel =
                ViewModelProviders.of(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        statisticsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        pieChart = (PieChart) root.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yvalues = new ArrayList<>();

        yvalues.add(new PieEntry(34,"Haui"));
        yvalues.add(new PieEntry(38,"Hust"));
        yvalues.add(new PieEntry(40,"UET"));
        yvalues.add(new PieEntry(50,"BA"));

       /* Description description = new Description();
        description.setText("Biểu đồ tỉ lệ hỏi các trường đại học");
        description.setTextSize(9);

        pieChart.setDescription(description);*/

        PieDataSet dataSet = new PieDataSet(yvalues, "School");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(10f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.RED);

        pieChart.setData(data);
        return root;
    }
}
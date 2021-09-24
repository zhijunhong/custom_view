package com.example.view_path;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_path.widget.SimpleLineChart;

import java.util.HashMap;

/**
 * author: zhijunhong
 * created on: 2021/9/6 8:08 PM
 * version: 1.0
 * description: 简约的折线图
 */
public class SimpleLineChartActivity extends AppCompatActivity {
    private SimpleLineChart mLineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_line_chart);
        mLineChart = findViewById(R.id.slc);

        String[] xItem = {"1","2","3","4","5","6","7"};
        String[] yItem = {"10k","20k","30k","40k","50k","60k","70k"};

        mLineChart.setXItems(xItem);
        mLineChart.setYItems(yItem);

        HashMap<Integer, Integer> pointMap = new HashMap<>();
        for (int i = 0; i < xItem.length; i++) {
            pointMap.put(i, (int) (Math.random() * 5));
        }
        mLineChart.setData(pointMap);
    }
}

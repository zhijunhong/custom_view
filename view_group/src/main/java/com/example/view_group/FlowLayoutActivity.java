package com.example.view_group;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.view_group.widget.Flowlayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zhijunhong
 * created on: 2021/8/27 2:42 PM
 * version: 1.0
 * description:
 */
public class FlowLayoutActivity extends AppCompatActivity {

    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        data.add("我要做远方的忠诚的儿子");
        data.add("和物质的");
        data.add("短暂情人");
        data.add("和所有以梦为马的");
        data.add("诗人一样");
        data.add("我");
        data.add("不得不和");
        data.add("烈士和小丑");
        data.add("走在同一道路上");

        Flowlayout flowLayout = findViewById(R.id.flow_layout);
        flowLayout.setData(data);
    }
}

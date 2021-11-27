package com.example.view_path;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_goto_search_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_radar_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RadarViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_bezier_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BezierViewActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.btn_goto_bezier_view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BezierViewActivity2.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_sin_wave_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SinWaveActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_circle_percent_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CirclePercentActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_simple_line_chart_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleLineChartActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_juejin_loading_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JueJinLoadingActivity.class);
                startActivity(intent);
            }
        });

    }
}

package com.example.view_group;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_goto_flow_layout).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FlowLayoutActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_goto_draw_menu).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrawMenuActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_goto_bottom_sheet_behavior).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BottomSheetBehaviorActivity.class);
            startActivity(intent);
        });
    }
}
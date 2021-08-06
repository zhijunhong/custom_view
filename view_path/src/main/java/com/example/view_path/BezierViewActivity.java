package com.example.view_path;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_path.widget.DrawingWithBezier;

/**
 * author: zhijunhong
 * created on: 2021/6/19 10:14 AM
 * version: 1.0
 * description:
 */
public class BezierViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingWithBezier(this));
    }
}

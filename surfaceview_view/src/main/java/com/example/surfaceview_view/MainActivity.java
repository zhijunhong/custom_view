package com.example.surfaceview_view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surfaceview_view.widget.MySurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}
package com.example.view_path;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_path.widget.SinWave;

/**
 * author: zhijunhong
 * created on: 2021/6/19 10:14 AM
 * version: 1.0
 * description:
 */
public class SinWaveActivity extends AppCompatActivity {
    private SinWave mSinWave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_wave_view);
        mSinWave = findViewById(R.id.sw);

        mSinWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSinWave.start();
            }
        });
    }
}

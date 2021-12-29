package com.example.view_path;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_path.widget.WaveLoadingView;

/**
 * author: zhijunhong
 * created on: 2021/6/19 10:14 AM
 * version: 1.0
 * description:
 */
public class WaveLoadingViewActivity extends AppCompatActivity {
    private WaveLoadingView mWaveLoadingView;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_loading);

        mWaveLoadingView = findViewById(R.id.vlv);
        mSeekBar = findViewById(R.id.sb_seek);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWaveLoadingView.setPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

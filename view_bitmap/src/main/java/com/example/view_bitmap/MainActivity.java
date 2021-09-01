package com.example.view_bitmap;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckView cv = findViewById(R.id.cv);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cv.isCheck()) {
                    cv.unCheck();
                } else {
                    cv.check();
                }
            }
        });
    }
}
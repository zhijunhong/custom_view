package com.example.camera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_system_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //系统camera
                Intent intent = new Intent(MainActivity.this, SystemCameraActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_custom_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自定义camera
                // TODO: 2020-06-18
            }
        });
    }
}

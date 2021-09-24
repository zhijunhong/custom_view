package com.example.view_region;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemoteControlMenu rcm = findViewById(R.id.rcm);

        rcm.setListener(new RemoteControlMenu.MenuListener() {
            @Override
            public void onCenterCliched() {
                Toast.makeText(MainActivity.this,"CenterCliched",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpCliched() {
                Toast.makeText(MainActivity.this,"UpCliched",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCliched() {
                Toast.makeText(MainActivity.this,"RightCliched",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownCliched() {
                Toast.makeText(MainActivity.this,"DownCliched",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftCliched() {
                Toast.makeText(MainActivity.this,"LeftCliched",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
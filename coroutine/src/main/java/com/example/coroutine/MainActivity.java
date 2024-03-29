package com.example.coroutine;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_text).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoroutineActivity.class);
            startActivity(intent);
        });
    }
}
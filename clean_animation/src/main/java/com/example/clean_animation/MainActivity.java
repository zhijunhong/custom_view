package com.example.clean_animation;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clean_animation.listener.VisibilityAnimationListener;
import com.example.clean_animation.manager.DataCleanManager;

public class MainActivity extends AppCompatActivity {
    private Button mBtn;
    private Handler mHandler = new Handler();
    private DataCleanManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindEvent();
        initData();
    }

    private void initData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                //缓存大小
//                tv.setText(dm.getTotalCacheSize(MainActivity.this));
                //清理缓存
                dm.clearAllCache(MainActivity.this);
                Toast.makeText(MainActivity.this, "清理完成", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }


    private void bindEvent() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        launchRocket();
                        launchCloud();
                        startLauncher();
                    }
                }, 150);
            }
        });
    }

    private void startLauncher() {
        View launcher = findViewById(R.id.launcher);
        Animation animationLaunch= AnimationUtils.loadAnimation(MainActivity.this, R.anim.launch);
        animationLaunch.setAnimationListener(new VisibilityAnimationListener(launcher));
        launcher.startAnimation(animationLaunch);
    }

    private void launchCloud() {
        View cloud = findViewById(R.id.cloud);
        Animation animationCloud = AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud);
        animationCloud.setAnimationListener(new VisibilityAnimationListener(cloud));
        cloud.startAnimation(animationCloud);
    }

    private void launchRocket() {
        View rocket = findViewById(R.id.rocket);
        Animation animationRocket = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rocket);
        animationRocket.setAnimationListener(new VisibilityAnimationListener(rocket));
        rocket.startAnimation(animationRocket);
    }

    private void initView() {
        mBtn = findViewById(R.id.getup);
    }
}

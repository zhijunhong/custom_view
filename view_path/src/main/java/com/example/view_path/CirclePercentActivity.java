package com.example.view_path;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_path.widget.CirclePercentView;

/**
 * author: zhijunhong
 * created on: 2021/8/31 4:46 PM
 * version: 1.0
 * description: 圆形加载进度
 */
public class CirclePercentActivity extends AppCompatActivity {
    private CirclePercentView mCirclePercent;
    private ValueAnimator mAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_precent);

        initView();
        initData();
        bindEvent();
    }

    private void initData() {
        mAnimator = ValueAnimator.ofInt(0, 100);
        mAnimator.setDuration(2000);
    }

    private void bindEvent() {
        mCirclePercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimator.start();
            }
        });

        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                Log.i("CirclePercentActivity", "animatedValue:" + animatedValue);
                mCirclePercent.setCurrentPercent(animatedValue);
            }
        });

    }

    private void initView() {
        mCirclePercent = findViewById(R.id.cpv);


    }
}

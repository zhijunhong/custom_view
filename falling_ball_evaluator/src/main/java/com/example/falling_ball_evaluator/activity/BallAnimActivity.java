package com.example.falling_ball_evaluator.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.falling_ball_evaluator.R;
import com.example.falling_ball_evaluator.animation.FullingBallEvaluator;
import com.example.falling_ball_evaluator.entity.Point;
import com.example.falling_ball_evaluator.widget.FullingBallImageView;

/**
 * author: zhijunhong
 * created on: 2021/8/2 3:00 PM
 * version: 1.0
 * description: 自定义Object Animator属性动画
 */
public class BallAnimActivity extends FragmentActivity {
    private TextView mTVRest;
    private FullingBallImageView mBallImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_animator);

        initView();
        bindEvent();
        initData();
    }

    private void bindEvent() {
        mTVRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimator();
            }
        });
    }

    private void initView() {
        mTVRest = findViewById(R.id.tv_reset);
        mBallImageView = findViewById(R.id.iv_circle);
    }

    private void initData() {
        startAnimator();
    }

    /**
     * 自定义Object Animator属性动画
     */
    private void startAnimator() {
        ObjectAnimator fallingPos = ObjectAnimator.ofObject(mBallImageView, "fallingPos", new FullingBallEvaluator(), new Point(0, 0), new Point(500, 500));
        fallingPos.setDuration(2000);
        fallingPos.start();
    }
}

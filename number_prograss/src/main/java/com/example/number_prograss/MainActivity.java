package com.example.number_prograss;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.number_prograss.lisenter.OnProgressBarListener;

public class MainActivity extends AppCompatActivity {
    private LineProgress mLineProgress;
    private Button mBtnLaunchAnimation;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindEvent();
    }

    private void bindEvent() {
        mBtnLaunchAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAnimation();
            }
        });

        mLineProgress.setOnProgressBarListener(new OnProgressBarListener() {
            @Override
            public void onProgressBarListener(int current) {
                if (current == 100) {
                    Toast.makeText(MainActivity.this, "Progress Finish!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        mLineProgress = findViewById(R.id.line_progress);
        mBtnLaunchAnimation = findViewById(R.id.btn_launch);
    }

    private void launchAnimation() {
        mLineProgress.setCurrent(0);
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }

        if (animator == null) {
            animator = ValueAnimator.ofInt(1, 100);
            animator.setDuration(4000);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int progress = (int) animation.getAnimatedValue();
                    mLineProgress.setCurrent(progress);
                }
            });
        }
        animator.start();
    }
}

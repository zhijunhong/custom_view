package com.example.basic_animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                       //执行动画
                initAnimation(mTvText);
            }
        });
    }

    /**
     * 组合播放动画
     *
     * @param view
     */
    private void initAnimation(View view) {
        AlphaAnimation alphaAnimation = buildAlphaAnimation();
        RotateAnimation rotateAnimation = buildRotateAnimation();
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(alphaAnimation);
        set.addAnimation(rotateAnimation);

        view.startAnimation(set);
    }

    /**
     * 旋转动画
     */
    private RotateAnimation buildRotateAnimation() {
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        return animation;
    }

    /**
     * 透明度动画
     */
    private AlphaAnimation buildAlphaAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 1.0f);
        animation.setDuration(2000);
        return animation;
    }

    private void initView() {
        mTvText = findViewById(R.id.tv_text);
    }
}

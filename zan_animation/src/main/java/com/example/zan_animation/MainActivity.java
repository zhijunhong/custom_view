package com.example.zan_animation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.zan_animation.IGoodView.*;

public class MainActivity extends AppCompatActivity {
    private int mFromY = FROM_Y_DELTA;

    private int mToY = TO_Y_DELTA;

    private float mFromAlpha = FROM_ALPHA;

    private float mToAlpha = TO_ALPHA;

    private int mDuration = DURATION;

    private ImageView mIvAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIvAnimation = findViewById(R.id.iv_animation);
        mIvAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvAnimation.startAnimation(createAnimation());
            }
        });
    }

    /**
     * 动画
     *
     * @return
     */
    private AnimationSet createAnimation() {
        AnimationSet mAnimationSet = new AnimationSet(true);
        TranslateAnimation translateAnim = new TranslateAnimation(0, 0, mFromY, -mToY);
        AlphaAnimation alphaAnim = new AlphaAnimation(mFromAlpha, mToAlpha);
        mAnimationSet.addAnimation(translateAnim);
        mAnimationSet.addAnimation(alphaAnim);
        mAnimationSet.setDuration(mDuration);
        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return mAnimationSet;
    }
}

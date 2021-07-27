package com.example.loading_imageview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * author: zhijunhong
 * created on: 2021/7/27 8:22 PM
 * version: 1.0
 * description:
 */
public class LoadingImageView extends ImageView {
    private int mCurrentImgIndex = 0;
    private int mImgCount = 3;
    private int mTop;

    public LoadingImageView(Context context) {
        this(context, null);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 100, 0);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(2000);
        animator.addUpdateListener(animation -> {
            Integer dx = (Integer) animation.getAnimatedValue();
            setTop(mTop - dx);
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setImageDrawable(getResources().getDrawable(R.mipmap.images001));
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mCurrentImgIndex ++;
                switch (mCurrentImgIndex % mImgCount) {
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.mipmap.images001));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.mipmap.images002));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.mipmap.images003));
                        break;
                }
            }
        });

        animator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }
}

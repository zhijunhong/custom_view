package com.example.view_group.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * author: zhijunhong
 * created on: 2021/8/27 2:47 PM
 * version: 1.0
 * description:
 */
public class DrawMenu extends ViewGroup implements View.OnClickListener {
    private boolean mIsChanged = true;

    public DrawMenu(Context context) {
        this(context, null);
    }

    public DrawMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutFirstView();

        if (mIsChanged) {
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();

                child.layout(0, getMeasuredHeight() - childHeight * (i + 1) *  2, childWidth, getMeasuredHeight());

                child.setVisibility(GONE);
            }
        }
    }

    private void layoutFirstView() {
        View firstChild = getChildAt(0);
        firstChild.setOnClickListener(this);
        int firstChildMeasuredWidth = firstChild.getMeasuredWidth();
        int firstChildMeasuredHeight = firstChild.getMeasuredHeight();
        int viewX = 0;
        int viewY = getMeasuredHeight() - firstChildMeasuredHeight;
        firstChild.layout(viewX, viewY, firstChildMeasuredWidth, getMeasuredHeight());
    }

    @Override
    public void onClick(View v) {
        toggleMenu();
    }

    private void toggleMenu() {
        int count = getChildCount();

        if (mIsChanged) {
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                TranslateAnimation to = new TranslateAnimation(-child.getMeasuredWidth(), 0, 0, 0);
                to.setDuration(100 + i * 100);
                child.setAnimation(to);
                child.setVisibility(VISIBLE);
                mIsChanged = false;
            }
        } else {
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                TranslateAnimation to = new TranslateAnimation(0, -child.getMeasuredWidth(), 0, 0);
                to.setDuration(100 + i * 100);
                child.setAnimation(to);
                child.setVisibility(GONE);
                mIsChanged = true;
            }
        }
    }
}

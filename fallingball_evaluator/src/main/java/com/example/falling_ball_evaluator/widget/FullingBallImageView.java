package com.example.falling_ball_evaluator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.falling_ball_evaluator.entity.Point;

/**
 * author: zhijunhong
 * created on: 2021/8/2 3:04 PM
 * version: 1.0
 * description:
 */
public class FullingBallImageView extends ImageView {
    public FullingBallImageView(Context context) {
        this(context, null);
    }

    public FullingBallImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FullingBallImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFullingPos(Point pos) {
        layout(pos.getX(), pos.getY(), pos.getX() + getWidth(), pos.getY() + getHeight());
    }
}

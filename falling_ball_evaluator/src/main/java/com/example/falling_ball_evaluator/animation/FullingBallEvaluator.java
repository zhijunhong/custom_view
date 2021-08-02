package com.example.falling_ball_evaluator.animation;

import android.animation.TypeEvaluator;

import com.example.falling_ball_evaluator.entity.Point;

/**
 * author: zhijunhong
 * created on: 2021/7/28 3:43 PM
 * version: 1.0
 * description:
 */
public class FullingBallEvaluator implements TypeEvaluator<Point> {
    Point point = new Point();

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        point.setX((int) (startValue.getX() + fraction * (endValue.getX() - startValue.getX())));

        if (fraction * 2 <= 1) {
            point.setY((int) (startValue.getY() + fraction * 2 * (endValue.getY() - startValue.getY())));
        } else {
            point.setY(endValue.getY());
        }

        return point;
    }
}

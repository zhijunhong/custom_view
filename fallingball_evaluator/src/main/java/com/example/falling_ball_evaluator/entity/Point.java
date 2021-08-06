package com.example.falling_ball_evaluator.entity;

/**
 * author: zhijunhong
 * created on: 2021/7/28 3:44 PM
 * version: 1.0
 * description:
 */
public class Point {
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

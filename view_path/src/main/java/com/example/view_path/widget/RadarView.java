package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: zhijunhong
 * created on: 2021/6/18 6:14 PM
 * <com.example.view_path.widget.Radar
 * version: 1.0
 * description:
 */
public class RadarView extends View {
    private int count = 6;
    private int centerX;
    private int centerY;
    private float radius;
    private float angle = (float) (Math.PI * 2 / count);
    private Paint mMainPaint;
    private Paint mValuePaint;
    private Paint mTextPaint;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float maxValue = 100;             //数据最大值

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mMainPaint = new Paint();
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setColor(Color.GREEN);
        mMainPaint.setStrokeWidth(3);
        mMainPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setStrokeWidth(14);
        mTextPaint.setAntiAlias(true);

        mValuePaint = new Paint();
        mValuePaint.setColor(Color.BLUE);
        mValuePaint.setStrokeWidth(14);
        mValuePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        mValuePaint.setAlpha(255);

        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 10, mValuePaint);
        }
        path.close();
        mValuePaint.setAlpha(127);
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, mValuePaint);

    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(i * angle));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(i * angle));
            if (angle * i >= 0 && angle * i < Math.PI / 2) {
                canvas.drawText(titles[i], x, y, mTextPaint);
            } else if (angle * i > Math.PI / 2 && angle * i < Math.PI) {
                float dis = mTextPaint.measureText(titles[i]);                                      //文本长度
                canvas.drawText(titles[i], x - dis, y, mTextPaint);
            } else if (angle * i > Math.PI && angle * i < Math.PI * 3 / 2) {
                float dis = mTextPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, mTextPaint);
            } else if (angle * i > Math.PI * 3 / 2 && angle * i < Math.PI * 2) {
                canvas.drawText(titles[i], x, y, mTextPaint);
            }
        }
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            //根据半径，计算出蜘蛛丝上每个点的坐标
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mMainPaint);
        }
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {//中心点不用绘制
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mMainPaint);
        }
    }
}

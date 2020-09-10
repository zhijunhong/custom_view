package com.example.number_prograss;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.number_prograss.lisenter.OnProgressBarListener;

public class LineProgress extends View {
    /**
     * 进度控制
     */
    private int mMax = 100;
    private int mCurrent;


    /**
     * 未到达进度
     */
    private int mUnreachedColor;
    private final int mDefaultUnreachedColor = Color.rgb(204, 204, 204);
    private float mUnReachedHeight;
    private final float mDefaultUnreachedBarHeight;
    private Paint mUnReachedPaint;
    private boolean mIsDrawUnReachedRectF = true;
    private RectF mUnReachedRectF = new RectF(0, 0, 0, 0);

    /**
     * 已到达进度
     */
    private int mResearchColor;
    private final int mDefaultReachedColor = Color.rgb(66, 145, 241);
    private float mResearchHeight;
    private final float mDefaultReachedBarHeight;
    private Paint mResearchPaint;
    private boolean mIsDrawReachedRectF = true;
    private RectF mReachedRectF = new RectF(0, 0, 0, 0);

    /**
     * 进度文本
     */
    private int mTextColor;
    private final int mDefaultTextColor = Color.rgb(66, 145, 241);
    private float mTextSize;
    private final float mDefaultTextSize;
    private float mOffset;
    private final float mDefaultProgressTextOffset;
    private Paint mTextPaint;
    private boolean mIsShowText = true;
    private static final int PROGRESS_TEXT_VISIBLE = 0;
    private String mCurrentDrawText;
    private String mPrefix = "%";
    private String mSuffix = "";
    private float mDrawTextStart;
    private float mDrawTextEnd;
    private float mDrawTextWidth;

    /**
     * 进度监听
     */
    private OnProgressBarListener mOnProgressBarListener;

    public LineProgress(Context context) {
        this(context, null);
    }

    public LineProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LineProgress, defStyleAttr, 0);

        mDefaultReachedBarHeight = dp2px(1.5f);
        mDefaultUnreachedBarHeight = dp2px(1.0f);
        mDefaultProgressTextOffset = dp2px(3.0f);
        mDefaultTextSize = sp2px(10);

        /**设置最大值和当前进度*/
        setMax(typedArray.getInt(R.styleable.LineProgress_progress_max, 100));
        setCurrent(typedArray.getInt(R.styleable.LineProgress_progress_current, 0));

        /**设置进度条属性*/
        mResearchColor = typedArray.getColor(R.styleable.LineProgress_progress_research_color, mDefaultReachedColor);
        mUnreachedColor = typedArray.getColor(R.styleable.LineProgress_progress_unreached_color, mDefaultUnreachedColor);
        mResearchHeight = typedArray.getDimension(R.styleable.LineProgress_progress_research_bar_height, mDefaultReachedBarHeight);
        mUnReachedHeight = typedArray.getDimension(R.styleable.LineProgress_progress_unreached_bar_height, mDefaultUnreachedBarHeight);

        /**设置文字属性*/
        int textVisibility = typedArray.getInt(R.styleable.LineProgress_progress_text_visibility, PROGRESS_TEXT_VISIBLE);
        if (textVisibility != PROGRESS_TEXT_VISIBLE) {
            mIsShowText = false;
        }
        mTextColor = typedArray.getColor(R.styleable.LineProgress_progress_text_color, mDefaultTextColor);
        mTextSize = typedArray.getDimension(R.styleable.LineProgress_progress_text_size, mDefaultTextSize);
        mOffset = typedArray.getDimension(R.styleable.LineProgress_progress_text_offset, mDefaultProgressTextOffset);

        typedArray.recycle();
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mResearchPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mResearchPaint.setColor(mResearchColor);

        mUnReachedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnReachedPaint.setColor(mUnreachedColor);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = isWidth ? getMinimumWidth() : getMinimumHeight();
            result += padding;

            if (specMode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, specSize);
                } else {
                    result = Math.min(result, specSize);
                }
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mIsShowText) {
            drawRectFWithText();
        } else {
            drawRectFWithoutText();
        }

        if (mIsDrawReachedRectF) {
            canvas.drawRect(mReachedRectF, mResearchPaint);
        }

        if (mIsDrawUnReachedRectF) {
            canvas.drawRect(mUnReachedRectF, mUnReachedPaint);
        }

        if (mIsShowText) {
            canvas.drawText(mCurrentDrawText, mDrawTextStart, mDrawTextEnd, mTextPaint);
        }
    }

    /**
     * 不显示文本
     */
    private void drawRectFWithoutText() {
        mReachedRectF.left = getPaddingLeft();
        mReachedRectF.top = getHeight() / 2.0f - mResearchHeight / 2.0f;
        mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getMax() * 1.0f) * getCurrent() + getPaddingLeft();
        mReachedRectF.bottom = getHeight() / 2.0f + mResearchHeight / 2.0f;
        mUnReachedRectF.left = mReachedRectF.right;

        mUnReachedRectF.left = getPaddingLeft();
        mUnReachedRectF.top = getHeight() / 2.0f - mUnReachedHeight / 2.0f;
        mUnReachedRectF.right = getWidth() - getPaddingRight();
        mUnReachedRectF.bottom = getHeight() / 2.0f + mUnReachedHeight / 2.0f;
    }

    /**
     * 显示文本
     */
    private void drawRectFWithText() {
        mCurrentDrawText = String.format("%d", getCurrent());
        mCurrentDrawText = mPrefix + mCurrentDrawText + mSuffix;
        mDrawTextWidth = mTextPaint.measureText(mCurrentDrawText);

        if (getCurrent() == 0) {
            mIsDrawReachedRectF = false;                                                            //进度为0,不需要画已到达部分
            mDrawTextStart = getPaddingLeft();
        } else {
            mIsDrawReachedRectF = true;
            mReachedRectF.left = getPaddingLeft();                                                  //已到达部分其实位置为getPaddingLeft()
            mReachedRectF.top = getHeight() / 2.0f - mResearchHeight / 2.0f;                        //上部分为空间宽度的一半在向上平移已到达部分宽度的一半
            mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getMax() * 1.0f) * getCurrent() - mOffset + getPaddingLeft();      //右侧为进度条的长度加上左边距后取出文本额偏移量
            mReachedRectF.bottom = getHeight() / 2.0f + mResearchHeight / 2.0f;                     //下部为控件宽度的一半加上已到达部分宽度的一半

            mDrawTextStart = mReachedRectF.right + mOffset;
        }

        mDrawTextEnd = (int) (getHeight() / 2.0f - (mTextPaint.ascent() + mTextPaint.descent()) / 2.0f);            //字体居中

        if (mDrawTextStart + mDrawTextWidth >= getWidth() - getPaddingRight()) {                                    //如果字体到达最右侧
            mDrawTextStart = getWidth() - getPaddingRight() - mDrawTextWidth;
            mReachedRectF.right = mDrawTextStart - mOffset;
        }

        float unReachedBarStart = mDrawTextStart + mDrawTextWidth + mOffset;                                        //未到达部分的起始位置

        if (unReachedBarStart >= getWidth() - getPaddingRight()) {                                                  //如果进度条已经完成，不用再画未达到部分
            mIsDrawUnReachedRectF = false;
        } else {
            mIsDrawUnReachedRectF = true;

            mUnReachedRectF.left = unReachedBarStart;
            mUnReachedRectF.top = getHeight() / 2.0f - mUnReachedHeight / 2.0f;
            mUnReachedRectF.right = getWidth() - getPaddingRight();
            mUnReachedRectF.bottom = getHeight() / 2.0f + mUnReachedHeight / 2.0f;

        }
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public void setCurrent(int current) {
        if (current <= getMax() && current > 0) {
            mCurrent = current;

            if (mOnProgressBarListener != null) {
                mOnProgressBarListener.onProgressBarListener(current);
            }

            invalidate();
        }
    }

    public int getCurrent() {
        return mCurrent;
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public void setOnProgressBarListener(OnProgressBarListener onProgressBarListener) {
        mOnProgressBarListener = onProgressBarListener;
    }
}

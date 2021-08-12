package com.example.view_path.widget;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.view_path.R;


/**
 * Created by zengyazhi on 17/3/31.
 * <p>
 * 波浪效果进度条
 */

public class SinWave extends View {

    private Paint mPaint;
    private Path mPath;
    private int mHeight;
    private int mWidth;
    private static final int X_OFFSET = 15;
    //坡度值，越小曲线越平缓
    private double mGrade = 0.2;
    //开启动画时x方向的偏移
    float mXOffset;
    //开启动画时y方向的偏移
    float mYOffset;
    private Context mContext;
    private Bitmap mBitmap;
    private AnimatorSet mAnimatorSet;
    //波浪高度
    private int mWaveHeight;

    public SinWave(Context context) {
        this(context, null);
    }

    public SinWave(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SinWave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //关闭GPU硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.my_color));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mPath = new Path();

        mContext = context;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setXfermode(null);
        if (mBitmap != null) {
            //调低背景图片高度，以防进度=0或进度=100时波浪够不到图片，或进度满了波浪没有充满图片
            canvas.drawBitmap(mBitmap, mWaveHeight, 2 * mWaveHeight, mPaint);
        }

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)); //只截取前景里和背景重合的部分
        canvas.drawPath(getSinPath(), mPaint);
    }

    /**
     * 获得正弦函数的path
     */
    private Path getSinPath() {
        mPath.reset();
        //注意，view的原点（0,0）是左上角，向右x轴正方向，向下y轴正方向
        mPath.moveTo(0, mHeight);
        for (float x = 0; x <= mWidth; x += X_OFFSET) {
            float y = (float) ((Math.sin(degreeToRad(mGrade * x + mXOffset)) + 1) * mWaveHeight);
            mPath.lineTo(x, y + mYOffset);
        }
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();
        return mPath;
    }

    /**
     * 启动动画
     */
    public void start() {
        mAnimatorSet.start();
    }

    /**
     * 角度转弧度
     *
     * @param degree
     * @return
     */
    private double degreeToRad(double degree) {
        return degree * Math.PI / 180;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mWaveHeight = mHeight / 10;
        mYOffset = mHeight - mWaveHeight;

        mBitmap = getBitmap(mContext, mHeight - 2 * mWaveHeight, mHeight - 2 * mWaveHeight, R.mipmap.mmexport);

        initAnim();
    }

    private void initAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mXOffset = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(mYOffset, 0);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mYOffset = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(valueAnimator, valueAnimator2);
        mAnimatorSet.setDuration(10000);
        mAnimatorSet.setInterpolator(new LinearInterpolator());
    }

    /**
     * 根据drawble文件名得到bitmap
     *
     * @param context 用来获得Resources
     * @param width   想要获得的bitmap的宽
     * @param height  想要获得的bitmap的高
     * @param drawble drawble图片名称
     * @return
     */
    public static Bitmap getBitmap(Context context, int width, int height, int drawble) {
        // 得到图像
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, drawble);

        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();

        float scaleX = (float) width / bWidth;
        float scaleY = (float) height / bHeight;

        float scale = Math.max(scaleX, scaleY) / 2;

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);

        return Bitmap.createBitmap(bitmap, 0, 0, bWidth, bHeight, matrix, true);
    }


}

package com.example.falling_ball_evaluator

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * 小球抛物动画
 * 自定义Evaluator转换器将进度值转化为最终的value animator值
 */
class MainActivity : AppCompatActivity() {
    private var mCurPoint: Point? = null
    private var mIvCircle: ImageView? = null
    private var mTvReset :TextView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        bindEvent()
        initData()
    }

    private fun initData() {
        startAnimator()
    }

    private fun startAnimator() {
        val animator = ValueAnimator.ofObject(FullingBallEvaluator(), Point(0, 0), Point(500, 500))
        animator.addUpdateListener { animation: ValueAnimator ->
            mCurPoint = animation.animatedValue as Point
            mIvCircle!!.layout(mCurPoint!!.x, mCurPoint!!.y, mCurPoint!!.x + mIvCircle!!.width, mCurPoint!!.y + mIvCircle!!.height)
        }
        animator.duration = 2000

        animator.start()
    }

    private fun bindEvent() {
        mTvReset!!.setOnClickListener {
            startAnimator()
        }
    }

    private fun initView() {
        mIvCircle = findViewById(R.id.iv_circle)
        mTvReset = findViewById(R.id.tv_reset)
    }
}
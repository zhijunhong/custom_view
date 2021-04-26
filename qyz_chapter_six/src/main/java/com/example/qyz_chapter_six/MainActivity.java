package com.example.qyz_chapter_six;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 双缓冲技术是游戏开发中的一个重要的技术。
 * 当一个动画争先显示时，程序又在改变它，前面还没有显示完，程序又请求重新绘制，这样屏幕就会不停地闪烁。
 * 而双缓冲技术是把要处理的图片在内存中处理好之后，再将其显示在屏幕上。
 * 双缓冲主要是为了解决 反复局部刷屏带来的闪烁。
 * 把要画的东西先画到一个内存区域里，然后整体的一次性画出来。
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

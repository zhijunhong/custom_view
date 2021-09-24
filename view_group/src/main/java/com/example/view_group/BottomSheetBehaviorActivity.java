package com.example.view_group;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.view_group.widget.BottomSheetBehavior;

/**
 * author: zhijunhong
 * created on: 2021/9/8 5:40 PM
 * version: 1.0
 * description:
 */
public class BottomSheetBehaviorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);

        LinearLayout bottom_sheet = findViewById(R.id.bottom_sheet);
        BottomSheetBehavior<LinearLayout> behavior = BottomSheetBehavior.from(bottom_sheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState, int finalTop) {
                if (newState == 2) {
                    int mHeight = bottomSheet.getHeight() - behavior.getPeekHeight();
                    Log.e("finalTop", "===" + finalTop);
                    if (finalTop <= mHeight && finalTop > mHeight / 4 * 3) {
                        // 最底层
                        Log.e("最底层", "最底层");
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    } else if (finalTop <= mHeight / 4 * 3 && finalTop > mHeight / 2) {
                        Log.e("2分之1层", "2分之1层");
                        behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                    } else if (finalTop <= mHeight / 2 && finalTop > mHeight / 4) {
                        Log.e("3分之2层", "3分之2层");
                        behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                    } else if (finalTop <= mHeight / 4) {
                        Log.e("最上层", "最上层");
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
                switch (newState) {
                    case 1:
                        //过渡状态此时用户正在向上或者向下拖动bottom sheet
                        Log.e("state", "====用户正在向上或者向下拖动");
                        break;
                    case 2:
                        // 视图从脱离手指自由滑动到最终停下的这一小段时间
                        Log.e("state", "====视图从脱离手指自由滑动到最终停下的这一小段时间");
                        break;
                    case 3:
                        //处于完全展开的状态
                        Log.e("state", "====处于完全展开的状态");
                        break;
                    case 4:
                        //默认的折叠状态
                        Log.e("state", "====默认的折叠状态");
                        break;
                    case 5:
                        //下滑动完全隐藏 bottom sheet
                        Log.e("state", "====下滑动完全隐藏");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e("slideOffset", "===" + slideOffset);
            }
        });


    }
}
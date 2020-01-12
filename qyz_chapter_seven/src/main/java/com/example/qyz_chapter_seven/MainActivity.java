package com.example.qyz_chapter_seven;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvView = findViewById(R.id.tv_view);
        mTvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mTvView, "translationX", 300);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTvView, "translationY", 300);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator, animator2);
                animatorSet.setDuration(300);
                animatorSet.start();
            }
        });
    }
}

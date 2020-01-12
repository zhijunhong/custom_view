package com.example.qyz_chapter_seven.valuesholder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qyz_chapter_seven.R;

public class ProValuesHolderActivity extends AppCompatActivity {
    private TextView mTvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_holder);

        mTvView = findViewById(R.id.tv_view);
        mTvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300);
                PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
                PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);

                ObjectAnimator.ofPropertyValuesHolder(mTvView, pvh1, pvh2, pvh3).setDuration(1000).start();


            }
        });
    }
}
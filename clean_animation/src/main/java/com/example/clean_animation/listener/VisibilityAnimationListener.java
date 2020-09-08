package com.example.clean_animation.listener;

import android.view.View;
import android.view.animation.Animation;

public class VisibilityAnimationListener implements Animation.AnimationListener {
    private View mVisibilityView;

    public VisibilityAnimationListener(View mVisibilityView) {
        this.mVisibilityView = mVisibilityView;
    }

    public void setmVisibilityView(View mVisibilityView) {
        this.mVisibilityView = mVisibilityView;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if(mVisibilityView!=null)
            mVisibilityView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(mVisibilityView!=null)
            mVisibilityView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

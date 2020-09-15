package com.example.sticky_title;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ScrollViewWrap.OnScrollListener {
    /**
     * 顶部固定的TabViewLayout
     */
    private LinearLayout mTopTabViewLayout;
    /**
     * 跟随ScrollView的TabviewLayout
     */
    private LinearLayout mTabViewLayout;

    /**
     * 要悬浮在顶部的View的子View
     */
    private LinearLayout mTopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollViewWrap mScrollViewWrap = (ScrollViewWrap) findViewById(R.id.my_scrollview);
        mTabViewLayout = (LinearLayout) findViewById(R.id.ll_tabView);
        mTopTabViewLayout = (LinearLayout) findViewById(R.id.ll_tabTopView);
        mTopView = (LinearLayout) findViewById(R.id.tv_topView);
        //滑动监听
        mScrollViewWrap.setOnScrollListener(this);
    }

    @Override
    public void onScroll(int scrollY) {
        int mHeight = mTabViewLayout.getTop();
        Log.i("dahua", "scrollY: " + scrollY + " mHeight: " + mHeight + " delta: " + (scrollY - mHeight));

        //判断滑动距离scrollY是否大于0，因为大于0的时候就是可以滑动了，此时mTabViewLayout.getTop()才能取到值。
        if (scrollY > 0 && scrollY >= mHeight) {
            if (mTopView.getParent() != mTopTabViewLayout) {
                mTabViewLayout.removeView(mTopView);
                mTopTabViewLayout.addView(mTopView);
            }

        } else {
            if (mTopView.getParent() != mTabViewLayout) {
                mTopTabViewLayout.removeView(mTopView);
                mTabViewLayout.addView(mTopView);
            }
        }
    }

}

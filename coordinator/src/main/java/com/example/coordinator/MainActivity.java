package com.example.coordinator;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import com.google.android.material.appbar.CollapsingToolbarLayout;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.nav_icon_back_normal);

        //Palette用来更漂亮地展示配色
        Palette.from(BitmapFactory.decodeResource(getResources(), R.mipmap.img_test))
                .generate(palette -> {
                    int color = palette.getVibrantColor(getResources().getColor(R.color.white));
                    collapsingToolbarLayout.setContentScrimColor(color);

                    //因为我暂时没有找到比较好的透明状态栏来适配这一套效果布局。
                    //因此就直接替换掉StatusBar的颜色，这样其实也蛮好看的。
                    getWindow().setStatusBarColor(color);
                });

        //CollapsingToolbarLayout
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle("我是一个标题啊");
    }
}
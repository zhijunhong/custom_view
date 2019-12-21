package fudaojun.com.view_shake;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int index = 0;
    private TextView tv_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_textview = findViewById(R.id.tv_textview);

        tv_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index % 2 == 0) { //如果为偶数
                    AnimationUtils.tada(tv_textview).start(); //上下抖动
                } else {
                    AnimationUtils.nope(tv_textview).start(); //左右抖动
                }

                index++;
            }
        });

    }
}

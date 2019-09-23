package fudaojun.com.common_api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTimeTaskActivity extends Activity {
    private TextView mTvTextIndex;

    private Handler mHandler;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTextIndex = findViewById(R.id.tv_text);

        initHandler();

        initTimeAndTimeTask();

    }

    /**
     * 初始化Handler
     */
    @SuppressLint("HandlerLeak")
    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mTvTextIndex.setText(String.valueOf(msg.what));
            }
        };
    }

    /**
     * 初始化
     */
    private void initTimeAndTimeTask() {
        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(index++);
            }
        };

        //更多用户请参考：https://batitan.iteye.com/blog/253483
        time.schedule(timerTask, 5000, 1000);
    }
}

package fudaojun.com.common_api.workmanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fudaojun.com.common_api.R;

public class WorkManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testWorkManagerWithConstraint();
    }

    private void testWorkManagerWithConstraint() {
        //约束条件。
//        Constraints constraints = new Constraints.Builder()
//                .setRequiresBatteryNotLow(true)
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build();
    }
}

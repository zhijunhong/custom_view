package fudaojun.com.custom_view;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加权限注解
        openFile();
    }

    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    private void openFile() {

    }
}

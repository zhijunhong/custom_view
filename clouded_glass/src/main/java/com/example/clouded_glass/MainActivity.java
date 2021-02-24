package com.example.clouded_glass;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加权限注解
                openContacts();
            }
        });

        findViewById(R.id.tv_clouded).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CloudedGlassActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,})
    private void openContacts() {

        ToastUtil.makeText(MainActivity.this, "打开联系人", ToastUtil.LENGTH_LONG).show();       //自定义Toast

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_TYPE);
        startActivityForResult(intent, 1);
    }
}

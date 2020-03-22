package fudaojun.com.common_api.injustdecodebounds;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.InputStream;

import fudaojun.com.common_api.R;


/**
 * 加载大图
 */
public class InJustDecodeBoundsActivity extends Activity {
    private ImageView iv;
    private WindowManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injust_decode);
        wm = getWindowManager();
        iv = findViewById(R.id.iv_big_pic);
        findViewById(R.id.tv_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(iv);
            }
        });
    }

    // 从系统的图库里面 获取一张照片
    public void click(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            // 获取到系统图库返回回来图片的uri
            Uri uri = data.getData();
            System.out.println(uri.toString());

            try {
                InputStream is = getContentResolver().openInputStream(uri);
                // 1.计算出来屏幕的宽高.
                int windowWidth = wm.getDefaultDisplay().getWidth();
                int windowHeight = wm.getDefaultDisplay().getHeight();
                //2. 计算图片的宽高.
                BitmapFactory.Options opts = new BitmapFactory.Options();
                // 设置 不去真正的解析位图 不把他加载到内存 只是获取这个图片的宽高信息
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, opts);
                int bitmapHeight = opts.outHeight;
                int bitmapWidth = opts.outWidth;

                if (bitmapHeight > windowHeight || bitmapWidth > windowWidth) {
                    int scaleX = bitmapWidth / windowWidth;
                    int scaleY = bitmapHeight / windowHeight;
                    if (scaleX > scaleY) {//按照水平方向的比例缩放
                        opts.inSampleSize = scaleX;
                    } else {//按照竖直方向的比例缩放
                        opts.inSampleSize = scaleY;
                    }

                } else {//如果图片比手机屏幕小 不去缩放了.
                    opts.inSampleSize = 1;
                }
                //让位图工厂真正的去解析图片
                opts.inJustDecodeBounds = false;
                //注意: 流的操作
                is = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, opts);
                iv.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
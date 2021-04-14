package com.example.coroutine

import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

/**
 * author: zhijunhong
 * created on: 2021/4/14 11:49 AM
 * version: 1.0
 * description: kotlin协程用法demo
 * 参考链接：https://kaixue.io/kotlin-coroutines-1/
 */
class CoroutineActivity : AppCompatActivity() {
    companion object {
        const val TAG = "CoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = getImageFromNetWork()
            val bitmapWithWatermark = createWatermark(bitmap, "zhijunhong")
            findViewById<ImageView>(R.id.iv_image).setImageBitmap(bitmapWithWatermark)
        }
    }

    private suspend fun createWatermark(bitmap: Bitmap, mark: String) = withContext(Dispatchers.IO) witchContext@{
        val w = bitmap.width
        val h = bitmap.height
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val paint = Paint()

        //水印颜色
        paint.color = Color.parseColor("#A5FF0000")
        //水印字体大小
        paint.textSize = 40F
        //抗锯齿
        paint.isAntiAlias = true
        //绘制图像
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        //绘制文字
        canvas.drawText(mark, 0f, (h-10).toFloat(), paint)
        canvas.save()
        canvas.restore()
        return@witchContext bmp
    }

    private suspend fun getImageFromNetWork() = withContext(Dispatchers.IO) {
        val url = URL("https://avatars.githubusercontent.com/u/10175921?v=4")
        val connection = url.openConnection() as HttpURLConnection
        var inputStream = connection.inputStream
        BitmapFactory.decodeStream(inputStream)
    }
}
package com.mingrisoft.androidnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

public class PicLoadActivity extends AppCompatActivity {

    private static final String TAG = "PicLoadActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_load);
    }

    public void loadPic(View view) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置为true以后呢，不是真的载入到内存中，只是获取到图片的相关信息
        options.inJustDecodeBounds = true;
        //拿到图片的大小
        BitmapFactory.decodeResource(getResources(), R.drawable.knight,options);
        imageView = findViewById(R.id.my_picture);
//        int outHeight = options.outHeight;
//        int outWidth = options.outWidth;
//        Log.d(TAG, "outHeight---->" + outHeight);
//        Log.d(TAG, "outWidth---->" + outWidth);
        //拿到控件的尺寸
        int measuredHeight = imageView.getMeasuredHeight();
        int measuredWidth = imageView.getMeasuredWidth();
        Log.d(TAG, "measuredHeight---->" + measuredHeight);
        Log.d(TAG, "measuredWidth---->" + measuredWidth);
        options.inSampleSize = calculateInSampleSize(options,measuredWidth,measuredHeight);
        //需要根据控件的大小，动态计算SampleSize
        //inSampleSize默认为1，控件高和宽都 大于 图片高和宽
        //options.inSampleSize = 1;
        //图片宽度/控件宽度
        //图片高度/控件宽度
        //取两者最大值
//        if (outHeight > measuredHeight || outWidth > measuredWidth) {
//            int subHeight = outHeight / measuredHeight;
//            int subWidth = outWidth / measuredWidth;
//            options.inSampleSize = subHeight > subWidth ? subHeight : subWidth;
//        }
        //要变成false了，因为真的要载入到内存中了
        options.inJustDecodeBounds = false;
        Log.d(TAG,"options.inSampleSize---->"+options.inSampleSize);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.knight, options);
        imageView.setImageBitmap(bitmap);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//            无法加载大图：
//                try {
//                    URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605967416797&di=8bba4675557aa3358c0eb7afa34d59e5&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0829%2F372edfeb74c3119b666237bd4af92be5.jpg");
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//                    connection.setConnectTimeout(1000);
//                    connection.setRequestMethod("GET");//八种常用方法get put.....
//                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");//请求头设置
//                    connection.setRequestProperty("Accept", "*/*");
//                    connection.connect();
//
//                    int responseCode = connection.getResponseCode();//拿到请求码
//                    if(responseCode==200){
//                        InputStream inputStream = connection.getInputStream();
//                        //转成位图BitMap
//                      final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        //更新UI，要在主线程
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                ImageView Image = findViewById(R.id.my_picture);
//                                Image.setImageBitmap(bitmap);
//                            }
//                        });
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize =3;
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.knight,options);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ImageView imageView = findViewById(R.id.my_picture);
//                        imageView.setImageBitmap(bitmap);
//                    }
//                });
//
//
//            }
//        }).start();
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {

        //这里其实是获取到默认的高度和宽度，也就是图片的实际高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;

        //默认采样率为1，也就是不变嘛。
        int inSampleSize = 1;


        //===============核心算法啦====================
        if (width > maxWidth || height > maxHeight) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) maxHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) maxWidth);
            }

            final float totalPixels = width * height;

            final float maxTotalPixels = maxWidth * maxHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > maxTotalPixels) {
                inSampleSize++;
            }
        }
        //=============核心算法end================
        return inSampleSize;
    }
}
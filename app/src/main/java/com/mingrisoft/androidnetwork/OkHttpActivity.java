package com.mingrisoft.androidnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = "OkHttpActivity";
    private final String BASE_URL = "http://10.0.2.2:9102";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }

    public void getRequest(View view) {
        //要有客户端，类似浏览器
        OkHttpClient okhttpclient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MICROSECONDS)
                .build();
        //创建请求内容
        final Request request = new Request.Builder().get()
                .url(BASE_URL+"/get/text")
                .build();
        //用浏览器client去创建任务
        Call task = okhttpclient.newCall(request);
        //异步请求，执行任务
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"onFail------->"+e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK){
                    ResponseBody body = response.body();
                    Log.d(TAG,"body------>"+body.string());
                }

            }
        });

    }
}
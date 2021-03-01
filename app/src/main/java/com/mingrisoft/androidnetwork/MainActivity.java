package com.mingrisoft.androidnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        /*RecyclerView recyclerView = findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ResultAdapter resultAdapter;
        resultAdapter = new ResultAdapter();
        recyclerView.setAdapter(resultAdapter);*/
    }

    //第4课：使用java的API进行数据请求
    public void loadJson(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   // URL url = new URL("https://www.sunofbeach.net");
                    URL url = new URL("http://10.0.2.2:9102/get/text");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开链接
                    connection.setRequestMethod("GET");//八种常用方法get put.....
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");//请求头设置
                    connection.setRequestProperty("Accept", "*/*");
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                        //entries.for
                        /*
                        打印头部信息，请求头
                         */
                        //for (Map.Entry<String, List<String>> entry : entries) {
                        //    Log.d(TAG, entry.getKey() + "==" + entry.getValue());
                        //}

                        //Object content = connection.getContent();
                        //Log.d(TAG, "content------>" + content); //输出的是一个buffer

                        //我们希望拿到的是一个字符串，所以要用流
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = bufferedReader.readLine();
                        Log.d(TAG, "Line------->" + line);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //第4课：使用java的API进行数据请求,到这截止

    /****
    跳转
     ****/
    public void toPicture(View view) {
        Intent intent = new Intent(MainActivity.this,PicLoadActivity.class);
        startActivity(intent);
    }

    public void postTest(View view) {
        Intent intent = new Intent(MainActivity.this,PostActivity.class);
        startActivity(intent);
    }

    public void okHttpRequest(View view) {
        Intent intent = new Intent(MainActivity.this,OkHttpActivity.class);
        startActivity(intent);
    }
}
package com.mingrisoft.androidnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PostActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void postRequest(View view) {
        String s = "666";
        new Thread(new Runnable() {
            private final String TAG = "PostActivity";

            @Override
            public void run() {
                InputStream inputStream=null;
                OutputStream outputStream=null;
                try {
                    URL url = new URL("https://www.sunofbeach.net/qa/1329787542995816448");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    httpURLConnection.setRequestProperty("Accept", "application/json, text/plain, */*");

                    byte[] bytes = s.getBytes("UTF-8");

                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bytes.length));

                    httpURLConnection.connect();
                    //把数据给到服务器

                    outputStream = httpURLConnection.getOutputStream();

                    outputStream.write(bytes);
                    outputStream.flush();
                    //拿结果
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {

                        inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        Log.d(TAG, "result---->" + bufferedReader.readLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(outputStream!=null){
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void downLoad(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
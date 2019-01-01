package com.example.mvp_newsdata.data.okhttputil;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    private static OkHttpUtil okHttpUtil;
    private final OkHttpClient okHttpClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    // 单例模式
    //私有化构造函数
    private OkHttpUtil(){
        //创建OKHTTP对象
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static OkHttpUtil getInstance(){
        //DCL 模式下的懒汉试
        if (null == okHttpUtil){
            synchronized (OkHttpUtil.class){
                if (null == okHttpUtil){
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }

        return okHttpUtil;
    }

    public void getEnqueue(String url , final Class clazz , final ICallBack icallback){
        //所需的参数是URL的地址
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        icallback.setFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response)  {

                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    final Object responseBackData = gson.fromJson(result, clazz);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            icallback.setData(responseBackData);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

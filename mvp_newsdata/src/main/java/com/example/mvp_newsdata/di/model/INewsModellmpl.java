package com.example.mvp_newsdata.di.model;

import android.os.Handler;
import android.os.Looper;

import com.example.mvp_newsdata.data.apils.Apils;
import com.example.mvp_newsdata.data.okhttputil.ICallBack;
import com.example.mvp_newsdata.data.okhttputil.OkHttpUtil;
import com.example.mvp_newsdata.di.contract.INewsContract;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class INewsModellmpl implements INewsContract.INewsModel {

    @Override
    public void showNewsResponesData(String url, Class clazz, final NewsCallBack newsCallBack) {
        OkHttpUtil.getInstance().getEnqueue(url, clazz, new ICallBack() {
            @Override
            public void setData(Object o) {
                newsCallBack.setData(o);
            }

            @Override
            public void setFail(IOException msg) {
               // newsCallBack.setFail(msg.getMessage());
            }
        });
    }

   /* private Handler mHandler = new Handler(Looper.myLooper());

    @Override
    public void showNewsResponesData(String url, Class clazz, NewsCallBack newsCallBack) {
        requestNewsDataEnqueue(url,clazz,newsCallBack);
    }

    private void requestNewsDataEnqueue(String url, final Class clazz, final NewsCallBack newsCallBack) {
        //创建OKHttp对象
        OkHttpClient client = new OkHttpClient.Builder().build();
        //空的表单请求体
        FormBody formBody = new FormBody.Builder().build();
        final Request request = new Request.Builder()
                .method("GET",formBody)
                .url(Apils.NEWS_URL)
                .build();
        //开启请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                       String fail = e.getMessage();
                       newsCallBack.setFail(fail);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();

                    final Object o = gson.fromJson(result, clazz);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            newsCallBack.setData(o);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }*/
}

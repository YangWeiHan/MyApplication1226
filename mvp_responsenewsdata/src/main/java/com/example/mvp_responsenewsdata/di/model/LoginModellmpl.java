package com.example.mvp_responsenewsdata.di.model;

import com.example.mvp_responsenewsdata.data.apils.Apils;
import com.example.mvp_responsenewsdata.di.contract.ILoginContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModellmpl implements ILoginContract.ILoginModel {
    @Override
    public void containLoginResponesData(String userName,String password , MyCallBack myCallBack) {
        //调用方法
        //注意参数
        requestLoginDataEnqueue(userName,password,myCallBack);
    }

        //在这个方法中有一个 MyCallback  这个用在M层向P层回传数据
    private void requestLoginDataEnqueue(String userName, String password, final MyCallBack myCallBack){
        //创建OKHTTP
        OkHttpClient client = new OkHttpClient.Builder().build();
        //得到一个   空的表单请求体
        FormBody formBody = new FormBody.Builder().build();
        //获取Request 对象
        Request request = new Request.Builder()
                //请求方式
                .method("POST",formBody)
                //请求地址
                .url(Apils.LOGIN_URL + "?phone=" + userName + "&pwd=" + password)
                .build();
        //开启请求
        Call call = client.newCall(request);
        //异步请求	
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //错误数据
                String responesData = e.getMessage();
                //接口回调  回传数据
                myCallBack.responesData(responesData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //相应数据
                String responesData = response.body().string();
                //接口回调  回传数据
                myCallBack.responesData(responesData);

            }
        });
    }


}

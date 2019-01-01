package com.example.holiday_work.di.model;

import com.example.holiday_work.data.aplis.Aplis;
import com.example.holiday_work.di.contract.LoginContracl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ILoginModellmpl implements LoginContracl.LoginModel {

    @Override
    public void containLoginResponesData(String loginUserName, String loginPassword, MyCallBack myCallBack) {
        requestLoginDataEnqueue(loginUserName,loginPassword,myCallBack);
    }

    private void requestLoginDataEnqueue(String loginUserName, String loginPassword, final MyCallBack myCallBack) {
        //创建OKHTTP
        OkHttpClient client = new OkHttpClient.Builder().build();
        //得到一个   空的表单请求体
        FormBody formBody = new FormBody.Builder().build();
        //获取Request 对象
        Request request = new Request.Builder()
                //请求方式
                .method("POST",formBody)
                //请求地址
                .url(Aplis.LOGIN_URL + "?mobile=" + loginUserName + "&password=" + loginPassword)
                .build();
         Call call = client.newCall(request);

         call.enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 String responseData = e.getMessage();
                 myCallBack.requestLoginData(responseData);
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 String responseData = response.body().string();
                 myCallBack.requestLoginData(responseData);
             }
         });
    }
}

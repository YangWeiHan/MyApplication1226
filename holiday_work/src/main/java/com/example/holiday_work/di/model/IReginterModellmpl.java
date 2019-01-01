package com.example.holiday_work.di.model;

import com.example.holiday_work.data.aplis.Aplis;
import com.example.holiday_work.data.bean.RegisterBean;
import com.example.holiday_work.di.contract.RegistraContracl;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class IReginterModellmpl implements RegistraContracl.RegistraModel {

    @Override
    public void containRegistrResponesData(String registerUserName, String registerPassword, MyCallBack myCallBack) {
        requesRegistrDataEnqueue(registerUserName,registerPassword,myCallBack);
    }

    private void requesRegistrDataEnqueue(String registerUserName, String registerPassword, final MyCallBack myCallBack){

        OkHttpClient client = new OkHttpClient.Builder().build();

        FormBody formBody = new FormBody.Builder().build();
        final Request request = new Request.Builder()
                //请求方式
                .method("POST",formBody)
                //请求地址
                .url(Aplis.REGISTER_URL + "?mobile=" + registerUserName + "&password=" + registerPassword)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //错误数据
                String responesData = e.getMessage();
                //接口回调  回传数据
                myCallBack.requestRegistrData(responesData);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String responesData = response.body().string();
                myCallBack.requestRegistrData(responesData);
            }
        });
    }
}

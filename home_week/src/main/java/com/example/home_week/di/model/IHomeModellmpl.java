package com.example.home_week.di.model;

import android.util.Log;

import com.example.home_week.data.apils.Apils;
import com.example.home_week.data.bean.ShopBean;
import com.example.home_week.di.contract.IHomeContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IHomeModellmpl implements IHomeContract.IHomeModel {
    @Override
    public void containData(final MyCallBack myCallBack) {
        OkGo.<String>get(Apils.SHOPING_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //相应数据
                String requestData = response.body();
                Gson gson = new Gson();
                ShopBean responseData = gson.fromJson(requestData, ShopBean.class);
                Log.e("tag", "奥会计师大石街道"+requestData );
                myCallBack.onCallBack(responseData);
            }
        });
    }
}

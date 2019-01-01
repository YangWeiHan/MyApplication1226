package com.example.holiday_work.di.model;

import com.example.holiday_work.data.aplis.Aplis;
import com.example.holiday_work.data.bean.ShowBean;
import com.example.holiday_work.di.contract.ShowContracl;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShowModellmpl implements ShowContracl.IShopModl {
    @Override
    public void containShopRequestData(final MyCallBack myCallBack) {
        OkGo.<String>get(Aplis.SHOPDATA_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responesData = response.body().toString();
                Gson gson = new Gson();
                ShowBean showBean = gson.fromJson(responesData, ShowBean.class);
                myCallBack.responestData(showBean);
            }
        });
    }
}

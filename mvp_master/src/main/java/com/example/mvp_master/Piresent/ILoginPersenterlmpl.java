package com.example.mvp_master.Piresent;

import com.example.mvp_master.Model.ILoginlModel;
import com.example.mvp_master.Model.ILoginlModellmpl;
import com.example.mvp_master.VIew.ILoginView;

import java.lang.ref.SoftReference;

public class ILoginPersenterlmpl implements ILoginPersenter {
    ILoginView iLoginView;
    private ILoginlModel modellmpl;
    private SoftReference<ILoginlModel> reference;
    @Override
    public void attahView(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        //创建M层  请求数据
        modellmpl = new ILoginlModellmpl();
        reference = new SoftReference<>(modellmpl);
    }

    @Override
    public void detachView(ILoginView iLoginView) {
        reference.clear();

    }

    @Override
    public void requestData(String userName, String pwd) {
        modellmpl.containData(userName, pwd, new ILoginlModel.CallBack() {
            @Override
            public void responseData(String response) {
                iLoginView.showLoginData(response);
            }
        });
    }



}

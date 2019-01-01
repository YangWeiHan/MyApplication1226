package com.example.holiday_work.di.presenter;

import com.example.holiday_work.di.contract.LoginContracl;
import com.example.holiday_work.di.model.ILoginModellmpl;

import java.lang.ref.SoftReference;

public class ILoginPresenterlmpl implements LoginContracl.ILoginPresenter<LoginContracl.ILoginView> {
    LoginContracl.ILoginView iLoginView;
    private SoftReference<LoginContracl.ILoginView> reference;
    private ILoginModellmpl modellmpl;


    @Override
    public void attahView(LoginContracl.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        reference = new SoftReference<>(iLoginView);
        modellmpl = new ILoginModellmpl();

    }

    @Override
    public void detachView(LoginContracl.ILoginView iLoginView) {

        reference.clear();

    }

    @Override
    public void requestData(String loginUserName, String loginPassword) {
        modellmpl.containLoginResponesData(loginUserName, loginPassword, new LoginContracl.LoginModel.MyCallBack() {
            @Override
            public void requestLoginData(String responseData) {
                iLoginView.setData(responseData);
            }
        });

    }
}

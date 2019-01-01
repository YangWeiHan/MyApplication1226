package com.example.mvp_responsenewsdata.di.prisenter;

import com.example.mvp_responsenewsdata.di.contract.ILoginContract;
import com.example.mvp_responsenewsdata.di.model.LoginModellmpl;

import java.lang.ref.SoftReference;

public class LoginPresenterimpl implements ILoginContract.ILoginPresenter<ILoginContract.ILoginView> {
    //在这里  要实现V层 和 M层
    ILoginContract.ILoginView loginView;
    private ILoginContract.ILoginModel modellmpl;
    private SoftReference<ILoginContract.ILoginView> reference;

    @Override
    public void attahView(ILoginContract.ILoginView iLoginView) {
        this.loginView = iLoginView;
        //在这里 要 new  一个 M层的实现类
        modellmpl = new LoginModellmpl();
        //之后 要用一个软引用进行包裹
        reference = new SoftReference<>(loginView);
    }

    @Override
    public void detachView(ILoginContract.ILoginView iLoginView) {
        //清除
        reference.clear();

    }
    //
    @Override
    public void requstLoginData(String userName, String passwors) {

        modellmpl.containLoginResponesData(userName, passwors, new ILoginContract.ILoginModel.MyCallBack() {
            //回传回来的数据是
            @Override
            public void responesData(String responesData) {
                //V层  把这个数据接走 接走的 数据是 responesData
                loginView.showData(responesData);
            }
        });
    }

}

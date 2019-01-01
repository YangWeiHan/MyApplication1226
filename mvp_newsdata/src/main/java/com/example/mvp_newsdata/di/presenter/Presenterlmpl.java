package com.example.mvp_newsdata.di.presenter;

import com.example.mvp_newsdata.di.contract.INewsContract;
import com.example.mvp_newsdata.di.model.INewsModellmpl;

import java.lang.ref.SoftReference;

public class Presenterlmpl implements INewsContract.INewsPresenter<INewsContract.INewsView> {

    INewsContract.INewsView iNewsView;
    private SoftReference<INewsContract.INewsView> reference;
    private INewsContract.INewsModel modellmpl;

    @Override
    public void attachView(INewsContract.INewsView iNewsView) {
        this.iNewsView = iNewsView;

        reference = new SoftReference<>(iNewsView);

        modellmpl = new INewsModellmpl();


    }

    @Override
    public void detachView(INewsContract.INewsView iNewsView) {
        reference.clear();
    }

    @Override
    public void requstLoginData(String url, Class clazz) {
        modellmpl.showNewsResponesData(url, clazz, new INewsContract.INewsModel.NewsCallBack() {
            @Override
            public void setData(Object o) {
               iNewsView.showData(o);
            }

            @Override
            public void setFail(String msg) {

            }
        });
    }

}

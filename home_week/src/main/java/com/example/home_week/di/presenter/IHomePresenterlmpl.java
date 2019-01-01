package com.example.home_week.di.presenter;

import com.example.home_week.data.bean.ShopBean;
import com.example.home_week.di.contract.IHomeContract;
import com.example.home_week.di.model.IHomeModellmpl;

import java.lang.ref.SoftReference;

public class IHomePresenterlmpl implements IHomeContract.IHomePresenter<IHomeContract.IHomeView> {
    IHomeContract.IHomeView iHomeView;
    private IHomeContract.IHomeModel modellmpl;
    private SoftReference<IHomeContract.IHomeView> reference;


    @Override
    public void atahView(IHomeContract.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        modellmpl = new IHomeModellmpl();
        reference = new SoftReference<>(iHomeView);

    }

    @Override
    public void detachView(IHomeContract.IHomeView iHomeView) {
        reference.clear();
    }

    @Override
    public void requestData() {

       modellmpl.containData(new IHomeContract.IHomeModel.MyCallBack() {
           @Override
           public void onCallBack(ShopBean responseData) {
               iHomeView.showData(responseData);
           }
       });

    }
}

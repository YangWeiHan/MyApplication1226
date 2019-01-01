package com.example.holiday_work.di.presenter;

import com.example.holiday_work.data.bean.ShowBean;
import com.example.holiday_work.di.contract.ShowContracl;
import com.example.holiday_work.di.model.IShowModellmpl;

import java.lang.ref.SoftReference;

public class IShowPresenterlmpl implements ShowContracl.IShopPresenter<ShowContracl.IShopView> {
    ShowContracl.IShopView iShopView;
    private SoftReference<ShowContracl.IShopView> reference;
    private IShowModellmpl modellmpl;


    @Override
    public void attahView(ShowContracl.IShopView iShopView) {
        this.iShopView = iShopView;
        reference = new SoftReference<>(iShopView);
        modellmpl = new IShowModellmpl();
    }

    @Override
    public void detachView(ShowContracl.IShopView iShopView) {
        reference.clear();
    }

    @Override
    public void requestShowData() {
        modellmpl.containShopRequestData(new ShowContracl.IShopModl.MyCallBack() {
            @Override
            public void responestData(ShowBean showBean) {
                iShopView.setData(showBean);
            }
        });
    }
}

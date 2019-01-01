package com.example.home_week.di.contract;

import com.example.home_week.data.bean.ShopBean;

public interface IHomeContract {

    //V 层接口
    public interface IHomeView{
        void showData(ShopBean shopBean);
    }

    //P层接口

    public  interface IHomePresenter<IHomeView>{
        //绑定
        void atahView(IHomeContract.IHomeView iHomeView);
        //解绑
        void detachView(IHomeContract.IHomeView iHomeView);
        //数据请求
        void requestData();
    }

    //M层  接口
    public  interface IHomeModel{

        //  去请求数据
        void containData(MyCallBack myCallBack);

        public interface MyCallBack{
             void onCallBack(ShopBean shopBean);
        }
    }
}

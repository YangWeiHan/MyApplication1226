package com.example.holiday_work.di.contract;

import com.example.holiday_work.data.bean.ShowBean;

public interface ShowContracl {

    //V  层  接口
    public interface IShopView{

        void setData(ShowBean showBean);
    }

    //P 层
    public interface IShopPresenter<IShopView>{
        void attahView(IShopView iShopView);
        void detachView(IShopView iShopView);
        void requestShowData();
    }

    //M 层
    public interface IShopModl{

        void containShopRequestData(MyCallBack myCallBack);

        public interface MyCallBack{
            //显示数据方法
            void responestData(ShowBean showBean);
        }
    }
}

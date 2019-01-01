package com.example.mvp_responsenewsdata.di.contract;

//这里面都是接口  是MVP 的接口
public interface ILoginContract {
    //接口里可以定义解口

    //首先定义V层接口
    public interface ILoginView{
        //定义方法  显示数据
         void showData(String responesData);
    }

    //定义P层接口
    public interface ILoginPresenter<ILoginView>{
        //  进行绑定
        void attahView(ILoginView iLoginView);

        //  进行解绑
        void detachView(ILoginView iLoginView);

        //数据请求 请求M层数据  做登录

        void requstLoginData(String userName, String passwors);
    }

    //M  层 的接口
    public interface ILoginModel{
        //  登录的接口其请求
        void containLoginResponesData(String userName, String password, MyCallBack myCallBack);

        //接口回调
        public interface MyCallBack{
            //回显数据方法
            void responesData(String responesData);
        }
    }

}

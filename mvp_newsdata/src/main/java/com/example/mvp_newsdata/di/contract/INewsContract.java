package com.example.mvp_newsdata.di.contract;

public interface INewsContract {

    //V  层接口
    public interface INewsView<T>{
        void showData(T data);
    }

    //P  层接口
    public interface INewsPresenter<INewsView>{
        //绑定
          void attachView(INewsView iNewsView);

        //解绑
         void detachView(INewsView iNewsView);
        //数据请求，请求M层数据，做登录处理
          void requstLoginData(String url,Class clazz);

    }

    public interface INewsModel{
        //做一个展示数据的接口
        void showNewsResponesData(String url,Class clazz ,NewsCallBack newsCallBack);

        //接口回调
        public interface NewsCallBack<T>{

            void setData(T t);

            void setFail(String msg);
        }
    }
}

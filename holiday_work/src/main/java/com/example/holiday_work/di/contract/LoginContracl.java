package com.example.holiday_work.di.contract;

public interface LoginContracl {

    //V  层接口
    public interface ILoginView{

        void setData(String responseData);
    }

    //p
    public interface ILoginPresenter<ILoginView>{
        void attahView(ILoginView iLoginView);
        void detachView(ILoginView iLoginView);
        void requestData(String loginUserName, String loginPassword);
    }

    //M

    public interface LoginModel{

        void containLoginResponesData(String loginUserName, String loginPassword,MyCallBack myCallBack);
        //接口回调
        public interface MyCallBack{

            void requestLoginData(String responseData);
        }
    }
}

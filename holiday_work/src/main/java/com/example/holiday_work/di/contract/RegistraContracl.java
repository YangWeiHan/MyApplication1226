package com.example.holiday_work.di.contract;

public interface RegistraContracl {

    //V  层接口
    public interface IRegistraView{

        void setData(String responesData);
    }

    //p
    public interface IRegistraPresenter<IRegistraView>{
        void attahView(IRegistraView iRegistraView);
        void detachView(IRegistraView iRegistraView);
        void requestData(String registerUserName, String registerPassword);


    }

    //M

    public interface RegistraModel{

        void containRegistrResponesData(String registerUserName, String registerPassword,MyCallBack myCallBack);
        //接口回调
        public interface MyCallBack{

            void requestRegistrData(String responesData);
        }
    }
}

package com.example.mvp_master.Model;

public interface ILoginlModel {

    void containData(String userName, String pwd,CallBack callBack);

    public interface CallBack{
        void responseData(String response);
    }


}

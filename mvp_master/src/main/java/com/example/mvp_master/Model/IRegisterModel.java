package com.example.mvp_master.Model;

public interface IRegisterModel {
    //请求数据接口  返回数据
    void containData(String userName,String pwd,CallBack callBack);
    //定义一个接口
    public interface CallBack{
        public void responseData(String response);
    }
}

package com.example.mvp_master.Piresent;

import com.example.mvp_master.VIew.IRegisterView;

public interface IRegisterPresenter {
    //接收V层  对象
    void attahView(IRegisterView registerView);
    //交给M层进行数据请求的处理
    void requsetData(String userName,String pwd);
}

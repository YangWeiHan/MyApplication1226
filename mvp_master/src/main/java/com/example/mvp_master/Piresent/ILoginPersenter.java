package com.example.mvp_master.Piresent;

import com.example.mvp_master.VIew.ILoginView;

public interface ILoginPersenter {
    //接收V层 对象
    void attahView(ILoginView iLoginView);
    //解绑V层对象
    public void detachView(ILoginView iLoginView);
    //交给M层进行数据请求的处理
    public void requestData(String userName, String pwd);




}

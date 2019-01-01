package com.example.mvp_master.Piresent;

import com.example.mvp_master.Model.IRegisterModel;
import com.example.mvp_master.Model.IRegisterModellmpl;
import com.example.mvp_master.VIew.IRegisterView;

import java.lang.ref.SoftReference;

public class IRegisterPresenterlmpl implements IRegisterPresenter {
    IRegisterView registerView;
    private IRegisterModel model;
    private SoftReference<IRegisterModel> reference;

    @Override
    public void attahView(IRegisterView registerView) {
           this.registerView = registerView;
           //创建M层  请求数据
        model = new IRegisterModellmpl();
        //声明软引用
        reference = new SoftReference<>(model);
    }
    public void datachView(IRegisterView iRegisterView){
        //把应用对象进行及时回收
        reference.clear();

    }

    @Override
    public void requsetData(String userName, String pwd) {
        //数据触发
        model.containData(userName, pwd, new IRegisterModel.CallBack() {
            @Override
            public void responseData(String response) {
                //数据回传给V 层
                registerView.showData(response);
            }
        });

    }


}

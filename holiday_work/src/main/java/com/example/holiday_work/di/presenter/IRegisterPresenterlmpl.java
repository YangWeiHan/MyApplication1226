package com.example.holiday_work.di.presenter;

import com.example.holiday_work.di.contract.RegistraContracl;
import com.example.holiday_work.di.model.IReginterModellmpl;

import java.lang.ref.SoftReference;

public class IRegisterPresenterlmpl implements RegistraContracl.IRegistraPresenter<RegistraContracl.IRegistraView> {
    RegistraContracl.IRegistraView iRegistraView;
    private RegistraContracl.RegistraModel modellmpl;
    private SoftReference<RegistraContracl.IRegistraView> reference;

    @Override
    public void attahView(RegistraContracl.IRegistraView iRegistraView) {
        this.iRegistraView = iRegistraView;

        reference = new SoftReference<>(iRegistraView);
        modellmpl = new IReginterModellmpl();


    }

    @Override
    public void detachView(RegistraContracl.IRegistraView iRegistraView) {
        reference.clear();
    }

    @Override
    public void requestData(String registerUserName, String registerPassword) {
        modellmpl.containRegistrResponesData(registerUserName, registerPassword, new RegistraContracl.RegistraModel.MyCallBack() {

            @Override
            public void requestRegistrData(String responesData) {
                iRegistraView.setData(responesData);
            }
        });
    }


}
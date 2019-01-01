package com.example.mvp_master.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp_master.Piresent.IRegisterPresenter;
import com.example.mvp_master.Piresent.IRegisterPresenterlmpl;
import com.example.mvp_master.R;
import com.example.mvp_master.VIew.IRegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {


    @BindView(R.id.ed_userName)
    EditText edUserName;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    //私有化P层
    private IRegisterPresenter presenter;

    // private IRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建P层 对象 实现类
        presenter = new IRegisterPresenterlmpl();
        presenter.attahView(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        //用户名。密码
        String userName = edUserName.getText().toString();
        String pwd = edPassword.getText().toString();
        presenter.requsetData(userName,pwd);
    }

    @Override
    public void showData(String response) {
        Toast.makeText(this, response+"注册成功了！！奥利给", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.attahView(this);
    }
}

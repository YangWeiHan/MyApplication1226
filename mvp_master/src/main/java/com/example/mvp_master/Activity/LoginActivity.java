package com.example.mvp_master.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp_master.Piresent.ILoginPersenter;
import com.example.mvp_master.Piresent.ILoginPersenterlmpl;
import com.example.mvp_master.R;
import com.example.mvp_master.VIew.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.ed_userName_login)
    EditText edUserNameLogin;
    @BindView(R.id.ed_password_login)
    EditText edPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register_login)
    Button btnRegisterLogin;
    private ILoginPersenter iLoginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //创建P层对象
        iLoginPersenter = new ILoginPersenterlmpl();
        iLoginPersenter.attahView(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String login_name = edUserNameLogin.getText().toString();
                String login_pwd = edPasswordLogin.getText().toString();
                iLoginPersenter.requestData(login_name,login_pwd);
                break;
            case R.id.btn_register_login:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iLoginPersenter.detachView(this);
    }

    @Override
    public void showLoginData(String response) {
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
    }
}

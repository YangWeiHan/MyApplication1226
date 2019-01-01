package com.example.holiday_work.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.holiday_work.R;
import com.example.holiday_work.data.bean.LoginBean;
import com.example.holiday_work.di.contract.LoginContracl;
import com.example.holiday_work.di.presenter.ILoginPresenterlmpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContracl.ILoginView {

    @BindView(R.id.ed_userName_login)
    EditText edUserNameLogin;
    @BindView(R.id.ed_password_login)
    EditText edPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.remember_password)
    CheckBox rememberPassword;
    private LoginContracl.ILoginPresenter presenterlmpl;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenterlmpl = new ILoginPresenterlmpl();
        presenterlmpl.attahView(this);
        preferences = getSharedPreferences("RememberPassword",Context.MODE_PRIVATE);
        boolean remember = preferences.getBoolean("remember", false);
        if (remember){
            String Tpass = preferences.getString("pass", "");
            String Tuser = preferences.getString("user", "");
            edUserNameLogin.setText(Tuser);
            edPasswordLogin.setText(Tpass);
            rememberPassword.setChecked(remember);

        }



    }

    @OnClick({R.id.btn_login, R.id.btn_register,R.id.remember_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String loginUserName = edUserNameLogin.getText().toString();
                String loginPassword = edPasswordLogin.getText().toString();
                presenterlmpl.requestData(loginUserName, loginPassword);

                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.remember_password:
                String userText = edUserNameLogin.getText().toString().trim();
                String passText = edPasswordLogin.getText().toString().trim();
                if (userText.equals("")|| passText.equals("")){
                    return;
                }
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("remember",rememberPassword.isChecked());
                edit.putString("user",userText);
                edit.putString("pass",passText);
                edit.commit();
                startActivity(new Intent( LoginActivity.this,ShowActivity.class));
                finish();


        }
    }

    @Override
    public void setData(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, responseData, Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(responseData, LoginBean.class);
                String code = loginBean.getCode();
                if (code.equals("0")) {
                    startActivity(new Intent(LoginActivity.this, ShowActivity.class));
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }
}

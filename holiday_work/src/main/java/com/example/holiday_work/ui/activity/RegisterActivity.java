package com.example.holiday_work.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.holiday_work.R;
import com.example.holiday_work.data.bean.RegisterBean;
import com.example.holiday_work.di.contract.RegistraContracl;
import com.example.holiday_work.di.presenter.IRegisterPresenterlmpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegistraContracl.IRegistraView {

    @BindView(R.id.ed_userName_register)
    EditText edUserNameRegister;
    @BindView(R.id.ed_password_register)
    EditText edPasswordRegister;
    @BindView(R.id.ed_password_confirm)
    EditText edPasswordConfirm;
    @BindView(R.id.btn_registration)
    Button btnRegistration;
    private RegistraContracl.IRegistraPresenter presenterlmpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenterlmpl = new IRegisterPresenterlmpl();
        presenterlmpl.attahView(this);

    }

    @OnClick(R.id.btn_registration)
    public void onViewClicked() {
        String registerUserName = edUserNameRegister.getText().toString();
        String registerPassword = edPasswordRegister.getText().toString();
        String confirmpassword = edPasswordConfirm.getText().toString();
        if (registerPassword.equals(confirmpassword)){
            presenterlmpl.requestData(registerUserName,registerPassword);
        }else {
            Toast.makeText(RegisterActivity.this,"两次输入的密码不一样，请再次输入",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setData(final String responesData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this,responesData,Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(responesData, RegisterBean.class);
                String code = registerBean.getCode();
                if (code.equals("0")){
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
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

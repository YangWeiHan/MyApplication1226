package com.example.mvp_responsenewsdata.ui.actifity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp_responsenewsdata.R;
import com.example.mvp_responsenewsdata.data.bean.LoginBean;
import com.example.mvp_responsenewsdata.di.contract.ILoginContract;
import com.example.mvp_responsenewsdata.di.prisenter.LoginPresenterimpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ILoginContract.ILoginView {

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private ILoginContract.ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建P层的实现类
        presenter = new LoginPresenterimpl();
        //实现P层的方法
        presenter.attahView(this);
    }

    @Override
    public void showData(final String responesData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //子线程切换到UI
                Toast.makeText(MainActivity.this,responesData,Toast.LENGTH_LONG).show();
                Log.i("tag", "run: "+responesData);
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(responesData, LoginBean.class);
                String status = loginBean.getStatus();
                if (status.equals("0000")){
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                    finish();
                }
            }
        });

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        //获取用户名  和密码
        String userName = etUserName.getText().toString();
        String passwors = etPassword.getText().toString();
        //获取P层的接口  进行操作  并且传入参数  没有P 层  那就创建一个P层
        //带个参数走！！
        presenter.requstLoginData(userName,passwors);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        presenter.detachView(this);
    }
}

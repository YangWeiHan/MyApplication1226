package com.example.mvp_responsenewsdata.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvp_responsenewsdata.R;
import com.example.mvp_responsenewsdata.data.apils.Apils;
import com.example.mvp_responsenewsdata.data.bean.ShopBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class MyPagerFragment extends Fragment {
    private Context context;
    private RecyclerView rv_contianl;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //得到布局
        View view = inflater.inflate(R.layout.mypager_item,container,false);
        //得到资源ID
        rv_contianl = view.findViewById(R.id.mypagerv_container);
        //上下文
        context = getActivity();
        //用  OKGO   进行 网络请求
        OkGo.<String>get(Apils.SHOP_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                // toString  response
                String result = response.body().toString();
                //解析
                Gson gson = new Gson();
                //得到  解析以后的数据  存到JAVA  BEAN里
                ShopBean shopBean = gson.fromJson(result, ShopBean.class);
                ArrayList<ShopBean.DataBean.ListBean> list = (ArrayList<ShopBean.DataBean.ListBean>) shopBean.getData().get(2).getList();
                //得到数据源以后  写一个 布局管理器  写一个  线性布局
                LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                rv_contianl.setLayoutManager(layoutManager);
                //   得到数据  得到布局管理器   写下来写适配器


            }
        });



       /* List<ShopBean.DataBean.ListBean> listBeans = new ArrayList<>();*/
        return view;
    }
}

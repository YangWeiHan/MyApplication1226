package com.example.holiday_work.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.holiday_work.R;
import com.example.holiday_work.data.adapter.ShowAdapter;
import com.example.holiday_work.data.bean.ShowBean;
import com.example.holiday_work.di.contract.ShowContracl;
import com.example.holiday_work.di.presenter.IShowPresenterlmpl;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment implements ShowContracl.IShopView {

    private RecyclerView ry_recyclerView;
    private ShowContracl.IShopPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_layout,container,false);
        ry_recyclerView = view.findViewById(R.id.rv_recylerview);
        presenter = new IShowPresenterlmpl();
        //绑定
        presenter.attahView(this);
        presenter.requestShowData();
        return view;
    }


    @Override
    public void setData(ShowBean showBean) {
        //得到 数据源
        ArrayList<ShowBean.DataBean> arrayList = (ArrayList<ShowBean.DataBean>) showBean.getData();
        //设置 布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        ry_recyclerView.setLayoutManager(layoutManager);
        //适配器
        ShowAdapter adapter = new ShowAdapter(R.layout.show_item,arrayList);
        ry_recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }
}

package com.example.home_week.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home_week.R;
import com.example.home_week.data.bean.ShopBean;
import com.example.home_week.di.contract.IHomeContract;
import com.example.home_week.di.presenter.IHomePresenterlmpl;
import com.example.home_week.ui.adapter.ShopAdapter;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment implements IHomeContract.IHomeView {


    private IHomePresenterlmpl presenterlmpl;
    private RecyclerView rv_recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_layout,container,false);
        rv_recyclerView = view.findViewById(R.id.rv_recylerview);
        presenterlmpl = new IHomePresenterlmpl();
        presenterlmpl.atahView(this);
        presenterlmpl.requestData();


        return view;
    }

    @Override
    public void showData(ShopBean responseData) {
        //得到数据源
        ArrayList<ShopBean.DataBean> beans = (ArrayList<ShopBean.DataBean>) responseData.getData();
        //得到  布局文件
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_recyclerView.setLayoutManager(layoutManager);
        //适配器
        ShopAdapter adapter = new ShopAdapter(R.layout.shop_item,beans);
        rv_recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }
}

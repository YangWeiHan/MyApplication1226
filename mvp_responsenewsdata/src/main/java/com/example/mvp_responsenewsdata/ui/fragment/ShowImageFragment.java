package com.example.mvp_responsenewsdata.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mvp_responsenewsdata.R;
import com.example.mvp_responsenewsdata.data.apapter.ImageAdapter;
import com.example.mvp_responsenewsdata.data.apils.Apils;
import com.example.mvp_responsenewsdata.data.bean.NewsBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class ShowImageFragment extends Fragment {
    Context context;

    private RecyclerView recyclerView_container;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_item,container,false);
        recyclerView_container = view.findViewById(R.id.rv_container);
        context = getActivity();

        OkGo.<String>get(Apils.REQUEST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                //解析
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(responseData,NewsBean.class);
                //1得到数据源
                ArrayList<NewsBean.NewslistBean> beanList = (ArrayList<NewsBean.NewslistBean>) newsBean.getNewslist();
                //2布局管理器   pubuliu
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                recyclerView_container.setLayoutManager(manager);
                //3 适配器
                ImageAdapter adapter = new ImageAdapter();
                adapter.setNewslistBeans(context,beanList);
                //设置
                recyclerView_container.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
        });

        return view;
    }
}

package com.example.mvp_newsdata.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.mvp_newsdata.R;
import com.example.mvp_newsdata.data.adapter.NewsAdapter;
import com.example.mvp_newsdata.data.apils.Apils;
import com.example.mvp_newsdata.data.bean.NewsBean;
import com.example.mvp_newsdata.di.contract.INewsContract;
import com.example.mvp_newsdata.di.presenter.Presenterlmpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements INewsContract.INewsView {
    private XRecyclerView xRecyclerView;
    private INewsContract.INewsPresenter presenterlmpl;
    private NewsAdapter adapter;
    /*private List<NewsBean.NewslistBean> beanList;*/
    private int hMaxNum = 3;
    private int pager = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源Id
        xRecyclerView = findViewById(R.id.xRecyclerView);

        presenterlmpl = new Presenterlmpl();
        presenterlmpl.attachView(this);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,hMaxNum);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(gridLayoutManager);

        //实例化Adapter
        adapter = new NewsAdapter(this);
        //设置适配器
        xRecyclerView.setAdapter(adapter);
        //设置xxRecyclerView加载和刷新
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pager =1;
                presenterlmpl.requstLoginData(String.format(Apils.NEWS_URL),NewsBean.class);
            }

            @Override
            public void onLoadMore() {
                pager ++;
                presenterlmpl.requstLoginData(String.format(Apils.NEWS_URL),NewsBean.class);
            }
        });

    }


    @Override
    public void showData(Object data) {
        NewsBean newsBean = (NewsBean) data;
        Log.e("teg",(String) data.toString());
        List<NewsBean.NewslistBean> newslistBeans = newsBean.getNewslist();
        if (pager == 1){
            adapter.setMlist(newslistBeans);
           xRecyclerView.refreshComplete();
        }else {
            adapter.addMlist(newslistBeans);
            xRecyclerView.loadMoreComplete();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }
}

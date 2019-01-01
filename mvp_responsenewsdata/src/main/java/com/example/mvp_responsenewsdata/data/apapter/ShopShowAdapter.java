package com.example.mvp_responsenewsdata.data.apapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvp_responsenewsdata.data.bean.ShopBean;

import java.util.List;

public class ShopShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShopBean.DataBean.ListBean> listBeans;

    public void setListBeans(Context context,List<ShopBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class SecondViewHolder extends  RecyclerView.ViewHolder {

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

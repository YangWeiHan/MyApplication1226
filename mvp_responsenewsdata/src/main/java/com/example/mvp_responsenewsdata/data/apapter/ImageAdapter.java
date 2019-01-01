package com.example.mvp_responsenewsdata.data.apapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mvp_responsenewsdata.R;
import com.example.mvp_responsenewsdata.data.bean.NewsBean;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    ArrayList<NewsBean.NewslistBean> newslistBeans;
    Context context;


    //接收数据

    public void setNewslistBeans(Context context,ArrayList<NewsBean.NewslistBean> newslistBeans) {
        this.newslistBeans = newslistBeans;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.new_item_layout,viewGroup,false);
        //设置  ViewHolder
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(newslistBeans.get(i).getPicUrl()).into(viewHolder.iv_icon);
    }

    @Override
    public int getItemCount() {
        return newslistBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}

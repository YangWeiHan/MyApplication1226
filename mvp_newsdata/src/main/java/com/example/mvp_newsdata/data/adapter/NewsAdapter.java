package com.example.mvp_newsdata.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mvp_newsdata.R;
import com.example.mvp_newsdata.data.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NewsBean.NewslistBean> mlist;

    public NewsAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }
    //下拉
    public void setMlist(List<NewsBean.NewslistBean> mlist) {
        if (mlist != null){
            this.mlist = mlist;
        }
        notifyDataSetChanged();
    }
    //上拉
    public void addMlist(List<NewsBean.NewslistBean> mlist) {
        if (mlist != null){
            this.mlist = mlist;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(context).load(mlist.get(i).getPicUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_item_image);
        }
    }
}

package com.example.holiday_work.data.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.holiday_work.R;
import com.example.holiday_work.data.bean.ShowBean;

import java.util.List;

public class ShowAdapter extends BaseQuickAdapter<ShowBean.DataBean,BaseViewHolder> {
    public ShowAdapter(int layoutResId, @Nullable List<ShowBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowBean.DataBean item) {
        helper.setText(R.id.news_title,item.getNews_title());
        Glide.with(mContext).load(item.getPic_url()).into((ImageView) helper.getView(R.id.pic_url));

    }
}

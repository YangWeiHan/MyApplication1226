package com.example.home_week.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.home_week.R;
import com.example.home_week.data.bean.ShopBean;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ShopBean.DataBean,BaseViewHolder> {

    public ShopAdapter(int layoutResId, @Nullable List<ShopBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean.DataBean item) {
        if (item.getList().size() != 0){
            if (null != item.getList().get(0).getSubhead()){
                helper.setText(R.id.subhead,item.getList().get(0).getSubhead());
            }
            if (null != item.getList().get(0).getPrice()+""){
                helper.setText(R.id.shop_price,item.getList().get(0).getPrice()+"");
            }
            if (null != item.getList().get(0).getImages()){
                String shop_images = item.getList().get(0).getImages();
                String[] split = shop_images.split("\\|");
                Glide.with(mContext).load(split[0]).into((ImageView) helper.getView(R.id.shop_icon));
            }
        }

    }
}

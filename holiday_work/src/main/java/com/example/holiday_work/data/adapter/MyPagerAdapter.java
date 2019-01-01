package com.example.holiday_work.data.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.holiday_work.ui.fragment.MainFragment;
import com.example.holiday_work.ui.fragment.ShoppingFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    //设置数据  展示Fragment
    private String[] list = new String[]{
            "数据展示","我的页面"
    };

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
               return new ShoppingFragment();
            case 1:
               return new MainFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list[position];
    }

    @Override
    public int getCount() {
        return list.length;
    }
}

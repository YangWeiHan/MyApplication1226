package com.example.mvp_responsenewsdata.data.apapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mvp_responsenewsdata.ui.fragment.MyPagerFragment;
import com.example.mvp_responsenewsdata.ui.fragment.ShowImageFragment;

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
               return new ShowImageFragment();
            case 1:
               return new MyPagerFragment();
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

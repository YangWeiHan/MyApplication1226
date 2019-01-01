package com.example.holiday_work.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.holiday_work.R;
import com.example.holiday_work.data.adapter.MyPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.shouye_viewpager)
    ViewPager shouyeViewpager;
    @BindView(R.id.shouye_tablayout)
    TabLayout shouyeTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        shouyeViewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        shouyeTablayout.setupWithViewPager(shouyeViewpager);
    }
}

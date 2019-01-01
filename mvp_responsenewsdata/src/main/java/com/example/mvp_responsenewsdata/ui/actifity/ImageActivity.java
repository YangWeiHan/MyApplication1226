package com.example.mvp_responsenewsdata.ui.actifity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.mvp_responsenewsdata.R;
import com.example.mvp_responsenewsdata.data.apapter.MyPagerAdapter;

public class ImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        tabLayout = findViewById(R.id.shouye_tablayout);
        viewPager = findViewById(R.id.shouye_viewpager);

        //Fragment嵌套Fragment
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

          tabLayout.setupWithViewPager(viewPager);
    }
}

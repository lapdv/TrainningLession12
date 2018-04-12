package com.mobile.lapdv.trainninglession12.screen.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.mobile.lapdv.trainninglession12.R;
import com.mobile.lapdv.trainninglession12.screen.home.adapter.ViewPagerFragmentAdapter;
import com.mobile.lapdv.trainninglession12.utils.CircleLayoutUtils;
import com.mobile.lapdv.trainninglession12.screen.widget.indicator.CircleIndicator;


public class HomeActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tablayout;
    private CircleIndicator indicator;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setupViewPager();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        tablayout = findViewById(R.id.tabLayout);
        indicator = findViewById(R.id.indicator);
        linearLayout = findViewById(R.id.ll_layout);
    }

    private void setupViewPager() {
        ViewPagerFragmentAdapter adapter =
                new ViewPagerFragmentAdapter(getSupportFragmentManager(), 3);
        if (adapter != null) {
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(3);
            indicator.setViewPager(viewPager);
            tablayout.setupWithViewPager(viewPager);
        }
        CircleLayoutUtils.backgroundDayOfWeek(this, linearLayout, true);
    }
}

package com.mobile.lapdv.trainninglession12.screen.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mobile.lapdv.trainninglession12.screen.home.StudentFragment;

/**
 * Created by lap on 10/04/2018.
 */

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private int pagePosition;

    public ViewPagerFragmentAdapter(FragmentManager fm, int position) {
        super(fm);
        this.pagePosition = position;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StudentFragment.newInstance(0, "Fragment1");
            case 1:
                return StudentFragment.newInstance(1, "Fragment2");
            case 2:
                return StudentFragment.newInstance(2, "Fragment3");
        }
        return null;
    }

    @Override
    public int getCount() {
        return pagePosition;
    }

    @Override
    public int getItemPosition(Object object) {
        System.out.println("state current" + object.toString());
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fragment 1";
            case 1:
                return "Fragment 2";
            case 2:
                return "Fragment 3";
        }
        return null;
    }
}

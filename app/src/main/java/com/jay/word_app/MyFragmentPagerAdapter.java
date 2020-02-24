package com.jay.word_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private final int PAGER_COUNT = 4;
    private MyFragment_review myFragmentReview ;
    private MyFragment_book myFragmentBook;
    private MyFragment_statistic myFragmentStatistic;
    private MyFragment_setting myFragmentSetting;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragmentReview = new MyFragment_review();
        myFragmentBook = new MyFragment_book();
        myFragmentStatistic = new MyFragment_statistic();
        myFragmentSetting = new MyFragment_setting();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragmentReview;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragmentBook;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragmentStatistic;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragmentSetting;
                break;
        }
        return fragment;
    }


}


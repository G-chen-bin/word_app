package com.jay.word_app;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //UI Objects
    private TextView txt_top_bar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_review;
    private RadioButton rb_book;
    private RadioButton rb_statistic;
    private RadioButton rb_setting;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private ViewPager vpager;
    private MyFragmentPagerAdapter vpAdapter;
    private List<android.support.v4.app.Fragment> mFragments = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();//绑定各种控件
        mFragments.add(new MyFragment_review());
        mFragments.add(new MyFragment_book());
        mFragments.add(new MyFragment_statistic());
        mFragments.add(new MyFragment_setting());
        vpager_setting();//ViewPage设置



    }

    public void bindViews() {
        txt_top_bar = (TextView) findViewById(R.id.txt_top_bar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);

        rb_review = (RadioButton) findViewById(R.id.rb_review);
        rb_book = (RadioButton) findViewById(R.id.rb_book);
        rb_statistic = (RadioButton) findViewById(R.id.rb_statistic);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        //RadioGroup中图片大小设置
        Drawable drawable1 = getResources().getDrawable(R.drawable.tab_menu_review);
        Drawable drawable2 = getResources().getDrawable(R.drawable.tab_menu_book);
        Drawable drawable3 = getResources().getDrawable(R.drawable.tab_menu_statistic);
        Drawable drawable4 = getResources().getDrawable(R.drawable.tab_menu_setting);
        drawable1.setBounds(0, 0, 60, 60);
        drawable2.setBounds(0, 0, 60, 60);
        drawable3.setBounds(0, 0, 60, 60);
        drawable4.setBounds(0, 0, 60, 60);
        rb_review.setCompoundDrawables(null, drawable1, null, null);
        rb_book.setCompoundDrawables(null, drawable2, null, null);
        rb_statistic.setCompoundDrawables(null, drawable3, null, null);
        rb_setting.setCompoundDrawables(null, drawable4, null, null);

        rg_tab_bar.setOnCheckedChangeListener(this);
    }
    public void vpager_setting() {
        vpAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments);
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(vpAdapter);
        vpager.setCurrentItem(0);//设置初始选中页面
        vpager.addOnPageChangeListener(this);//设置页面监听
        rb_review.setChecked(true);
    }

    //ViewPage的选择
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_review:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_book:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_statistic:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_setting:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }
    //重写三个ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
    }
    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_review.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_book.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_statistic.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_setting.setChecked(true);
                    break;
            }
        }
    }


}

package com.jay.word_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {


    private List<book> mData;
    private LayoutInflater layoutInflater;

    public MyListViewAdapter(LayoutInflater layoutInflater, List<book> mData) {
        super();
        this.mData=mData;
        this.layoutInflater =layoutInflater;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        book Book = (book) getItem(position); // 获取当前项的book实例

        View view=layoutInflater.inflate(R.layout.book_item, null);
        ImageView bookImage = (ImageView) view.findViewById(R.id.book_image);//获取该布局内的图片视图
        TextView bookName = (TextView) view.findViewById(R.id.book_name);//获取该布局内的文本视图

        bookImage.setImageResource(Book.getBookImageId());//为图片视图设置图片资源
        bookName.setText(Book.getBookName());//为文本视图设置文本内容
        return view;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

}
